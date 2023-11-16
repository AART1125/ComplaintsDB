/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Complaints_src;

 /**
 *
 * @author Jalene Siazon
 */

import java.sql.*;
import java.util.*;

public class Personnel {

    public String   dbpath = "";

    public int personnelid;
    public String lastname;
    public String middlename;
    public String firstname;
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
    public Personnel (int personnelid,String lastname,String middlename,String firstname,String dateofbirth,Gender gender, String email, int contactnumber, Undertaking undertaking, String hiredate, Position position){
        this.personnelid = personnelid;
        this.lastname = lastname;
        this.middlename = middlename;
        this.firstname = firstname;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
        this.email = email;
        this.contactnumber = contactnumber;
        this.undertaking = undertaking;
        this.hiredate = hiredate;
        this.position = position;
    }

    public boolean register_personnel(){
        int baseId = 1000;
        try {
            Connection conn = DriverManager.getConnection(dbpath);
            PreparedStatement statement = conn.prepareStatement("SELECT MAX(personnelId) + 1 as newPersonnelId FROM personnel");
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                baseId = Integer.valueOf(results.getString("newPersonnelId"));
            }

            statement = conn.prepareStatement("INSERT INTO member VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setInt(1, baseId);
            statement.setString(2, lastname);
            statement.setString(3, middlename);
            statement.setString(4, firstname);
            statement.setString(5, dateofbirth);
            statement.setString(6, gender.name());
            statement.setString(7, email);
            statement.setInt(8, contactnumber);
            statement.setString(9, undertaking.name());
            statement.setString(10, hiredate);
            statement.setString(13, position.name());

            statement.executeUpdate();
            statement.close();
            conn.close();

            return true;
        } catch (SQLException e){
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
            statement.setString(4, dateofbirth);
            statement.setString(5, gender.name());
            statement.setString(6, email);
            statement.setInt(7, contactnumber);
            statement.setString(8, undertaking.name());
            statement.setString(9, hiredate);
            statement.setString(12, position.name());
            statement.setInt(13, personnelid);

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
            PreparedStatement statement = conn.prepareStatement("DELETE FROM personnel p WHERE p.personnelid=?");
            statement.setInt(1, personnelid);

            statement.executeUpdate();
            statement.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void get_member(){
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
                dateofbirth = results.getString("dateofbirth");
                gender = Gender.valueOf(results.getString("gender"));
                email = results.getString("email");
                contactnumber = results.getInt("contactnumber");
                undertaking = Undertaking.valueOf(results.getString("undertaking"));
                hiredate = results.getString("hiredate");
                position = Position.valueOf(results.getString("position"));
            }

        } catch (SQLException e) {

        }
    }

}
