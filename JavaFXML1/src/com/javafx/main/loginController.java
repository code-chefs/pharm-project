/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.main;

import com.javafx.main.search.searchController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
      JDBC db = new JDBC();
      String u = usernameField.getText();
      String p = passwordField.getText();
      String sql = "SELECT loginPassword FROM doctor WHERE loginID = '"+u+"';";
      
      List<List> results = db.querySelect(sql, 1);
      //get item at first index(should be only item in list because ID is unique)
      List<String> list = results.get(0);
      String pw = list.get(0);
      if (p.equals(pw)){
         //good login   
        actiontarget.setText("Logging in...");
        searchController.doctorID = u;
        //start new scene
        Parent root = FXMLLoader.load(getClass().getResource("search/search.fxml"));
        Scene scene = new Scene(root, 600, 500);
        Node source = (Node)event.getSource(); 
        Stage stageClose  = (Stage) source.getScene().getWindow();
        stageClose.close();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
   
      } else {
         //bad login
        actiontarget.setText("Invalid username/password");
      }
   }
   
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      // TODO
   }   
}
