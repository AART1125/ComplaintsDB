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
    public Date dateofcomplaintfiling;
    public Type typeofcomplaint;
    public Status statusofcomplaint; 
    public String description;
    
    // fields for personnel complaint
    public int adminincharge;
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
    
    public String dbconn = "jdbc:mysql://localhost:3306/dbapp?user=root&password=12345678&useTimezone=true&serverTimezone=UTC&useSSL=false";
    
    public Complaint(int complainant, String dateofcomplaintfiling, String typeofcomplaint, String personnelrequired, int complaintsubject, String description) {
        this.complaintid = 0;
        this.complainant = complainant;
        this.dateofcomplaintfiling = Date.valueOf(dateofcomplaintfiling);
        this.statusofcomplaint = Status.F; // automatically set to filed
        this.typeofcomplaint = Type.valueOf(typeofcomplaint);
        this.description = description;
        
        if (typeofcomplaint.compareTo("I") == 0) {
            this.problematicinfrastructure = complaintsubject;
            this.maintainrespondent = 0;
            this.securityrespondent = 0;
            this.typeofinfrastructurecomplaint = Personnel_Involved.valueOf(personnelrequired);
        } else if (typeofcomplaint.compareTo("P") == 0) {
            this.personnelrespondent = complaintsubject;
            this.securityincharge = 0;
            this.maintainincharge = 0;
            this.typeofpersonnelcomplaint = Personnel_Involved.valueOf(personnelrequired);
            if (typeofpersonnelcomplaint.name().compareTo("S") == 0) {
                this.securityrespondent = personnelrespondent;
                this.maintainrespondent = 0;
            } else if (typeofpersonnelcomplaint.name().compareTo("M") == 0) {
                this.maintainrespondent = personnelrespondent;
                this.securityrespondent = 0;
            }
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
            pstmt = conn.prepareStatement("INSERT INTO complaint (complaintid, complainant, dateofcomplaintfiling, typeofcomplaint, statusofcomplaint, description) VALUE (?,?,?,?,?,?)");
            pstmt.setInt(1, complaintid);
            pstmt.setInt(2, complainant);
            pstmt.setDate(3, dateofcomplaintfiling);
            pstmt.setString(4, typeofcomplaint.name());
            pstmt.setString(5, Status.F.name());            // automatically set to filed when created
            pstmt.setString(6, description);
            pstmt.executeUpdate();
            
            if (typeofcomplaint.name().compareTo("P") == 0) {
                pstmt = conn.prepareStatement("INSERT INTO personnelcomplaint (complaintid, personnelrespondent, typeofpersoncomplaint) VALUE (?, ?, ?)");
                pstmt.setInt(1, complaintid);
                pstmt.setInt(2, personnelrespondent);
                pstmt.setString(3, typeofpersonnelcomplaint.name());
                pstmt.executeUpdate();
                if (typeofpersonnelcomplaint.name().compareTo("S") == 0) {
                    pstmt = conn.prepareStatement("INSERT INTO securitypersoncomplaint (complaintid, securityrespondent) VALUE (?,?)");
                    pstmt.setInt(1, complaintid);
                    pstmt.setInt(2, securityrespondent);
                    pstmt.executeUpdate();
                } else if (typeofpersonnelcomplaint.name().compareTo("M") == 0) {
                    pstmt = conn.prepareStatement("INSERT INTO maintainpersoncomplaints (complaintid, maintainrespondent) VALUE (?,?)");
                    pstmt.setInt(1, complaintid);
                    pstmt.setInt(2, maintainrespondent);
                    pstmt.executeUpdate();
                }
            } else if (typeofcomplaint.name().compareTo("I") == 0) {
                pstmt = conn.prepareStatement("INSERT INTO infrastructurecomplaint (complaintid, problematicinfrastructure, typeofinfracomplaint) VALUE (?, ?, ?)");
                pstmt.setInt(1, complaintid);
                pstmt.setInt(2, problematicinfrastructure);
                pstmt.setString(3, typeofinfrastructurecomplaint.name());
                pstmt.executeUpdate();
                if (typeofinfrastructurecomplaint.name().compareTo("S") == 0) {
                    pstmt = conn.prepareStatement("INSERT INTO securityinfracomplaint (complaintid) VALUE (?)");
                    pstmt.setInt(1, complaintid);
                    pstmt.executeUpdate();
                } else if (typeofinfrastructurecomplaint.name().compareTo("M") == 0) {
                    pstmt = conn.prepareStatement("INSERT INTO maintaininfracomplaint (complaintid) VALUE (?)");
                    pstmt.setInt(1, complaintid);
                    System.out.println("hello");
                    pstmt.executeUpdate();
                }
            }
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
    }
    
    /*
    public int create_security_personnel_complaint() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconn);
           
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(complaintid) + 1 AS newID FROM complaint");
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                complaintid = rst.getInt("newID");
            }
            pstmt = conn.prepareStatement("INSERT INTO securitypersoncomplaint (complaintid, complainant, dateofcomplaintfiling, typeofcomplaint, statusofcomplaint, description) VALUE (?,?,?,?,?,?,?)");
            pstmt.setInt(1, complaintid);
            pstmt.setInt(2, complainant);
           
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }    
    }
    */
    
     // if complaint is deemed invalid by admin
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
                conn.close();
                return 0;
            }
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE complaint SET description=? WHERE complaintid=?");
            pstmt.setString(1, description);
            pstmt.setInt(2, complaintid);
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
    
    public int change_security_infra_complaint_type() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconn);
            
            if (statusofcomplaint.compareTo(Status.F) == 0) {
                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM securityinfracomplaint WHERE complaintid=?");  // delete first from wrong table
                pstmt.setInt(1, complaintid);
                pstmt.executeUpdate();
            
                pstmt = conn.prepareStatement("INSERT INTO maintaininfracomplaint (complaintid) VALUE (?)");                // place into proper table
                pstmt.setInt(1, complaintid);
                pstmt.executeUpdate();
            
                pstmt = conn.prepareStatement("UPDATE complaint SET typeofinfracomplaint=? WHERE complaintid=?");           // change type of most original complaint
                pstmt.setString(1, "M");
                pstmt.setInt(1, complaintid);
                pstmt.executeUpdate();
            
                pstmt.close();
                conn.close();
                return 1;
            } else {
                conn.close();
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public int change_maintain_infra_complaint_type() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconn);
            
            if (statusofcomplaint.compareTo(Status.F) == 0) {
                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM maintaininfracomplaint WHERE complaintid=?");
                pstmt.setInt(1, complaintid);
                pstmt.executeUpdate();
            
                pstmt = conn.prepareStatement("INSERT INTO securityinfracomplaint (complaintid) VALUE (?)");
                pstmt.setInt(1, complaintid);
                pstmt.executeUpdate();
            
                pstmt = conn.prepareStatement("UPDATE complaint SET typeofinfracomplaint=? WHERE complaintid=?");
                pstmt.setString(1, "S");
                pstmt.setInt(1, complaintid);
                pstmt.executeUpdate();
            
                pstmt.close();
                conn.close();
                return 1;
            } else {
                conn.close();
                return 0;
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public int update_complaint_status(String newstatus) {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconn);
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE complaint SET statusofcomplaint=? WHERE complaintid=?");
            pstmt.setString(1, newstatus);
            pstmt.setInt(2, complaintid);
             
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public int assign_security_personnel(int personnelassigned) {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconn);
           
           this.personnelrespondent = personnelassigned;
           this.securityincharge = personnelassigned;
           PreparedStatement pstmt = conn.prepareStatement("UPDATE reviewedsecuritypersoncomplaint SET securityincharge=? WHERE complaintid=?");
           pstmt.setInt(1, personnelrespondent);
           pstmt.setInt(2, complaintid);
           
           pstmt.executeUpdate();
           
           if (update_complaint_status("R") == 1) {
               return 1;
           }
           return 0;
        } catch (Exception e) {
           System.out.println(e.getMessage());
           return 0;
        }
    }
    
    public int assign_maintain_personnel(int personnelassigned) {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconn);
           
           this.personnelrespondent = personnelassigned;
           this.maintainincharge = personnelassigned;
           PreparedStatement pstmt = conn.prepareStatement("UPDATE reviewedmaintainpersoncomplaint SET maintainincharge=? WHERE complaintid=?");
           pstmt.setInt(1, personnelrespondent);
           pstmt.setInt(2, complaintid);
           
           pstmt.executeUpdate();
           
           if (update_complaint_status("R") == 1) {
               return 1;
           }
           return 0;
        } catch (Exception e) {
           System.out.println(e.getMessage());
           return 0;
        }
    }
    
    
    public int get_complaint() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconn);
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid, complainant, dateofcomplaintfiling, typeofcomplaint, statusofcomplaint, description FROM complaint WHERE complaintid=?");
            pstmt.setInt(1, complaintid);
            ResultSet rst = pstmt.executeQuery();
            
            while (rst.next()) {
                complainant = rst.getInt("complainant");
                dateofcomplaintfiling = rst.getDate("dateofcomplaintfiling");
                typeofcomplaint = Type.valueOf(rst.getString("typeofcomplaint"));
                statusofcomplaint = Status.valueOf(rst.getString("statusofcomplaint"));
                description = rst.getString("description");
            }
            
            if (typeofcomplaint.name().compareTo("P") == 0) {
                pstmt = conn.prepareStatement("SELECT personnelrespondent, typeofpersoncomplaint FROM personnelcomplaint WHERE complaintid=?");
                pstmt.setInt(1, complaintid);
                rst = pstmt.executeQuery();
                
                while (rst.next()) {
                    personnelrespondent = rst.getInt("personnelrespondent");
                    typeofpersonnelcomplaint = Personnel_Involved.valueOf(rst.getString("typeofpersoncomplaint"));                   
                } 
                
                if (typeofpersonnelcomplaint.name().compareTo("S") == 0) {
                    pstmt = conn.prepareStatement("SELECT securityrespondent FROM securitypersoncomplaint WHERE complaintid=?");
                    pstmt.setInt(1, complaintid);
                    
                    rst = pstmt.executeQuery();
                    problematicinfrastructure = 0;
                    while (rst.next()) {
                        securityrespondent = rst.getInt("securityrespondent");
                        maintainrespondent = 0;
                    }
                } else if (typeofpersonnelcomplaint.name().compareTo("M") == 0) {
                    pstmt = conn.prepareStatement("SELECT maintainrespondent FROM maintainpersoncomplaint WHERE complaintid=?");
                    pstmt.setInt(1, complaintid);
                    
                    rst = pstmt.executeQuery();
                    personnelrespondent = 0;
                    securityrespondent = 0;
                    maintainrespondent = 0;
                    while (rst.next()) {
                        maintainrespondent = rst.getInt("maintainrespondent");
                    }
                }
            } else if (typeofcomplaint.name().compareTo("I") == 0) {
                pstmt = conn.prepareStatement("SELECT problematicinfrastructure, FROM infrastructurecomplaint WHERE complaintid=?");
                pstmt.setInt(1, complaintid);
                
                rst = pstmt.executeQuery();
                
                while (rst.next()) {
                    problematicinfrastructure = rst.getInt("problematicinfrastructure");
                }
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
