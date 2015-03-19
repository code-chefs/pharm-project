/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.main.list;
import com.javafx.main.prescription.PrescOrder;
import com.javafx.main.fillPresc.rxFillController;
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
/**
 *
 * @author orion
 */
public class listPrescController implements Initializable{
    public static int selectedRow;
   @FXML private TextArea DateOrderTextArea;
   @FXML private TextArea MedIDTextArea;
   @FXML private TextArea DocNameTextArea;
   @FXML private TextArea DocContactTextArea;
   @FXML private TextArea PatNameTextArea;
   @FXML private TextArea PatContactTextArea;
   @FXML private TextArea PillTextArea;
   
   @FXML private TableView PatientTableView;
   @FXML private TableColumn NameColumn;
   @FXML private TableColumn IDColumn;
   //TODO change this for prescriptions instead of patient info
   //read-only
   private final ObservableList<PrescOrder> data =
        FXCollections.observableArrayList(
            new PrescOrder("February 3, 2015","XXX-XXXY","Bob","342-23-1343",
                    "Smith, Bob", "bobsmith@example.com","Valium"),
            new PrescOrder("May 2, 2014","XXX-XXXX","Alyssa","676-92-7236",
                    "Doe, Jane", "janedoe@example.com","Ambien")
        ); 
   
  @Override
   public void initialize(URL url, ResourceBundle rb) {
     PatientTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
     selectedRow = 0;
      // TODO
     TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new 
         PropertyValueFactory<PrescOrder,String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(
            new PropertyValueFactory<PrescOrder,String>("lastName")
        );
        
        TableColumn idCol = new TableColumn("Medical Number");
        idCol.setMinWidth(150);
        idCol.setCellValueFactory(
            new PropertyValueFactory<PrescOrder,String>("IDnum")
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
     PrescOrder first = data.get(0);
     DateOrderTextArea.setText(first.getDate());
     DateOrderTextArea.setEditable(false);
     MedIDTextArea.setText(first.getMedNum());
     MedIDTextArea.setEditable(false);
     DocNameTextArea.setText(first.getDoctor());
     DocNameTextArea.setEditable(false);
     DocContactTextArea.setText(first.getDoctorContact());
     DocContactTextArea.setEditable(false);
     PatNameTextArea.setText(first.getPatName());
     PatNameTextArea.setEditable(false);
     PatContactTextArea.setText(first.getPatContact());
     PatContactTextArea.setEditable(false);
     PillTextArea.setText(first.getPill());
     PillTextArea.setEditable(false);
  } 
  
  public void updatePatientInfo(int index){
     PrescOrder first = (PrescOrder)PatientTableView.getItems().get(index);
     DateOrderTextArea.setText(first.getDate());
     DateOrderTextArea.setEditable(false);
     MedIDTextArea.setText(first.getMedNum());
     MedIDTextArea.setEditable(false);
     DocNameTextArea.setText(first.getDoctor());
     DocNameTextArea.setEditable(false);
     DocContactTextArea.setText(first.getDoctorContact());
     DocContactTextArea.setEditable(false);
     PatNameTextArea.setText(first.getPatName());
     PatNameTextArea.setEditable(false);
     PatContactTextArea.setText(first.getPatContact());
     PatContactTextArea.setEditable(false);
     PillTextArea.setText(first.getPill());
     PillTextArea.setEditable(false);
  }
    @FXML protected void handleContinueButton(ActionEvent event) throws Exception{ 
       //go to prescription page
        //start new scene without closing old
        rxFillController x = new rxFillController();
        x.prescOrder = data.get(selectedRow);
        x.handleContinueRx();
    }
}
