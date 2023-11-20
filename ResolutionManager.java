/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ccslearner
 */

import java.sql.*;
import java.util.ArrayList;

public class ResolutionManager {
    
    public Resolution resolution;
    
    ArrayList<Integer> orderedbyid = new ArrayList<>();
    ArrayList<Integer> orderedbydate = new ArrayList<>();
    ArrayList<Integer> orderedbyspecificmonth = new ArrayList<>();
    ArrayList<Integer> orderedbyspecificyear = new ArrayList<>();
    ArrayList<Integer> orderedbytype = new ArrayList<>();
    ArrayList<Integer> criterialist = new ArrayList<>();
    
    String dbconnection = "jdbc:mysql://localhost:3306/dbapp?user=root&password=12345678&useTimezone=true&serverTimezone=UTC&useSSL=false";
    
    public ResolutionManager() {}
    
    public void list_by_id() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           orderedbyid.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT resolutionid FROM resolutions ORDER BY resolutionid");
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               orderedbyid.add(rst.getInt("resolutionid"));
           }
           
           pstmt.close();
           rst.close();
           conn.close();
           
           //return orderedbyid;
          
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //return null;
        }
    }
    
    public void list_by_date() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           orderedbydate.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT resolutionid FROM resolutions ORDER BY dateofresolution");
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               orderedbydate.add(rst.getInt("resolutionid"));
           }
           
           pstmt.close();
           rst.close();
           conn.close();
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void list_by_specific_month(String datechosen) {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           orderedbyspecificmonth.clear();
           
           Date datereceived = Date.valueOf(datechosen);
           int month = datereceived.toLocalDate().getMonthValue();
           int year = datereceived.toLocalDate().getYear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT resolutionid FROM resolutions WHERE MONTH(dateofresolution)=? AND YEAR(dateofresolution)=? ORDER BY dateofresolution");
           pstmt.setInt(1, month);
           pstmt.setInt(2, year);
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               orderedbyspecificmonth.add(rst.getInt("resolutionid"));
           }
           
           pstmt.close();
           rst.close();
           conn.close();
          
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void list_by_specific_year(String datechosen) {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           orderedbyspecificyear.clear();
           
           Date datereceived = Date.valueOf(datechosen);
           int month = datereceived.toLocalDate().getMonthValue();
           int year = datereceived.toLocalDate().getYear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT resolutionid FROM resolutions WHERE MONTH(dateofresolution)=? AND YEAR(dateofresolution)=? ORDER BY dateofresolution");
           pstmt.setInt(1, month);
           pstmt.setInt(2, year);
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               orderedbyspecificmonth.add(rst.getInt("resolutionid"));
           }
           
           pstmt.close();
           rst.close();
           conn.close();
          
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void list_by_criteria(String dateformonth, String dateforyear, String typeP, String typeI, String typeS, String typeM) {
        String query = "";
        boolean personSecurityNeeded = false, personMaintainNeeded = false, infraSecurityNeeded = false, infraMaintainNeeded = false, noCriteria = false;
        if(dateformonth.compareTo("") == 0 || dateforyear.compareTo("") == 0 || typeP.compareTo("") == 0 || typeI.compareTo("") == 0 || typeS.compareTo("") == 0 || typeM.compareTo("") == 0){
            query = "SELECT resolutionid FROM resolutions";
            noCriteria = true;
        } else {
            Date datemonth = Date.valueOf(dateformonth);
            int month = datemonth.toLocalDate().getMonthValue();
            
            Date dateyear = Date.valueOf(dateforyear);
            int year = dateyear.toLocalDate().getYear();
            
            if(month != 0 && year != 0){
                query += " WHERE MONTH(dateofresolution)="+month+" AND YEAR(dateofresolution)="+year;
            } else if (month != 0 && year == 0) {
                query += " WHERE MONTH(dateofresolution)="+month;
            }  else if (month != 0 && year == 0) {
                query += " WHERE YEAR(dateofresolution)="+year;
            }
            
            if (typeS.compareTo("") == 1) {         // if all security chosen
                if (typeP.compareTo("") == 1) {     // check if needing personnel security
                    personSecurityNeeded = true;
                }
                
                if (typeI.compareTo("") == 1) {     // check if needing infrastructure security
                    infraSecurityNeeded = true;
                }
            }
            
            if (typeM.compareTo("") == 1) {         // if all maintenance chosen
                if (typeP.compareTo("") == 1) {     // check if needing personnel maintenance
                    personMaintainNeeded = true;                  
                }
                
                if (typeI.compareTo("") == 1) {     // check if needing infrastructure maintenance
                    infraMaintainNeeded = true;
                }
            }
        
        }
         
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
                
           criterialist.clear();
            
           PreparedStatement pstmt;
           ResultSet rst;
                
           if (noCriteria) { // then simple query collecting all resolutionsids is called
                pstmt = conn.prepareStatement(query);
                rst = pstmt.executeQuery();
                    
                while (rst.next()) {
                    criterialist.add(rst.getInt("resolutionid"));
                }
            }
                
            if (personSecurityNeeded) { // check if needed, then add to list
                pstmt = conn.prepareStatement("SELECT resolutionid FROM resolutions r JOIN securitypersonresolution s ON s.resolutionid = r.resolutionid" + query);
                rst = pstmt.executeQuery();
                
                while (rst.next()) {
                    criterialist.add(rst.getInt("resolutionid"));
                }
            }
            
            if (infraSecurityNeeded) {  // check if needed, then add to list
                pstmt = conn.prepareStatement("SELECT resolutionid FROM resolutions r JOIN securityinfraresolution s ON s.resolutionid = r.resolutionid" + query);
                rst = pstmt.executeQuery();
                
                while (rst.next()) {
                    criterialist.add(rst.getInt("resolutionid"));
                }
            }
            
            if (personMaintainNeeded) { // check if needed, then add to list
                pstmt = conn.prepareStatement("SELECT resolutionid FROM resolutions r JOIN maintainpersonresolution s ON s.resolutionid = r.resolutionid" + query);
                rst = pstmt.executeQuery();
                
                while (rst.next()) {
                    criterialist.add(rst.getInt("resolutionid"));
                }
            }
            
            if (infraMaintainNeeded) {  // check if needed, then add to list
                pstmt = conn.prepareStatement("SELECT resolutionid FROM resolutions r JOIN maintaininfraresolution s ON s.resolutionid = r.resolutionid" + query);
                rst = pstmt.executeQuery();
                
                while (rst.next()) {
                    criterialist.add(rst.getInt("resolutionid"));
                }
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }    
    }
}
