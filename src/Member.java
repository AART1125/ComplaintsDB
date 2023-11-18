package src;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.ArrayList;
import java.lang.NumberFormatException;

/**
 *
 * @author Aaron Ace Toledo
 */
public class Member {

    private String   dbpath = "jdbc:mysql://localhost:3306/dbapp?user=root&password=12345678&useTimezone=true&serverTimezone=UTC&useSSL=false";

    public int      memberid = 1000;//PK
    public String   lastname;
    public String   middlename;
    public String   firstname;
    public String   password;
    public String   dateofbirth;
    public Gender   gender;
    public String   email;
    public String      contactnumber;
    public int      unitnumber;

    public ArrayList<Integer> member_idList = new ArrayList<Integer>();
    
    public Member() {}

    /**
     * Constructor of the member object
     *
     * @param memberid unique member id
     * @param lastname last name of the member
     * @param firstname first name of the member
     * @param dateofbirth birth date of the member
     * @param gender gender of the member
     * @param email email address of the member
     * @param contactnumber contact number of the member
     * @param unitnumber unit number the member is residing in
     */
    public Member(int memberid, String lastname, String middlename, String firstname, String password, String dateofbirth, Gender gender, String email, String contactnumber, int unitnumber) {
        this.memberid = memberid;
        this.lastname = lastname;
        this.middlename = middlename;
        this.firstname = firstname;
        this.password = password;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
        this.email = email;
        this.contactnumber = contactnumber;
        this.unitnumber = unitnumber;
    }

    public boolean register_member(){

        try {
            Connection conn = DriverManager.getConnection(dbpath);
            PreparedStatement statement = conn.prepareStatement("SELECT MAX(memberId) + 1 as newMemberId FROM member");
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                try {
                    memberid = Integer.valueOf(results.getString("newMemberId"));
                } catch (NumberFormatException e) {}
            }

            statement = conn.prepareStatement("INSERT INTO member VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setInt(1, memberid);
            statement.setString(2, lastname);
            statement.setString(3, middlename);
            statement.setString(4, firstname);
            statement.setString(5, password);
            statement.setString(6, dateofbirth);
            statement.setString(7, gender.name());
            statement.setString(8, email);
            statement.setString(9, contactnumber);
            statement.setInt(10, unitnumber);

            statement.executeUpdate();
            statement.close();
            conn.close();

            return true;
        } catch (SQLException e){
            return false;
        }
    }

    public boolean modify_member(){
        try {
            Connection conn = DriverManager.getConnection(dbpath);
            PreparedStatement statement = conn.prepareStatement("UPDATE member SET lastname=?, middlename=?, firstname=?, dateofbirth=?, password=?, gender=?, email=?, contactnumber=?, unitnumber=?  WHERE memberid = ?");

            statement.setString(1, lastname);
            statement.setString(2, middlename);
            statement.setString(3, firstname);
            statement.setString(4, dateofbirth);
            statement.setString(5, password);
            statement.setString(6, gender.name());
            statement.setString(7, email);
            statement.setString(8, contactnumber);
            statement.setInt(9, unitnumber);
            statement.setInt(10, memberid);


            statement.executeUpdate();
            statement.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete_member(){
        try {
            Connection conn = DriverManager.getConnection(dbpath);
            PreparedStatement statement = conn.prepareStatement("DELETE FROM member WHERE memberid=?");
            statement.setInt(1, memberid);

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
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM member WHERE memberid=?");
            statement.setInt(1, memberid);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                memberid = results.getInt("memberid");
                lastname = results.getString("lastname");
                middlename = results.getString("middlename");
                firstname = results.getString("firstname");
                password = results.getString("password");
                dateofbirth = results.getString("dateofbirth");
                gender = Gender.valueOf(results.getString("gender"));
                email = results.getString("email");
                contactnumber = results.getString("contactnumber");
                unitnumber = results.getInt("unitnumber");
            }
            
            statement.executeUpdate();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            
        }
    }
    
    public String getDateOfBirth(){
        return dateofbirth;
    }

}
