package src;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ccslearner
 */
public class Member {

    public String   dbpath = "";

    public int      memberid;//PK
    public String   lastname;
    public String   middlename;
    public String   firstname;
    public String   dateofbirth;
    public Gender   gender;
    public String   email;
    public int      contactnumber;
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
    public Member(int memberid, String lastname, String middlename, String firstname, String dateofbirth, Gender gender, String email, int contactnumber, int unitnumber) {
        this.memberid = memberid;
        this.lastname = lastname;
        this.middlename = middlename;
        this.firstname = firstname;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
        this.email = email;
        this.contactnumber = contactnumber;
        this.unitnumber = unitnumber;
    }

    public boolean register_member(){
        int baseId = 1000;

        try {
            Connection conn = DriverManager.getConnection(dbpath);
            PreparedStatement statement = conn.prepareStatement("SELECT MAX(memberId) + 1 as newMemberId FROM member");
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                baseId = Integer.valueOf(results.getString("newMemberId"));
            }

            memberid = baseId;

            statement = conn.prepareStatement("INSERT INTO member VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setInt(1, memberid);
            statement.setString(2, lastname);
            statement.setString(3, middlename);
            statement.setString(4, firstname);
            statement.setString(5, dateofbirth);
            statement.setString(6, gender.name());
            statement.setString(7, email);
            statement.setInt(8, contactnumber);
            statement.setInt(9, unitnumber);

            statement.executeUpdate();
            statement.close();
            conn.close();

            return true;
        } catch (SQLException e){
            return false;
        }
    }

    public boolean modify_member(String lastname, String middlename, String firstname, String dateofbirth, Gender gender, String email, int contactnumber, int unitnumber){
        try {
            Connection conn = DriverManager.getConnection(dbpath);
            PreparedStatement statement = conn.prepareStatement("UPDATE member SET lastname=?, middlename=?, firstname=?, dateofbirth=?, gender=?, email=?, contactnum=?, unitnum=?  WHERE memberid = ?");

            if (!lastname.equals("")) {
                this.lastname = lastname;
            }
            
            if (!middlename.equals("")) {
                this.middlename = middlename;
            }

            if (!firstname.equals("")) {
                this.firstname = firstname;
            }

            if (!dateofbirth.equals("")) {
                this.dateofbirth = dateofbirth;
            }

            if (!gender.name().equals("")) {
                this.gender = gender;
            }

            if (!email.equals("")) {
                this.email = email;
            }

            if (contactnumber != -1) {
                this.contactnumber = contactnumber;
            }

            if (unitnumber != -1) {
                this.unitnumber = unitnumber;
            }

            statement.setString(1, this.lastname);
            statement.setString(2, this.middlename);
            statement.setString(3, this.firstname);
            statement.setString(4, this.dateofbirth);
            statement.setString(5, this.gender.name());
            statement.setString(6, this.email);
            statement.setInt(7, this.contactnumber);
            statement.setInt(8, this.unitnumber);


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
            PreparedStatement statement = conn.prepareStatement("DELETE FROM member m WHERE m.memberid=?");
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
                dateofbirth = results.getString("dateofbirth");
                gender = Gender.valueOf(results.getString("gender"));
                email = results.getString("email");
                contactnumber = results.getInt("contactnumber");
                unitnumber = results.getInt("unitnumber");
            }

        } catch (SQLException e) {
            
        }
    }

}
