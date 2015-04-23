/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.main.search.info.prescription;

import com.javafx.main.JDBC;
import com.javafx.main.doctor.Doctor;
import com.javafx.main.patient.Patient;
import static com.javafx.main.search.searchController.doctorID;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author crepanich23
 */
public class rxController implements Initializable{
   public static int numberOfActiveWindows = 0;
   public static Patient patient;
   public static Doctor doctor;
   
   @FXML private Label patientLabel;
   @FXML private Label doctorLabel;
   @FXML private CheckBox orderCheck;
   @FXML private Button orderButton;
   @FXML private TextField dosageField;
   @FXML private TextField rxField;
   @FXML private TextField quantityField;
   @FXML private TextField refillField;
   @FXML private TextArea instructionsField;
   @FXML private Label warningLabel;
   
   private String errMsg = "**CANNOT ORDER RX**   ";
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      patientLabel.setText("Patient: " + patient.getLastName()+ ", "+patient.getFirstName());
      doctorLabel.setText("Doctor: "+doctor.getLastName()+", "+doctor.getFirstName());
      orderCheck.setText("Order verified by: " + doctor.getFirstName() + " " +doctor.getLastName());
      warningLabel.setText("");
   }  
   
   @FXML protected void handleRxFill(ActionEvent event) throws Exception{  
      //initialize JDBC connection
      JDBC db = new JDBC();
      //dont continue if certain conditions aren't met
      boolean canOrder = true;
      //check if doctor clicked check box to verify order
      if (orderCheck.isSelected()==false){
         warningLabel.setText(errMsg+ "Check the box to verify the order");
         return;
      }
      //check if rx field has valid rx
      if (db.querySelect("SELECT rxName FROM pharmacy WHERE rxName='"+rxField.getText()+"'",1).isEmpty()){
         warningLabel.setText(errMsg+ "Invalid Rx Name");
         return;
      }
      
      // dosage/quantity/refill fields left empty
      if (dosageField.getText().length() < 1 || quantityField.getText().length() < 1
       || refillField.getText().length() < 1) {
         warningLabel.setText(errMsg+ "Do not leave dosage, quanity, or refill empty");
         return;
      }
      //check if rx is in stock
      String stock = (String) db.querySelect("SELECT rxQuantity FROM pharmacy WHERE rxName='"+rxField.getText()+"'",1).get(0).get(0);
      int quantity = Integer.parseInt(stock);
      if (quantity < Integer.parseInt(quantityField.getText()) && canOrder){
         warningLabel.setText(errMsg+ "The pharmacy does not have enough of that Rx in stock");
         return;
      }
      
      //order rx since checks have been done
      if (canOrder){
         int newQuantity = quantity - Integer.parseInt(quantityField.getText());
         //update quantity then add prescription
         db.queryUpdate("UPDATE pharmacy SET rxQuantity='"+newQuantity+"' WHERE rxName='"+rxField.getText()+"'");
         
         //get values to write prescription in database
         String name = rxField.getText();
         String dosage = dosageField.getText();
         String quant = quantityField.getText();
         String refill = refillField.getText();
         String inst = instructionsField.getText();
         String rxID = (String)db.querySelect("SELECT rxID FROM pharmacy WHERE rxName='"+name+"'", 1).get(0).get(0);
         //create Order ID
         int orderIDint = 0;
         if (db.querySelect("SELECT orderID FROM prescription", 1).isEmpty()){
            //no prescriptions in system (only for first one made)
            orderIDint = 900;
         } else {
            //find last rx in list 
            List<List> rs = db.querySelect("SELECT orderID FROM prescription ORDER BY orderID ASC", 1);
            int size = rs.size();
            String lastOrderID = (String)rs.get(size-1).get(0);
            int lastInt = Integer.parseInt(lastOrderID);
            orderIDint = lastInt+1;
         }
         String orderID = ""+orderIDint;
         Date date = new Date(System.currentTimeMillis()); // your date
         String orderDate = date.toString();
         String pID = patient.getMedID();
         String query ="INSERT INTO prescription (nameOfDrug, dosage, quantity, "+
          "refill, instructions, prescriptionID, orderID, patientID, dateOrdered, dateFilled) VALUES ('"
          +name+"','"+dosage+"','"+quant+"','"+refill+"','"+inst+"','"+rxID+"','"+orderID+"','"+pID+"','"+orderDate+"', NULL)"; 
         db.queryUpdate(query);
      }
      
      //finall close window
      Stage stage = (Stage) orderButton.getScene().getWindow();
      stage.close();
   }
   
   public void handleContinueRx() throws Exception{
      if (numberOfActiveWindows==0){
        Parent root = FXMLLoader.load(getClass().getResource("rx.fxml"));
        Scene scene = new Scene(root, 370, 330);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
          public void handle(WindowEvent we) {
             numberOfActiveWindows=0;
          }
         }); 
        numberOfActiveWindows++;
      }
   }
}
