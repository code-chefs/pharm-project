/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author crepanich23
 */
public class loginController implements Initializable {
   @FXML private PasswordField passwordField;
   @FXML private TextField usernameField;
   @FXML private Text actiontarget;
   
   @FXML protected void handleLoginButtonAction(ActionEvent event) throws Exception{     
      String u = usernameField.getText();
      String p = passwordField.getText();
      if (p.equals("1") && u.equals("Bob")){
         //good Doctor login   
        actiontarget.setText("Logging in...");
        
        //start new scene
        Parent root = FXMLLoader.load(getClass().getResource("search/patientSearch.fxml"));
        Scene scene = new Scene(root, 600, 500);
        Node source = (Node)event.getSource(); 
        Stage stageClose  = (Stage) source.getScene().getWindow();
        stageClose.close();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
   
      } else if(p.equals("2") && u.equals("Alyssa")){ //Pharmacist login test
        //good Pharmacist login   
        actiontarget.setText("Logging in...");
        
        //start new scene TODO
        Parent root = FXMLLoader.load(getClass().getResource("list/listPresc.fxml"));
        Scene scene = new Scene(root, 600, 500);
        Node source = (Node)event.getSource(); 
        Stage stageClose  = (Stage) source.getScene().getWindow();
        stageClose.close();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
      }else {
         //bad login
        actiontarget.setText("Invalid username/password");
      }
   }
   
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      // TODO
   }   
}
