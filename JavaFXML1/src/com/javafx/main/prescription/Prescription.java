/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.main.prescription;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author crepanich23
 */
public class Prescription {
   private final SimpleStringProperty name;
   private final SimpleStringProperty quantity;
   private final SimpleStringProperty orderID;
   private final SimpleStringProperty refill;
   private final SimpleStringProperty patientID;
   private final SimpleStringProperty rxID;
   private final SimpleStringProperty dosage;
   private final SimpleStringProperty instructions;
   private final SimpleStringProperty dateOrdered;
   private final SimpleStringProperty dateFilled;

   public Prescription(String n, String d, String q, String ref, String i, String rI, String oI,
    String pI, String dO, String dF){
      this.name = new SimpleStringProperty(n);
      this.quantity = new SimpleStringProperty(q);
      this.orderID = new SimpleStringProperty(oI);
      this.patientID = new SimpleStringProperty(pI);
      this.rxID = new SimpleStringProperty(rI);
      this.refill = new SimpleStringProperty(ref);
      this.dosage = new SimpleStringProperty(d);
      this.instructions = new SimpleStringProperty(i);
      this.dateOrdered = new SimpleStringProperty(dO);
      this.dateFilled = new SimpleStringProperty(dF);
   }
   
   public String getName(){
      return name.get();
   }
   
   public void setName(String n){
      name.set(n);
   }
   
   public String getQuantity(){
      return quantity.get();
   }
   
   public void setQuantity(String q){
      quantity.set(q);
   }
   
   public String getPatientID(){
      return patientID.get();
   }
   public void setPatientID(String p){
      patientID.set(p);
   }
   
   public String getOrderID(){
      return orderID.get();
   }
   public void setOrderID(String o){
      orderID.set(o);
   }
   
   public String getRefill(){
      return refill.get();
   }
   public void setRefill(String r){
      refill.set(r);
   }
   
   public String getRxID(){
      return rxID.get();
   }
   public void setRxID(String rI){
      rxID.set(rI);
   }
   
   public String getDosage(){
      return dosage.get();
   }
   public void setDosage(String d){
      dosage.set(d);
   }
   
   public String getInstructions(){
      return instructions.get();
   }
   public void setInstructions(String i){
      instructions.set(i);
   }
   
   public String getDateOrdered(){
      return dateOrdered.get();
   }
   public void setDateOrdered(String dO){
      dateOrdered.set(dO);
   }
   
   public String getDateFilled(){
      return dateFilled.get();
   }
   public void setDateFilled(String dF){
      dateFilled.set(dF);
   }
}
