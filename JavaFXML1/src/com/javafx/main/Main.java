/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author crepanich23
 */
public class Main extends Application {
   private Stage stage;
   @Override
   public void start(Stage stage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
      
      Scene scene = new Scene(root, 300, 275);
      
      stage.setTitle("User Login");
      stage.setScene(scene);
      stage.show();
      JDBC db = new JDBC();
   }
   
   /**
    * The main() method is ignored in correctly deployed JavaFX application.
    * main() serves only as fallback in case the application can not be launched
    * through deployment artifacts, e.g., in IDEs with limited FX support.
    * NetBeans ignores main().
    *
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      launch(args);
   }
}