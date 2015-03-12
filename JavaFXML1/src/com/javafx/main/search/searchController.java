/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.main.search;
import com.javafx.main.patient.Patient;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.javafx.main.prescription.rxController;

/**
 *
 * @author crepanich23
 */
public class searchController implements Initializable{
   public static int selectedRow;
   @FXML private TextField FirstLastTextField;
   @FXML private TextField IDNumTextField;
   @FXML private TextField LastVisitTextField;
   @FXML private TextArea MedicalHistoryTextArea;
   @FXML private TextArea AllergiesTextArea;
   @FXML private TextField DOBTextField;
   @FXML private TextField EmailTextField;
   @FXML private TextField PhoneNumberTextField;
   
   @FXML private TableView PatientTableView;
   @FXML private TableColumn NameColumn;
   @FXML private TableColumn IDColumn;
   private final ObservableList<Patient> data =
        FXCollections.observableArrayList(
            new Patient("Bob", "Smith", "bobsmith@example.com","February 3, 2015",
    "Hip Replacement (May 2014)", "Nuts", "3/4/1972","562-555-2343","342-23-1343"),
            new Patient("Jane", "Doe", "janedoe@example.com","May 2, 2014",
    "Healthy", "None", "10/15/1962","562-555-1988","676-92-7236")
        ); 
   
  @Override
   public void initialize(URL url, ResourceBundle rb) {
     PatientTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
     selectedRow = 0;
      // TODO
     TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new 
         PropertyValueFactory<Patient,String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(
            new PropertyValueFactory<Patient,String>("lastName")
        );
        
        TableColumn idCol = new TableColumn("Medical Number");
        idCol.setMinWidth(150);
        idCol.setCellValueFactory(
            new PropertyValueFactory<Patient,String>("IDnum")
        );

         PatientTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
    @Override 
    public void handle(MouseEvent event) {
            Node node = ((Node) event.getTarget()).getParent();
            TableRow row;
            if (node instanceof TableRow) {
                row = (TableRow) node;
            } else {
                // clicking on text part
                row = (TableRow) node.getParent();
            }
            selectedRow = row.getIndex();
            updatePatientInfo(selectedRow);
    }
});
        /////
     PatientTableView.setItems(data);  
     PatientTableView.getColumns().addAll(firstNameCol, lastNameCol, idCol);
     //Patient Info
     Patient first = data.get(0);
     FirstLastTextField.setText(first.getFirstName() + " " + first.getLastName());
     IDNumTextField.setText(first.getIDnum());
     LastVisitTextField.setText(first.getLastVisit());
     MedicalHistoryTextArea.setText(first.getHistory());
     AllergiesTextArea.setText(first.getAllergies());
     DOBTextField.setText(first.getDOB());
     EmailTextField.setText(first.getEmail());
     PhoneNumberTextField.setText(first.getPhoneNumber());
  } 
   
  public void updatePatientInfo(int index){
     Patient first = (Patient)PatientTableView.getItems().get(index);
     FirstLastTextField.setText(first.getFirstName() + " " + first.getLastName());
     IDNumTextField.setText(first.getIDnum());
     LastVisitTextField.setText(first.getLastVisit());
     MedicalHistoryTextArea.setText(first.getHistory());
     AllergiesTextArea.setText(first.getAllergies());
     DOBTextField.setText(first.getDOB());
     EmailTextField.setText(first.getEmail());
     PhoneNumberTextField.setText(first.getPhoneNumber());
  }
    @FXML protected void handleContinueButton(ActionEvent event) throws Exception{ 
       //go to prescription page
        //start new scene without closing old
        rxController x = new rxController();
        x.patient = data.get(selectedRow);
        x.handleContinueRx();
    }
}


///TODO 
/*
 * Edit patient (dont allow editing unless edit patient button clicked
 * Change "edit patient" to "save patient" when editing
 */
