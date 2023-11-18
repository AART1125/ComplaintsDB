/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

 /**
 *
 * @author Jalene Siazon
 */

import java.sql.*;
import java.util.*;
import java.lang.NumberFormatException;

public class Personnel {

    private String   dbpath = "jdbc:mysql://localhost:3306/dbapp?user=root&password=12345678&useTimezone=true&serverTimezone=UTC&useSSL=false";

    public int personnelid = 2000;
    public String lastname;
    public String middlename;
    public String firstname;
    public String password;
    public String dateofbirth;
    public Gender gender; 
    public String email;
    public int contactnumber;
    public Undertaking undertaking;
    public String hiredate; 
    public Position position; 

    public ArrayList<Integer> personnel_idList = new ArrayList<Integer>();

    public Personnel() {}
    
    /**
     * Constructor of the personnel object
     * @param personnelid
     * @param lastname
     * @param middlename
     * @param firstname
     * @param password
     * @param birthday
     * @param gender
     * @param email
     * @param contactnumber
     * @param undertaking
     * @param hiredate
     * @param availdays
     * @param availtime
     * @param position
     */
    public Personnel (int personnelid,String lastname,String middlename,String firstname, String password, String dateofbirth,Gender gender, String email, int contactnumber, Undertaking undertaking, String hiredate, Position position){
        this.personnelid = personnelid;
        this.lastname = lastname;
        this.middlename = middlename;
        this.firstname = firstname;
        this.password = password;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
        this.email = email;
        this.contactnumber = contactnumber;
        this.undertaking = undertaking;
        this.hiredate = hiredate;
        this.position = position;
    }

    public boolean register_personnel(){
        try {
            Connection conn = DriverManager.getConnection(dbpath);
            PreparedStatement statement = conn.prepareStatement("SELECT MAX(personnelid) + 1 as 'newPersonnelId' FROM personnel");
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                try {
                    personnelid = Integer.valueOf(results.getString("newPersonnelId"));
                } catch (NumberFormatException e){}
            }
            
            statement = conn.prepareStatement("INSERT INTO personnel VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            

            statement.setInt(1, personnelid);
            statement.setString(2, lastname);
            statement.setString(3, middlename);
            statement.setString(4, firstname);
            statement.setString(5, password);
            statement.setString(6, dateofbirth);
            statement.setString(7, gender.name());
            statement.setString(8, email);
            statement.setInt(9, contactnumber);
            statement.setString(10, undertaking.name());
            statement.setString(11, hiredate);
            statement.setString(12, position.name());

            statement.executeUpdate();
            statement.close();
            conn.close();

            return true;
        } catch (SQLException e){
            System.err.print(e);
            return false;
        }
    }

    public boolean modify_personnel(){
        try {
            Connection conn = DriverManager.getConnection(dbpath);
            PreparedStatement statement = conn.prepareStatement("UPDATE personnel SET lastname=?, middlename=?, firstname=?, dateofbirth=?, gender=?, email=?, contactnumber=?, undertaking=?, hiredate=?, availdays=?, availtime=?, position=? WHERE personnelid = ?");

            statement.setString(1, lastname);
            statement.setString(2, middlename);
            statement.setString(3, firstname);
            statement.setString(4, password);
            statement.setString(5, dateofbirth);
            statement.setString(6, gender.name());
            statement.setString(7, email);
            statement.setInt(8, contactnumber);
            statement.setString(9, undertaking.name());
            statement.setString(10, hiredate);
            statement.setString(11, position.name());
            statement.setInt(12, personnelid);

            statement.executeUpdate();
            statement.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete_personnel(){
        try {
            Connection conn = DriverManager.getConnection(dbpath);
            PreparedStatement statement = conn.prepareStatement("DELETE FROM personnel WHERE personnelid=?");
            statement.setInt(1, personnelid);

            statement.executeUpdate();
            statement.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void get_personnel(){
        try {
            Connection conn = DriverManager.getConnection(dbpath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM personnel WHERE personnelid=?");
            statement.setInt(1, personnelid);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                personnelid = results.getInt("personnelid");
                lastname = results.getString("lastname");
                middlename = results.getString("middlename");
                firstname = results.getString("firstname");
                password = results.getString("password");
                dateofbirth = results.getString("dateofbirth");
                gender = Gender.valueOf(results.getString("gender"));
                email = results.getString("email");
                contactnumber = results.getInt("contactnumber");
                undertaking = Undertaking.valueOf(results.getString("undertaking"));
                hiredate = results.getString("hiredate");
                position = Position.valueOf(results.getString("position"));
            }
            
            statement.executeUpdate();
            statement.close();
            conn.close();

        } catch (SQLException e) {

        }
    }
    
    

}
