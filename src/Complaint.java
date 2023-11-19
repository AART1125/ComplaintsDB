package src;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amor De Guzman
 */
import java.util.ArrayList;
import java.sql.*;

public class Complaint {
    
    // fields of complaint
    public int complaintid;
    public int complainant;
    public String dateofcomplaintfiling;
    public Type typeofcomplaint;
    public Status statusofcomplaint; 
    public String description;
    
    // fields for personnel complaint
    public int adminincharge; // to be changed in update
    public int personnelrespondent;
    public Personnel_Involved typeofpersonnelcomplaint;
    // fields for security personnel complaint
    public int securityrespondent;
    // fields for maintainance personnel complaint
    public int maintainrespondent;
    
    // fields for infrastructure complaints
    public int problematicinfrastructure;
    public Personnel_Involved typeofinfrastructurecomplaint;
    // fields for security infra complaint
    public int securityincharge;
    // fields for maintainance infra complaint
    public int maintainincharge;
    
    private String dbconn = "jdbc:mysql://localhost:3306/INSERTDBNAMEHERE?user=root&password=12345678&useTimezone=true&serverTimezone=UTC&useSSL=false";
    
    
    public Complaint(int complaintid, int complainant, String dateofcomplaintfiling, String dateofoffense, String typeofcomplaint, String personnelrequired, int complaintsubject, String statusofcomplaint, String description) {
        this.complaintid = complaintid;
        this.complainant = complainant;
        this.dateofcomplaintfiling = dateofcomplaintfiling;
        this.statusofcomplaint = Status.valueOf(statusofcomplaint);
        
        //Type.valueOf("P")
        
        if (typeofcomplaint.compareTo("I") == 0) {
            this.problematicinfrastructure = complaintsubject;
            this.maintainrespondent = 0;
            this.securityrespondent = 0;
            this.typeofinfrastructurecomplaint = Personnel_Involved.valueOf(personnelrequired);
        } else if (typeofcomplaint.compareTo("P") == 0) {
            this.personnelrespondent = complaintsubject;
            this.typeofpersonnelcomplaint = Personnel_Involved.valueOf(personnelrequired);
            this.problematicinfrastructure = 0;
        }
        
    // THESE FIELDS ARE FOR UNDER REVIEW COMPLAINTS
    // fields for personnel complaint
        this.adminincharge = 0;   
    // fields for security infra complaint
        this.securityincharge = 0;
    // fields for maintainance infra complaint
        this.maintainincharge = 0;
    }
    
