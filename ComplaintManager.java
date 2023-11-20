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
   
    ArrayList<Integer> bydatelistofcomplaints = new ArrayList<>();
    ArrayList<Integer> listbymonthcomplaints = new ArrayList<>();
    ArrayList<Integer> byprioritylistofcomplaints = new ArrayList<>();
    ArrayList<Integer> onememberscomplaints = new ArrayList<>();
    ArrayList<Integer> bymemberlistofcomplaints = new ArrayList<>();
    ArrayList<Integer> personnelcomplaints = new ArrayList<>();
    ArrayList<Integer> securitypersonnelcomplaints = new ArrayList<>();
    ArrayList<Integer> maintainpersonnelcomplaints = new ArrayList<>();
    ArrayList<Integer> securityinfracomplaints = new ArrayList<>();
    ArrayList<Integer> maintaininfracomplaints = new ArrayList<>();
    ArrayList<Integer> infrastructurecomplaints = new ArrayList<>();
    ArrayList<Integer> listforoneinfrastructurecomplaints = new ArrayList<>();
    
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
    
    
    
    public ArrayList<Integer> list_by_member() {
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
    
    public ArrayList<Integer> list_for_one_member(int memberid) {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");  
           
           onememberscomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM complaints WHERE complainant=? ORDER BY complaintid");
           pstmt.setInt(1, memberid);
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               onememberscomplaints.add(rst.getInt("complaintid"));
           }
           
           pstmt.close();
           rst.close();
           conn.close();
           return onememberscomplaints;
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Integer> list_by_priority() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           byprioritylistofcomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM complaints ORDER BY complaintid");
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               byprioritylistofcomplaints.add(rst.getInt("complaintid"));
           }
           
           pstmt.close();
           rst.close();
           conn.close();
           
           return byprioritylistofcomplaints;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Integer> list_by_date() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           bydatelistofcomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM complaints ORDER BY dateofoffense");
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               bydatelistofcomplaints.add(rst.getInt("complaintid"));
           }
           
           pstmt.close();
           rst.close();
           conn.close();
           
           return bydatelistofcomplaints;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Integer> list_for_one_month(String dateselected) {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           listbymonthcomplaints.clear();
           
           Date datereceived = Date.valueOf(dateselected);
           int month = datereceived.toLocalDate().getMonthValue();
           int year = datereceived.toLocalDate().getYear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM complaint WHERE MONTH(dateofcomplaint)=? AND YEAR(dateofcomplaint)=? ORDER BY dateofcomplaint");
           pstmt.setInt(1, month);
           pstmt.setInt(2, year);
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               listbymonthcomplaints.add(rst.getInt("complaintid"));            
           }
           
           pstmt.close();
           rst.close();
           conn.close();
           return listbymonthcomplaints;
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    public ArrayList<Integer> list_for_one_year(String dateselected) {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           listbymonthcomplaints.clear();
           
           Date datereceived = Date.valueOf(dateselected);
           int year = datereceived.toLocalDate().getYear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM complaint WHERE YEAR(dateofcomplaint)=? ORDER BY dateofcomplaint");
           pstmt.setInt(1, year);
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               listbymonthcomplaints.add(rst.getInt("complaintid"));            
           }
           
           pstmt.close();
           rst.close();
           conn.close();
           return listbymonthcomplaints;
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    public ArrayList<Integer> list_per_infrastructure_complaints(int infrastructurementioned) {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           listforoneinfrastructurecomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM infrastructurecomplaint WHERE problematicinfrastructure=? ORDER BY problematicinfrastructure");
           pstmt.setInt(1, infrastructurementioned);
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               listforoneinfrastructurecomplaints.add(rst.getInt("complaintid"));            
           }
           
           pstmt.close();
           rst.close();
           conn.close();
           return listforoneinfrastructurecomplaints;
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    } 
    
    public ArrayList<Integer> list_personnel_complaints() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           personnelcomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM personnelcomplaint ORDER BY complaintid");
           
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
    
    
    public ArrayList<Integer> list_personnel_security_complaints() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           securitypersonnelcomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM securitypersoncomplaint ORDER BY complaintid");
           
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
    
    public ArrayList<Integer> list_personnel_maintain_complaints() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           maintainpersonnelcomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM maintainpersoncomplaints ORDER BY complaintid");
           
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
    
    
    public ArrayList<Integer> list_infrastructure_complaints() {
        try {
            Connection conn;
            conn = DriverManager.getConnection(dbconnection);
            System.out.println("Connection Successful");
           
            infrastructurecomplaints.clear();
           
             PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM infrastructurecomplaint ORDER BY complaintid");
           
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
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM securityinfracomplaint ORDER BY complaintid");
           
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
    
    public ArrayList<Integer> list_maintain_infrastructure_complaints() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           maintaininfracomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM maintaininfracomplaint ORDER BY complaintid");
           
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
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM complaint ORDER BY complaintid");
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
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM personnelcomplaint ORDER BY complaintid");
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
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM infrastructurecomplaint ORDER BY complaintid");
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
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM securityinfracomplaint ORDER BY complaintid");
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
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM maintaininfracomplaint ORDER BY complaintid");
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
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM securitypersoncomplaint ORDER BY complaintid");
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
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM maintainpersoncomplaints ORDER BY complaintid");
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
