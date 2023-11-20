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

public class Resolution {
    
    public int resolutionid;
    public int personnelinvolved; // can be either securityincharge, maintainincharge, or adminincharge
    public String description;
    
    // for specific type resolutions
    public int complaintid;
    
    public String dbconn = "\"jdbc:mysql://localhost:3306/INSERTDBNAMEHERE?user=root&password=12345678&useTimezone=true&serverTimezone=UTC&useSSL=false\";";
    
    public Resolution(int complaintid, int personnelinvolved, String description) {
        this.resolutionid = 0;
        this.complaintid = complaintid;
        this.personnelinvolved = personnelinvolved;
        this.description = description;
    }
    
    /*
    generic resolutions will instead be created AFTER the specialized ones
    public int create_resolution() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconn);
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(resolutionid) + 1 AS newID FROM resolutions");
            ResultSet rst = pstmt.executeQuery();
            
            while (rst.next()) {
                resolutionid = rst.getInt("newID");
            }
            
            pstmt = conn.prepareStatement("INSERT INTO resolutions (resolutionid, personnelinvolved, description) VALUE (?,?,?)");
            pstmt.setInt(1, resolutionid);
            pstmt.setInt(2, personnelinvolved);
            pstmt.setString(3, description);
            
            pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    */
    
    public int create_security_personnel_resolution() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconn);
            
           PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(resolutionid) + 1 AS newID FROM resolutions");
           ResultSet rst = pstmt.executeQuery();
            
           while (rst.next()) {
               resolutionid = rst.getInt("newID");
           }
            
           pstmt = conn.prepareStatement("INSERT INTO securitypersonresolution (resolutionid, complaintid, adminincharge) VALUE (?,?,?)");
           pstmt.setInt(1, resolutionid);
           pstmt.setInt(2, complaintid);
           pstmt.setInt(3, personnelinvolved);            
           pstmt.executeUpdate();
           
           pstmt = conn.prepareStatement("INSERT INTO resolutions (resolutionid, personnelinvolved, description) VALUE (?,?,?)");
           pstmt.setInt(1, resolutionid);
           pstmt.setInt(2, personnelinvolved);
           pstmt.setString(3, description);
           pstmt.executeUpdate();
           
           pstmt.close();
           conn.close();
           return 1;
           
       } catch (Exception e) {
           System.out.println(e.getMessage());
           return 0;
        }
    }
    
    public int create_maintain_personnel_resolution() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconn);
            
           PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(resolutionid) + 1 AS newID FROM resolutions");
           ResultSet rst = pstmt.executeQuery();
            
           while (rst.next()) {
               resolutionid = rst.getInt("newID");
           }
            
           pstmt = conn.prepareStatement("INSERT INTO maintainpersonresolution (resolutionid, complaintid, adminincharge) VALUE (?,?,?)");
           pstmt.setInt(1, resolutionid);
           pstmt.setInt(2, complaintid);
           pstmt.setInt(3, personnelinvolved);            
           pstmt.executeUpdate();
           
           pstmt = conn.prepareStatement("INSERT INTO resolutions (resolutionid, personnelinvolved, description) VALUE (?,?,?)");
           pstmt.setInt(1, resolutionid);
           pstmt.setInt(2, personnelinvolved);
           pstmt.setString(3, description);
           pstmt.executeUpdate();
           
           pstmt.close();
           conn.close();
           return 1;
           
       } catch (Exception e) {
           System.out.println(e.getMessage());
           return 0;
        }
    }
    
    public int create_security_infra_resolution() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconn);
            
           PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(resolutionid) + 1 AS newID FROM resolutions");
           ResultSet rst = pstmt.executeQuery();
            
           while (rst.next()) {
               resolutionid = rst.getInt("newID");
           }
            
           pstmt = conn.prepareStatement("INSERT INTO securityinfraresolution (resolutionid, complaintid, securityincharge) VALUE (?,?,?)");
           pstmt.setInt(1, resolutionid);
           pstmt.setInt(2, complaintid);
           pstmt.setInt(3, personnelinvolved);            
           pstmt.executeUpdate();
           
           pstmt = conn.prepareStatement("INSERT INTO resolutions (resolutionid, personnelinvolved, description) VALUE (?,?,?)");
           pstmt.setInt(1, resolutionid);
           pstmt.setInt(2, personnelinvolved);
           pstmt.setString(3, description);
           pstmt.executeUpdate();
           
           pstmt.close();
           conn.close();
           return 1;
       } catch (Exception e) {
           System.out.println(e.getMessage());
           return 0;
        }
    }
    
    public int create_maintain_infra_resolutiion() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconn);
            
           PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(resolutionid) + 1 AS newID FROM resolutions");
           ResultSet rst = pstmt.executeQuery();
            
           while (rst.next()) {
               resolutionid = rst.getInt("newID");
           }
            
           pstmt = conn.prepareStatement("INSERT INTO maintaininfraresolution (resolutionid, complaintid, securityincharge) VALUE (?,?,?)");
           pstmt.setInt(1, resolutionid);
           pstmt.setInt(2, complaintid);
           pstmt.setInt(3, personnelinvolved);            
           pstmt.executeUpdate();
           
           pstmt = conn.prepareStatement("INSERT INTO resolutions (resolutionid, personnelinvolved, description) VALUE (?,?,?)");
           pstmt.setInt(1, resolutionid);
           pstmt.setInt(2, personnelinvolved);
           pstmt.setString(3, description);
           pstmt.executeUpdate();
           
           pstmt.close();
           conn.close();
           return 1;
       } catch (Exception e) {
           System.out.println(e.getMessage());
           return 0;
       }
    }
    
    public int get_resolution_record() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconn);
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT personnelinvolved, description FROM resolutions WHERE resolutionid=?");
           pstmt.setInt(1, resolutionid);
           ResultSet rst = pstmt.executeQuery();
         
           while (rst.next()) {
             personnelinvolved = rst.getInt("personnelinvolved");
             description = rst.getString("description");
           }
          
           pstmt.close();
           conn.close();
           return 1;
        } catch (Exception e) {
           System.out.println(e.getMessage());
           return 0;
        }
    }
}
