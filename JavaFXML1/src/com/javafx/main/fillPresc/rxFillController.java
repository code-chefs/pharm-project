/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.main.fillPresc;

import com.javafx.main.prescription.PrescOrder;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author orion
 */
public class rxFillController implements Initializable{
   static int numberOfActiveWindows = 0;
   public PrescOrder prescOrder;
   
   @FXML private Label patientNameLabel;
   //@FXML private ChoiceBox rxType;
   @FXML private Button orderRxButton;
   //@FXML private TextField dosageAmount;
   //@FXML private ChoiceBox dosageType;
   //@FXML private TextField rxQuantity;
   
   @FXML private TextField doctorSign;
   
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      patientNameLabel.setText("Smith, Bob");
      /*rxType.setItems(FXCollections.observableArrayList("Ambien",
       "Lipitor", "Valium", "Vicodin","Xanax"));*/
      //dosageType.setItems(FXCollections.observableArrayList("mg"));
      // TODO
       
      doctorSign.setText("Sign here");
   }  
   
   public void handleContinueRx() throws Exception{
      if (numberOfActiveWindows==0){
        Parent root = FXMLLoader.load(getClass().getResource("rxFill.fxml"));
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
