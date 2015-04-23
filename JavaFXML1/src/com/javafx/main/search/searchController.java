/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.main.search;

import com.javafx.main.JDBC;
import com.javafx.main.Main;
import com.javafx.main.doctor.Doctor;
import com.javafx.main.patient.Patient;
import com.javafx.main.search.info.infoController;
import com.javafx.main.search.info.prescription.rxController;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 *
 * @author crepanich23
 */
public class searchController implements Initializable{
   public Patient patient;
   public Doctor doctor;
   public static String doctorID;
   static int selectedRow = -1;
   
   @FXML private Label userLabel;
   @FXML private TextField searchInput;
   @FXML private Button searchButton;
   @FXML private Button continueButton;
   @FXML private TableView searchTable;
   @FXML private TableColumn firstNameCol;
   @FXML private TableColumn lastNameCol;
   @FXML private TableColumn medicalNumCol;
   @FXML private TableColumn lastVisitCol;
   @FXML private ObservableList patientArrayList;
   
   @FXML protected void handleSearchAction(ActionEvent event) throws Exception{  
      ObservableList<Patient> data = searchTable.getItems();
      data.clear();
      String search = searchInput.getText();
      JDBC db = new JDBC();
      List<List> list = db.querySelect("SELECT * FROM patient WHERE doctorID='"
       +doctorID+"' AND firstName LIKE '%"+search+"%';", 6);
      int i=0;
      for (List item: list){
         Patient add = new Patient((String)item.get(0),(String)item.get(1),
          (String)item.get(2),(String)item.get(3),(String)item.get(4),(String)item.get(5));
         data.add(add);
      }
   }
   
   @FXML protected void handleContinueAction(ActionEvent event) throws Exception{  
      selectedRow = searchTable.getSelectionModel().getSelectedIndex();
      if (selectedRow != -1 && rxController.numberOfActiveWindows == 0){//make sure a row(patient) is selected before continuing
        ObservableList<Patient> data = searchTable.getItems();
        Patient selectedPatient = data.get(selectedRow);
        infoController.patient = selectedPatient;
        infoController.doctor = doctor;
        Parent root = FXMLLoader.load(getClass().getResource("info/info.fxml"));
        Scene scene = new Scene(root);
        Stage stageRx = new Stage();
        stageRx.setTitle("Patient Info");
        stageRx.setScene(scene);
        stageRx.show();
      }
   }
   
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      JDBC init = new JDBC();
      List<List> one = init.querySelect("SELECT firstName, lastName, loginID FROM doctor WHERE loginID='"+doctorID+"';",3);
      String fN = "",lN = "", id = "";
      for (List item: one){
         fN = (String)item.get(0);
         lN = (String)item.get(1);
         id = (String)item.get(2);
      }
      doctor = new Doctor(fN, lN, id);
      userLabel.setText("User: "+doctor.getLastName()+", "+doctor.getFirstName());
      
      ObservableList<Patient> data = searchTable.getItems();
      data.clear();
      String search = searchInput.getText();
      JDBC db = new JDBC();
      List<List> list = db.querySelect("SELECT * FROM patient WHERE doctorID='"
       +doctorID+"';", 6);
      int i=0;
      for (List item: list){
         Patient add = new Patient((String)item.get(0),(String)item.get(1),
          (String)item.get(2),(String)item.get(3),(String)item.get(4),(String)item.get(5));
         data.add(add);
      }
      
   }//end initialize  
}