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
    
    String query;
    ArrayList<Integer> orderedbyid = new ArrayList<>();
    ArrayList<Integer> orderedbydate = new ArrayList<>();
    ArrayList<Integer> orderedbyspecificmonth = new ArrayList<>();
    ArrayList<Integer> orderedbyspecificyear = new ArrayList<>();
    ArrayList<Integer> orderedbytype = new ArrayList<>();
    ArrayList<Integer> criterialist = new ArrayList<>();
    ArrayList<Resolution> resolutionbylist = new ArrayList<>();
    
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
    
    
    public void list_by_criteria(String dateformonth, String dateforyear, int personnel) {
        String query = "";
        if(dateformonth.compareTo("") == 0 && dateforyear.compareTo("") == 0 && personnel == 0){
            query = "SELECT * FROM resolutions";
        } else {
            query = "SELECT * FROM resolutions WHERE";
            
            int month = 0, year = 0;
            Date datemonth, dateyear;
            
            if (dateformonth.compareTo("") != 0) {
                datemonth = Date.valueOf(dateformonth);
                month = datemonth.toLocalDate().getMonthValue();
            }
            
            if (dateforyear.compareTo("") != 0) {
                dateyear = Date.valueOf(dateforyear);
                year = dateyear.toLocalDate().getYear();
            }
            
            if(month != 0 && year != 0){
                query += " MONTH(dateofresolution)="+month+" AND YEAR(dateofresolution)="+year;
            } else if (month != 0 && year == 0) {
                query += " MONTH(dateofresolution)="+month;
            }  else if (month != 0 && year == 0) {
                query += " YEAR(dateofresolution)="+year;
            }
            
            if (personnel != 0) {
                if (month != 0 && year != 0) {
                    query += " AND personnelincharge="+personnel;
                } else {
                    query += " WHERE personnelincharge="+personnel;
                }
            }
        }
    }
        
    public void generate_report() {
        int resolutionid, personnelincharge;
        String dateofresolution;
        String description;
        
        resolutionbylist.clear();
        
        try {
            Connection conn = DriverManager.getConnection(dbconnection);
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rst = pstmt.executeQuery();
            
            while(rst.next()) {
                resolutionid = rst.getInt("resolutionid");
                personnelincharge = rst.getInt("personnelincharge");
                dateofresolution = rst.getString("dateofresolution");
                description = rst.getString("description");
                
                resolutionbylist.add(new Resolution(resolutionid, personnelincharge, dateofresolution, description));
            }
        } catch(SQLException e) {}
                System.err.println(e.getMessage());
    }
         
        
}
