/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.main;
//STEP 1. Import required packages
import java.sql.*;
import java.util.*;

public class JDBC {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://chrisrepanich.com/chrisrep_rx";

   //  Database credentials
   static final String USER = "chrisrep_project";
   static final String PASS = "project1";
   
   Connection conn;
   Statement stmt;
   /**
    * Construct JDBC object by trying to connect
    */
   public JDBC() {
   
}//end main
   
   /*
    * Method for select queries to db
    * String query = the query to execute
    * int columns = the number of columns selected
    */
   public List<List> querySelect(String query, int columns) {
      //initalize return value
      List<List> list = new ArrayList();
      try{
      //Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
      //Connect and execute query
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      //loop through result set
      while (rs.next()){
         //create array to add each row into
         List<String> toAdd = new ArrayList();
         //loop through row of result set and put all output into an array
         for (int i=1; i<=columns; i++){
            toAdd.add(rs.getString(i));
         }
         list.add(toAdd);
      }
      }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){
         //Handle errors for Class.forName
         e.printStackTrace();
      }finally{
         //finally block used to close resources
         try{
            if(stmt!=null)
               conn.close();
         }catch(SQLException se){
         }// do nothing
         try{
            if(conn!=null)
               conn.close();
         }catch(SQLException se){
            se.printStackTrace();
         }//end finally try
      }//end try
      //return result set
      return list;
   }//end querySelect method
   
   /*
    * Method for insert queries to db
    */
   public void queryInsert(String query){
   
      
   }
   
   /*
    * Method for update queries to db
    */
   public void queryUpdate(String query){
   
   }
   
   /*
    * Method for remove queries to db
    */
   public void queryRemove(String query){
   
   }
}//END JDBC

/*
 * CONNECTION/ QUERY EXAMPLE
 * //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      System.out.println("Creating statement...");
      
      stmt = conn.createStatement();
      String sql = "SELECT firstName, lastName, loginID FROM doctor ";
      ResultSet rs = stmt.executeQuery(sql);
       while(rs.next()) {
           String firstName = rs.getString("firstName");
           String lastName = rs.getString("lastName");
           String loginID = rs.getString("loginID");
           
           System.out.println("\nFirst Name: "+firstName+"\nLast Name: "+lastName+"\nLoginID: "+loginID+"\n");
       }
 */