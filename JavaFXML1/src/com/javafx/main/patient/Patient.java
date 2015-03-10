/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.main.patient;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author crepanich23
 */
public class Patient {
   private final SimpleStringProperty firstName;
   private final SimpleStringProperty lastName;
   private final SimpleStringProperty email;
   private final SimpleStringProperty lastVisit;
   private final SimpleStringProperty history;
   private final SimpleStringProperty allergies;
   private final SimpleStringProperty dob;
   private final SimpleStringProperty phoneNumber;
   private final SimpleStringProperty IDnum;
   

        public Patient(String fName, String lName, String email, String last, 
         String hist, String all, String dobs , String pn, String id) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
            this.lastVisit = new SimpleStringProperty(last);
            this.history = new SimpleStringProperty(hist);
            this.allergies = new SimpleStringProperty(all);
            this.dob = new SimpleStringProperty(dobs);
            this.phoneNumber = new SimpleStringProperty(pn);
            this.IDnum = new SimpleStringProperty(id);
        }

        public String getFirstName() {
            return firstName.get();
        }
        public void setFirstName(String fName) {
            firstName.set(fName);
        }
       
        public String getLastName() {
            return lastName.get();
        }
        public void setLastName(String fName) {
            lastName.set(fName);
        }
       
        public String getEmail() {
            return email.get();
        }
        public void setEmail(String fName) {
            email.set(fName);
        }
        
        public String getPhoneNumber() {
            return phoneNumber.get();
        }
        public void setPhoneNumber(String num) {
            phoneNumber.set(num);
        }
        
        public String getLastVisit() {
            return lastVisit.get();
        }
        public void setLastVisit(String lv) {
            lastVisit.set(lv);
        }
        
        public String getHistory() {
            return history.get();
        }
        public void setHistory(String hist) {
            history.set(hist);
        }
        
        public String getIDnum() {
            return IDnum.get();
        }
        public void setIDnum(String id) {
            IDnum.set(id);
        }
        
        public String getAllergies() {
            return allergies.get();
        }
        public void setAllergies(String all) {
            allergies.set(all);
        }
        
        public String getDOB() {
            return dob.get();
        }
        public void setDOB(String d) {
            dob.set(d);
        }
       
    }
