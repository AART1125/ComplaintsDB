/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package DB_Complaints_src;

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
   
    public ArrayList<Integer> bydatelistofcomplaints = new ArrayList<>();
    public ArrayList<Integer> listbymonthcomplaints = new ArrayList<>();
    public ArrayList<Integer> byprioritylistofcomplaints = new ArrayList<>();
    public ArrayList<Integer> onememberscomplaints = new ArrayList<>();
    public ArrayList<Integer> bymemberlistofcomplaints = new ArrayList<>();
    public ArrayList<Integer> personnelcomplaints = new ArrayList<>();
    public ArrayList<Integer> securitypersonnelcomplaints = new ArrayList<>();
    public ArrayList<Integer> maintainpersonnelcomplaints = new ArrayList<>();
    public ArrayList<Integer> securityinfracomplaints = new ArrayList<>();
    public ArrayList<Integer> maintaininfracomplaints = new ArrayList<>();
    public ArrayList<Integer> infrastructurecomplaints = new ArrayList<>();
    public ArrayList<Integer> listforoneinfrastructurecomplaints = new ArrayList<>();
    public ArrayList<Integer> criterialist = new ArrayList<>();
    public ArrayList<Complaint> complaintsfromcriterialist = new ArrayList<>();
    public ArrayList<Integer> filedcomplaints = new ArrayList<>();
    
    public String query;
    
    String dbconnection = "jdbc:mysql://localhost:3306/dbapp?user=root&password=12345678&useTimezone=true&serverTimezone=UTC&useSSL=false";
    
    public static void main(String[] args){
        ComplaintManager m = new ComplaintManager();
        m.list_for_one_member(1005);
        System.out.println(m.onememberscomplaints.get(0));
    }
    
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
    
    
    
    public void list_by_member() {
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
           
           
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void list_for_one_member(int memberid) {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");  
           
           onememberscomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM complaint WHERE complainant=? AND statusofcomplaint='F' ORDER BY complaintid");
           pstmt.setInt(1, memberid);
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               onememberscomplaints.add(rst.getInt("complaintid"));
           }
           
           pstmt.close();
           rst.close();
           conn.close();
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
    
    public void list_personnel_maintain_complaints() {
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
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void list_infrastructure_complaints() {
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
            
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
          
        }
    }
    
    
    public void list_security_infrastructure_complaints() {
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
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void list_maintain_infrastructure_complaints() {
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
           
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        }
    }
    
    public void list_filed_commplaints() {
        try {
           Connection conn;
           conn = DriverManager.getConnection(dbconnection);
           System.out.println("Connection Successful");
           
           filedcomplaints.clear();
           
           PreparedStatement pstmt = conn.prepareStatement("SELECT complaintid FROM complaint WHERE statusofcomplaint='F' ORDER BY complaintid");
           
           ResultSet rst = pstmt.executeQuery();
           
           while (rst.next()) {
               filedcomplaints.add(rst.getInt("complaintid"));            
           }
           
           pstmt.close();
           rst.close();
           conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /*Filtering by:
        status - String status (F/R/S/U)
        type   - String type (P/I)
        year   - String dateforyear (year is extracted in the function)
        month  - String dateformonth (month is extracted in the function)
        member - String complainant
    
    */
    public void list_by_criteria(String status, String type, String dateforyear, String dateformonth, String complainant) {
        query = "";
        if(status.compareTo("") == 0 && type.compareTo("") == 0 && dateforyear.compareTo("") == 0 && dateformonth.compareTo("") == 0 && complainant.compareTo("") == 0 ){
            query = "SELECT * FROM complaint";
        } else {
            query = "SELECT * FROM complaint WHERE";
                                                                                
            Date datemonth;
            Date dateyear;
            int month, year;
            
            if (dateforyear.compareTo("") == 1) {
                dateyear = Date.valueOf(dateforyear);
                year = dateyear.toLocalDate().getYear();
            } else {
                year = 0;
            }
            
            if (dateformonth.compareTo("") == 1) {
                datemonth = Date.valueOf(dateformonth);
                month = datemonth.toLocalDate().getMonthValue();
            } else {
                month = 0;
            }
            
            if (month != 0 && year != 0){ // if month and year were not required
                query += " MONTH(dateofresolution)="+month+" AND YEAR(dateofresolution)="+year;
            }   else if (month == 0 && year != 0) { // if year was required
                query += " YEAR(dateofresolution)="+year;
            }  else if (month != 0 && year == 0) {
                query += " MONTH(dateofcomplaint)="+month+"AND YEAR(dateofresolution)="+year; // if month (and consequentially, year) was required
            }
            
            if (status.compareTo("") == 1 && type.compareTo("") == 0 && complainant.compareTo("") == 0) { // only status
                query += "AND statusofcomplaint="+ status;
            }
            
            if (status.compareTo("") == 0 && type.compareTo("") == 1 && complainant.compareTo("") == 0) { // only type
                query += "AND typeofcomplaint=" + type;
            }
            
            if (status.compareTo("") == 0 && type.compareTo("") == 0 && complainant.compareTo("") == 1) { // only complainant
                query += "AND complainant=" +complainant;
            }
            
            if (status.compareTo("") == 1 && type.compareTo("") == 1 && complainant.compareTo("") == 1) { // if three filters
                query += "AND statusofcomplaint"+status+"AND typeofcomplaint="+type+"AND complainant="+complainant;
            }
            
            if (status.compareTo("") == 1 && type.compareTo("") == 1 && complainant.compareTo("") == 0) { // if two filters (status and type)
                query += "AND statusofcomplaint"+status+"AND typeofcomplaint="+type;
            }
            
            if (status.compareTo("") == 0 && type.compareTo("") == 1 && complainant.compareTo("") == 1) { // if two filters (type and complainant)
                query += "AND typeofcomplaint="+type+"AND complainant="+complainant;
            }
            
            if (status.compareTo("") == 1 && type.compareTo("") == 0 && complainant.compareTo("") == 1) { // if two filters (status and complainant)
                query += "AND statusofcomplaint="+type+"AND complainant="+complainant;
            }
        }
    }

        
    public void generate_report() {
        int complaintid, complainant;
        String dateofcomplaintfiling;
        String typeofcomplaint;
        String statusofcomplaint; 
        String description;
        
        complaintsfromcriterialist.clear();
        
        try {
            Connection conn = DriverManager.getConnection(dbconnection);
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rst = pstmt.executeQuery();
            
            while(rst.next()) {
                complaintid = rst.getInt("complaintid");
                complainant = rst.getInt("complainant");
                dateofcomplaintfiling = rst.getString("dateofcomplaint");
                typeofcomplaint = rst.getString("typeofcomplaint");
                statusofcomplaint = rst.getString("statusofcomplaint");
                description = rst.getString("description");
                
                complaintsfromcriterialist.add(new Complaint(complaintid, complainant, dateofcomplaintfiling, typeofcomplaint, statusofcomplaint, description));
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
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

