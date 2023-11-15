package src;
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
public class infrastructure {
    
    //fields of infrastructure
        
    public int infrastructureid;
    public String infrastructurename;
    public InfrastructureType infrastructuretype;
    public Status status;
    
    public ArrayList<Integer> infrastructure_idlist = new ArrayList<> ();
    public ArrayList<String> infrastructure_namelist = new ArrayList<> ();
    public ArrayList<InfrastructureType> infrastructure_typelist = new ArrayList<> ();
    public ArrayList<Status> infrastructure_statuslist = new ArrayList<> ();
    
    public infrastructure(){}
        
    public int register_infrastructure(){
        
        try{
            Connection conn;
            conn = DriverManager.getConnection(dbpath);
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("Select MAX(infrastructure_id) + 1 AS newID FROM infrastructure");
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()){
                infrastructureid = rst.getInt("newID");
            }
            
            pstmt = conn.prepareStatement("INSERT INTO infrastructure (infrastructureid, infrastructurename, infrastructuretype, status) VALUE(?,?,?,?)");
            pstmt.setInt(1,infrastructureid);
            pstmt.setString(2,infrastructurename);
            pstmt.setString(3,infrastructuretype.name());
            pstmt.setString(4,status.name());//should there be a default?
            pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
            
            System.out.println("Success");
            return 1;
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public static void main(String args[]){

    }
}
