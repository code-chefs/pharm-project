/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.main.search;

import com.javafx.main.JDBC;
import com.javafx.main.patient.Patient;
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
   
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      JDBC init = new JDBC();
      List<List> one = init.querySelect("SELECT firstName, lastName FROM doctor WHERE loginID='"+doctorID+"';",2);
      String fN = "",lN = "";
      for (List item: one){
         fN = (String)item.get(0);
         lN = (String)item.get(1);
      }
      userLabel.setText("User: "+lN+", "+fN);
      
   }//end initialize  
}