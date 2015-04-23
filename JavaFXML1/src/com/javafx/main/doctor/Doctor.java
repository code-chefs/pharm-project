/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.main.doctor;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author crepanich23
 */
public class Doctor {
   private final SimpleStringProperty firstName;
   private final SimpleStringProperty lastName;
   private final SimpleStringProperty ID;
   
   public Doctor(String fN, String lN, String i){
      this.firstName = new SimpleStringProperty(fN);
      this.lastName = new SimpleStringProperty(lN);
      this.ID = new SimpleStringProperty(i);
   }
   
   public String getFirstName(){
      return firstName.get();
   }
   
   public void setFirstName(String fN){
      firstName.set(fN);
   }
   
   public String getLastName(){
      return lastName.get();
   }
   
   public void setLastName(String lN){
      lastName.set(lN);
   }
   
   public String getID(){
      return ID.get();
   }
   
   public void setID(String i){
      ID.set(i);
   }
   
   
}
