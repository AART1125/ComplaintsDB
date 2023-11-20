package DB_Complaints_src;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.*;

/**
 *
 * @author ccslearner
 */
public class Infrastructure {
    
    //fields of infrastructure
    private String   dbpath = "jdbc:mysql://localhost:3306/dbapp?user=root&password=12345678&useTimezone=true&serverTimezone=UTC&useSSL=false";
        
    public int infrastructureid;
    public String infrastructurename;
    public InfrastructureType infrastructuretype; 
    public Status status;
    public ArrayList<Infrastructure> list = new ArrayList<>();    
    
    
    public Infrastructure () {}
    
    public Infrastructure (int infrastructureid, String infrastructurename, InfrastructureType type, Status status) {
        this.infrastructureid = infrastructureid;
        this.infrastructurename = infrastructurename;
        this.infrastructuretype = infrastructuretype;
        this.status = status;
    }
    
        
     public boolean register_infrastructure(){
        
        try{
            Connection conn;
            conn = DriverManager.getConnection(dbpath);
            
            PreparedStatement statement = conn.prepareStatement("Select MAX(infrastructureid) + 1 AS newID FROM infrastructure");
            ResultSet rst = statement.executeQuery();
            if (rst.next()){
                try {
                    infrastructureid = rst.getInt("newID");
                } catch (NumberFormatException e){}
            }
            
            statement = conn.prepareStatement("INSERT INTO infrastructure VALUES(?, ?, ?, ?)");
            statement.setInt(1,infrastructureid);
            statement.setString(2,infrastructurename);
            statement.setString(3,infrastructuretype.name());
            statement.setString(4,status.name());
            
            statement.executeUpdate();
            statement.close();
            conn.close();
            
            return true;
            
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean delete_infrastructure(){
        try {
            Connection conn = DriverManager.getConnection(dbpath);
            PreparedStatement statement = conn.prepareStatement("DELETE FROM infrastructure WHERE infrastructureid=?");
            statement.setInt(1, infrastructureid);

            statement.executeUpdate();
            
            statement.close();
            conn.close();
            
            return true;
            
        } catch (SQLException e) {
            
            return false;
            
        }
    }
    
    public boolean modify_infrastructure(){
        try {
            Connection conn = DriverManager.getConnection(dbpath);
            PreparedStatement statement = conn.prepareStatement("UPDATE infrastructure SET infrastructurename=?, infrastructuretype=?, status=? WHERE infrastructureid=?");

            
            statement.setString(1,infrastructurename);
            statement.setString(2,infrastructuretype.name());
            statement.setString(3,status.name());
            statement.setInt(4,infrastructureid);

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
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM infrastructure WHERE infrastructureid=?");
            statement.setInt(1, infrastructureid);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                infrastructureid = results.getInt("infrastructureid");
                infrastructurename = results.getString("infrastructurename");
                infrastructuretype = InfrastructureType.valueOf(results.getString("infrastructuretype"));
                status = Status.valueOf(results.getString("status"));
            }
            
            statement.close();
            conn.close();
           

        } catch (SQLException e) {
               
        }
    }
    
    public void list_infrastructure(){
        try{
            Connection conn = DriverManager.getConnection(dbpath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM infrastructure");
            ResultSet results = statement.executeQuery();
            
            String name; 
            InfrastructureType type;
            Status status;
            int id;
            
            while(results.next()){
                id = results.getInt("infrastructureid");
                name = results.getString("infrastructurename");
                type = InfrastructureType.valueOf(results.getString("infrastructuretype"));
                status = Status.valueOf(results.getString("status"));
                
                this.list.add(new Infrastructure(id, name, type, status));
            }
            
            statement.close();
            conn.close();
            
        } catch (SQLException e){
            
        }
    }
}