    public int create_complaint() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconn);
           
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(complaintid) + 1 AS newID FROM complaint");
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                complaintid = rst.getInt("newID");
            }
            pstmt = conn.prepareStatement("INSERT INTO complaint (complaintid, complainant, dateofcomplaintfiling, dateofoffense, typeofcomplaint, statusofcomplaint, description) VALUE (?,?,?,?,?,?,?)");
            pstmt.setInt(1, complaintid);
            pstmt.setInt(2, complainant);
            pstmt.setDate(3, dateofcomplaintfiling);
            pstmt.setDate(4 , dateofoffense);
            pstmt.setString(5, typeofcomplaint.name());
            pstmt.setString(6, Status.F.name());            // automatically set to filed when created
            pstmt.setString(7, description);
            pstmt.executeUpdate();
            
            if (typeofcomplaint.name().compareTo("P") == 0) {
                pstmt = conn.prepareStatement("INSERT INTO personnelcomplaint (complaintid, personnelrespondent, typeofpersonnelcomplaint) VALUE (?, ?, ?)");
                pstmt.setInt(1, complaintid);
                pstmt.setInt(2, personnelrespondent);
                pstmt.setString(3, typeofpersonnelcomplaint.name());
            } else if (typeofcomplaint.name().compareTo("I") == 0) {
                pstmt = conn.prepareStatement("INSERT INTO infrastructurecomplaint (complaintid, personnelrespondent, typeofpersonnelcomplaint) VALUE (?, ?, ?)");
                pstmt.setInt(1, complaintid);
                pstmt.setInt(2, securityrespondent);
                pstmt.setString(3, typeofinfrastructurecomplaint.name());
            }
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
    }
    
    
    public int create_security_personnel_complaint() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconn);
           
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(complaintid) + 1 AS newID FROM complaint");
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                complaintid = rst.getInt("newID");
            }
            pstmt = conn.prepareStatement("INSERT INTO complaint (complaintid, complainant, dateofcomplaintfiling, dateofoffense, typeofcomplaint, statusofcomplaint, description) VALUE (?,?,?,?,?,?,?)");
            pstmt.setInt(1, complaintid);
            pstmt.setInt(2, complainant);
            pstmt.setDate(3, dateofcomplaintfiling);
            pstmt.setDate(4 , dateofoffense);
            pstmt.setString(5, typeofcomplaint.name());
            pstmt.setString(6, statusofcomplaint.name());
            pstmt.setString(7, description);
            
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
    }
    
     // if complaint is deemed invalid by personnel
    public int delete_complaint() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconn);
            PreparedStatement pstmt;
            
            if (typeofcomplaint.name().compareTo("P") == 0) { // if complaint is personnel
                if (typeofpersonnelcomplaint.name().compareTo("S") == 0) { // and if personnel complaint is security
                    pstmt = conn.prepareStatement("DELETE FROM securitypersonnelcomplaint WHERE complaintid=?"); // delete from corresponding table
                    pstmt.setInt(1, complaintid);
                    pstmt.executeUpdate();
                    
                } else if (typeofpersonnelcomplaint.name().compareTo("M") == 0) {
                    pstmt = conn.prepareStatement("DELETE FROM maintainpersonnelcomplaint WHERE complaintid=?"); // delete from corresponding table
                    pstmt.setInt(1, complaintid);
                    pstmt.executeUpdate();
                }
                pstmt = conn.prepareStatement("DELETE FROM personnelcomplaint WHERE complaintid=?");             // delete from corresponding table
                pstmt.setInt(1, complaintid);
                pstmt.executeUpdate();
            } else if (typeofcomplaint.name().compareTo("I") == 0) {
                if (typeofinfrastructurecomplaint.name().compareTo("S") == 0) {
                    pstmt = conn.prepareStatement("DELETE FROM securityinfracomplaint WHERE complaintid=?");     // delete from corresponding table
                    pstmt.setInt(1, complaintid);
                    pstmt.executeUpdate();
                    
                } else if (typeofinfrastructurecomplaint.name().compareTo("M") == 0) {
                    pstmt = conn.prepareStatement("DELETE FROM maintaininfracomplaint WHERE complaintid=?");     // delete from corresponding table
                    pstmt.setInt(1, complaintid);
                    pstmt.executeUpdate();
                }
                pstmt = conn.prepareStatement("DELETE FROM infrastructurecomplaint WHERE complaintid=?");        // delete from corresponding table
                pstmt.setInt(1, complaintid);
                pstmt.executeUpdate();
            }
            
            pstmt = conn.prepareStatement("DELETE FROM complaint WHERE complaintid=?"); // delete from the main complaint table
            pstmt.setInt(1, complaintid);
            pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public int update_complaint_desc(String description) {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconn);
            
            if (statusofcomplaint.name().compareTo("F") == 1) { // if not filed, DO NOT ALLOW to change
                return 0;
            }
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE complaint SET description=?");
            pstmt.setString(1, description);
            pstmt.executeUpdate();
            
            // close
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public int update_complaint_status(String newstatus) {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconn);
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE complaint SET statusofcomplaint=?");
            pstmt.setString(1, newstatus);
             
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public int get_complaint() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconn);
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid, complainant, dateofcomplaintfiling, dateofoffense, typeofcomplaint, statusofcomplaint, description FROM complaint WHERE complaintid=?");
            pstmt.setInt(1, complaintid);
            ResultSet rst = pstmt.executeQuery();
            
            while (rst.next()) {
                complainant = rst.getInt("complainant");
                dateofcomplaintfiling = rst.getDate("dateofcomplaintfiling");
                dateofoffense = rst.getDate("dateofoffense");
                typeofcomplaint = Type.valueOf(rst.getString("typeofcomplaint"));
                statusofcomplaint = Status.valueOf(rst.getString("statusofcomplaint"));
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
