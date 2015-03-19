/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.main.prescription;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Orion
 * Description: for working prescription orders. A single prescription order
 */
public class PrescOrder {
    private final SimpleStringProperty date;
    private final SimpleStringProperty medNum;
    private final SimpleStringProperty doctor;
    private final SimpleStringProperty doctorContact;
    private final SimpleStringProperty patName;
    private final SimpleStringProperty patContact;
    private final SimpleStringProperty pill;
    
    
    
    public PrescOrder(String date, String medNum, String doctor, String doctorContact, String patName, String patContact,
            String pill){
        this.date = new SimpleStringProperty(date);
        this.medNum = new SimpleStringProperty(medNum);
        this.doctor = new SimpleStringProperty(doctor);
        this.doctorContact = new SimpleStringProperty(doctorContact);
        this.patName = new SimpleStringProperty(patName);
        this.patContact = new SimpleStringProperty(patContact);
        this.pill = new SimpleStringProperty(pill);
    }
    
    public String getDate(){
        return date.get();
    }
    
    public String getMedNum(){
        return medNum.get();
    }
    
    public String getDoctor(){
        return doctor.get();
    }
    
    public String getDoctorContact(){
        return doctorContact.get();
    }
    
    public String getPatName(){
        return patName.get();
    }
    
    public String getPatContact(){
        return patContact.get();
    }
    
    public String getPill(){
        return pill.get();
    }
}
