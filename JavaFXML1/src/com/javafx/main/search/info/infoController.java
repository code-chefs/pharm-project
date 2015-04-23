/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.main.search.info;

import com.javafx.main.JDBC;
import com.javafx.main.doctor.Doctor;
import com.javafx.main.patient.Patient;
import com.javafx.main.prescription.Prescription;
import com.javafx.main.search.info.prescription.rxController;
import static com.javafx.main.search.info.prescription.rxController.doctor;
import static com.javafx.main.search.info.prescription.rxController.patient;
import static com.javafx.main.search.searchController.doctorID;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author crepanich23
 */
public class infoController implements Initializable {
   public static Patient patient;
   public static Doctor doctor;
   
   @FXML private Label patientLabel;
   @FXML private Label doctorLabel;
   @FXML private TextField doctorID;
   @FXML private TextField patientID;
   @FXML private TextField lastVisit;
   @FXML private TextArea medicalHistory;
   @FXML private TableView rxTable;
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      patientLabel.setText("Patient: " + patient.getLastName()+ ", "+patient.getFirstName());
      doctorLabel.setText("Doctor: "+doctor.getLastName()+", "+doctor.getFirstName());
      doctorID.setText(doctor.getID());
      doctorID.setEditable(false);
      patientID.setText(patient.getMedID());
      patientID.setEditable(false);
      lastVisit.setText(patient.getLastVisit());
      lastVisit.setEditable(false);
      medicalHistory.setText(patient.getHistory());
      medicalHistory.setEditable(false);
      // TODO
      
      ObservableList<Prescription> data = rxTable.getItems();
      data.clear();
      JDBC db = new JDBC();
      List<List> list = db.querySelect("SELECT * FROM prescription WHERE patientID='"
       +patient.getMedID()+"';", 10);
      int i=0;
      for (List item: list){
         Prescription add = new Prescription((String)item.get(0),(String)item.get(1),
          (String)item.get(2),(String)item.get(3),(String)item.get(4),(String)item.get(5),
          (String)item.get(6),(String)item.get(7),(String)item.get(8),(String)item.get(9));
         data.add(add);
      }
   }
   
   @FXML protected void handleOrderRx(ActionEvent event) throws Exception{
      //order RX go to rx
      rxController.patient = patient;
      rxController.doctor = doctor;
      Parent root = FXMLLoader.load(getClass().getResource("prescription/rx.fxml"));
      Scene scene = new Scene(root);
      Stage stageRx = new Stage();
      stageRx.setTitle("Order Rx");
      stageRx.setScene(scene);
      stageRx.show();
   }
   
   @FXML protected void handleRefresh(ActionEvent event) throws Exception {
      //refresh rx table
      ObservableList<Prescription> data = rxTable.getItems();
      data.clear();
      JDBC db = new JDBC();
      List<List> list = db.querySelect("SELECT * FROM prescription WHERE patientID='"
       +patient.getMedID()+"';", 10);
      int i=0;
      for (List item: list){
         Prescription add = new Prescription((String)item.get(0),(String)item.get(1),
          (String)item.get(2),(String)item.get(3),(String)item.get(4),(String)item.get(5),
          (String)item.get(6),(String)item.get(7),(String)item.get(8),(String)item.get(9));
         data.add(add);
      }
   }
   
   @FXML protected void handleRemoveRx(ActionEvent event) throws Exception {
      int selectedRow = rxTable.getSelectionModel().getSelectedIndex();
      if (selectedRow != -1){//make sure a row is selected
         //grab data from table
          ObservableList<Prescription> data = rxTable.getItems();
          
          //now remove prescription from the database
          JDBC db = new JDBC();
          Prescription remove = data.get(selectedRow);
          String orderID = remove.getOrderID();
          //remove selected row
          db.queryUpdate("DELETE FROM prescription WHERE orderID='"+orderID+"'");
          data.remove(data.get(selectedRow));
          //display new table
          rxTable.setItems(data);
      }
   }
}
