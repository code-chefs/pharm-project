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
   private final SimpleStringProperty doctor;
   private final SimpleStringProperty medID;
   private final SimpleStringProperty history;
   private final SimpleStringProperty lastVisit;
   

        public Patient(String fName, String lName, String doc, String id, String lVisit, String hist) {
            this.lastVisit = new SimpleStringProperty(lVisit);
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.history = new SimpleStringProperty(hist);
            this.doctor = new SimpleStringProperty(doc);
            this.medID = new SimpleStringProperty(id);
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
       
        public String getMedID() {
            return medID.get();
        }
           public void setMedID(String id) {
            medID.set(id);
        }
           
        public String getLastVisit() {
            return lastVisit.get();
        }
        public void setLastVisit(String lVisit) {
            lastVisit.set(lVisit);
        }  
        
        public String getDoctor() {
            return doctor.get();
        }
        public void setDoctor(String doc) {
            doctor.set(doc);
        }
        
        public String getHistory() {
            return history.get();
        }
        public void setHistory(String hist) {
            history.set(hist);
        }
       
    }
