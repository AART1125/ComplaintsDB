package src;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ObjectInputFilter.Status;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ccslearner
 */
public class Infrastructure {
    
    //fields of infrastructure
    private String dbpath = "jdbc:mysql://localhost:3306/dbapp?user=root&password=12345678&useTimezone=true&serverTimezone=UTC&useSSL=false";
        
    public int infrastructureid;
    public String infrastructurename;
    public InfrastructureType infrastructuretype; 
    public Status status;
    
    public ArrayList<Integer> infrastructure_idlist = new ArrayList<> ();
    public ArrayList<String> infrastructure_namelist = new ArrayList<> ();
    public ArrayList<InfrastructureType> infrastructure_typelist = new ArrayList<> ();
    public ArrayList<Status> infrastructure_statuslist = new ArrayList<> ();
    
    public Infrastructure () {}
        
     public int register_infrastructure(){
        
        try{
            Connection conn;
            conn = DriverManager.getConnection(dbpath);
            System.out.println("Connection Successful");
            
            PreparedStatement statement = conn.prepareStatement("Select MAX(infrastructure_id) + 1 AS newID FROM infrastructure");
            ResultSet rst = statement.executeQuery();
            while(rst.next()){
                infrastructureid = rst.getInt("newID");
            }
            
            statement = conn.prepareStatement("INSERT INTO infrastructure (infrastructureid, infrastructurename, infrastructuretype, status) VALUE(?,?,?,?)");
            statement.setInt(1,infrastructureid);
            statement.setString(2,infrastructurename);
            statement.setString(3,infrastructuretype.name());
            statement.setString(4,status.name());
            statement.executeUpdate();
            
            statement.close();
            conn.close();
            
            System.out.println("Success");
            return 1;
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public boolean delete_infrastructure(){
        try {
            Connection conn = DriverManager.getConnection(dbpath);
            PreparedStatement statement = conn.prepareStatement("DELETE FROM infrastructure i WHERE i.infrastructureid=?");
            statement.setInt(1, infrastructureid);

            statement.executeUpdate();
            
            statement.close();
            conn.close();
            
            return true;
            
        } catch (SQLException e) {
            
            return false;
            
        }
    }
    
    public boolean modify_personnel(){
        try {
            Connection conn = DriverManager.getConnection(dbpath);
            PreparedStatement statement = conn.prepareStatement("UPDATE infrastructure SET infrastructureid=?, infrastructurename=?, infrastructuretype=?, status=?");

            statement.setInt(1,infrastructureid);
            statement.setString(2,infrastructurename);
            statement.setString(3,infrastructuretype.name());
            statement.setString(4,status.name());

            statement.executeUpdate();
            
            statement.close();
            conn.close();
            
            return true;
            
        } catch (SQLException e) {
            
            return false;
            
        }
    }
    
    public void get_infrastructure(){
        try {
            Connection conn = DriverManager.getConnection(dbpath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM infrastructure i WHERE i.infrastructureid=?");
            statement.setInt(1, infrastructureid);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                infrastructureid = results.getInt("infrastructureid");
                infrastructurename = results.getString("infrastructurename");
                infrastructuretype = InfrastructureType.valueOf(results.getString("infrastructuretype"));
                status = Status.valueOf(results.getString("status"));
            }

        } catch (SQLException e) {
               
        }
    }
}