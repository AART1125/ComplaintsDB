/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amor De Guzman
 * 
 */
import java.util.ArrayList;
import java.sql.*;

public class ComplaintManager {
    
    public Complaint complaints;
    public Date dateofoffense;
    
    /*ArrayLists for lists of all categories*/
   
    ArrayList<Integer> chronologicallistofcomplaints = new ArrayList();
    ArrayList<Integer> bymemberlistofcomplaints = new ArrayList();
    ArrayList<Integer> personnelcomplaints = new ArrayList();
    ArrayList<Integer> securitypersonnelcomplaints = new ArrayList();
    ArrayList<Integer> maintainpersonnelcomplaints = new ArrayList();
    ArrayList<Integer> securityinfracomplaints = new ArrayList();
    ArrayList<Integer> maintaininfracomplaints = new ArrayList();
    ArrayList<Integer> infrastructurecomplaints = new ArrayList();
    
    String dbconnection = "jdbc:mysql://localhost:3306/dbapp?user=root&password=12345678&useTimezone=true&serverTimezone=UTC&useSSL=false";
    
    public boolean isEditable() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
          
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM complaints WHERE complaintid=?");
           pstmt.setInt(1, complaints.complaintid);
           ResultSet rst = pstmt.executeQuery();
           String status = "";
           
           while (rst.next()) {
               status = rst.getString("statusofcomplaint");
           }
                   
           if (status.compareTo(Status.F.name()) == 0) {
               return true;
           }   else {
               return false;
           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    
    public ArrayList list_by_member() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           bymemberlistofcomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM complaints ORDER BY complainant");
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               bymemberlistofcomplaints.add(rst.getInt("complaintid"));
           }
           
           pstmt.close();
           rst.close();
           conn.close();
           
           return bymemberlistofcomplaints;
           
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ArrayList list_by_priority() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           chronologicallistofcomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM complaints ORDER BY dateofcomplaint");
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               chronologicallistofcomplaints.add(rst.getInt("complaintid"));
           }
           
           pstmt.close();
           rst.close();
           conn.close();
           
           return chronologicallistofcomplaints;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ArrayList list_personnel_complaints() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           personnelcomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM personnelcomplaints ORDER BY complaintid");
           
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               personnelcomplaints.add(rst.getInt("complaintid"));            
           }
           
           pstmt.close();
           rst.close();
           conn.close();
           return personnelcomplaints;
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ArrayList list_personnel_security_complaints() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           securitypersonnelcomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM securitypersonnelcomplaints ORDER BY complaintid");
           
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               securitypersonnelcomplaints.add(rst.getInt("complaintid"));            
           }
           
           pstmt.close();
           rst.close();
           conn.close();
           return securitypersonnelcomplaints;
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ArrayList list_personnel_maintain_complaints() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           maintainpersonnelcomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM maintainpersonnelcomplaints ORDER BY complaintid");
           
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               maintainpersonnelcomplaints.add(rst.getInt("complaintid"));            
           }
           
           pstmt.close();
           rst.close();
           conn.close();
           return maintainpersonnelcomplaints;
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    
    public ArrayList list_infrastructure_complaints() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconnection);
            System.out.println("Connection Successful");
           
            infrastructurecomplaints.clear();
           
             PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM infrastructurecomplaints ORDER BY complaintid");
           
             ResultSet rst = pstmt.executeQuery();
           
            while (rst.next()) {
                infrastructurecomplaints.add(rst.getInt("complaintid"));            
            }
           
            pstmt.close();
            rst.close();
            conn.close();
            return infrastructurecomplaints;
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    
    public ArrayList list_security_infrastructure_complaints() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           securityinfracomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM securityinfracomplaints ORDER BY complaintid");
           
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               securityinfracomplaints.add(rst.getInt("complaintid"));            
           }
           
           pstmt.close();
           rst.close();
           conn.close();
           return securityinfracomplaints;
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ArrayList list_maintain_infrastructure_complaints() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           maintaininfracomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM maintaininfracomplaints ORDER BY complaintid");
           
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               maintaininfracomplaints.add(rst.getInt("complaintid"));            
           }
           
           pstmt.close();
           rst.close();
           conn.close();
           return maintaininfracomplaints;
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public int get_total_complaints() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconnection);
            System.out.println("Connection Successful");
            int number = 0;
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM complaints ORDER BY complaintid");
            ResultSet results = pstmt.executeQuery();
            
            while (results.next()) {
                number++;
            }
            
            pstmt.close();
            results.close();
            conn.close();
            return number;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
    public int get_total_personnel_complaints() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconnection);
            System.out.println("Connection Successful");
            int number = 0;
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM personnelcomplaints ORDER BY complaintid");
            ResultSet results = pstmt.executeQuery();
            
            while (results.next()) {
                number++;
            }
            
            pstmt.close();
            results.close();
            conn.close();
            return number;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
     public int get_total_infra_complaints() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconnection);
            System.out.println("Connection Successful");
            int number = 0;
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM infrastructurecomplaints ORDER BY complaintid");
            ResultSet results = pstmt.executeQuery();
            
            while (results.next()) {
                number++;
            }
            
            pstmt.close();
            results.close();
            conn.close();
            return number;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
     
     public int get_total_security_infra_complaints() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconnection);
            System.out.println("Connection Successful");
            int number = 0;
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM securityinfracomplaints ORDER BY complaintid");
            ResultSet results = pstmt.executeQuery();
            
            while (results.next()) {
                number++;
            }
            
            pstmt.close();
            results.close();
            conn.close();
            return number;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
     
    public int get_total_maintain_infra_complaints() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconnection);
            System.out.println("Connection Successful");
            int number = 0;
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM maintaininfracomplaints ORDER BY complaintid");
            ResultSet results = pstmt.executeQuery();
            
            while (results.next()) {
                number++;
            }
            
            pstmt.close();
            results.close();
            conn.close();
            return number;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
    
    public int get_total_security_personnel_complaints() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconnection);
            System.out.println("Connection Successful");
            int number = 0;
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM securitypersonnelcomplaints ORDER BY complaintid");
            ResultSet results = pstmt.executeQuery();
            
            while (results.next()) {
                number++;
            }
            
            pstmt.close();
            results.close();
            conn.close();
            return number;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
    public int get_total_maintain_personnel_complaints() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconnection);
            System.out.println("Connection Successful");
            int number = 0;
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM maintainpersonnelcomplaints ORDER BY complaintid");
            ResultSet results = pstmt.executeQuery();
            
            while (results.next()) {
                number++;
            }
            
            pstmt.close();
            results.close();
            conn.close();
            return number;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
}
