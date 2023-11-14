package src;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ccslearner
 */
import java.sql.*;
import java.util.ArrayList;
public class PeopleManager {
    private String           dbPath = "";

    public ArrayList<Member> member_list = list_members();
    public ArrayList<Member> member_male_list = list_male_members();
    public ArrayList<Member> member_female_list = list_female_members();

    public boolean memberExists(int memberId){
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM member WHERE memberid = ?");
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                statement.close();
                conn.close();
                return true;
            } else {
                statement.close();
                conn.close();
                return false;
            }

        } catch (SQLException e){
            return false;
        }
    }

    private ArrayList<Member> list_members(){
        ArrayList<Member> memberlist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM member");
            ResultSet memberinfo = statement.executeQuery();

            String lname, fname, dateofbirth, gender, email;
            int contactnum,unitnum,memberId;
            
            while (memberinfo.next()) {
                memberId = memberinfo.getInt("memberid");
                lname = memberinfo.getString("lastname");
                fname = memberinfo.getString("firstname");
                dateofbirth = memberinfo.getString("dateofbirth");
                gender = memberinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = memberinfo.getString("email");
                contactnum = memberinfo.getInt("contactnum");
                unitnum = memberinfo.getInt("unitnum");

                memberlist.add(new Member(memberId, lname, fname, dateofbirth, gen, email, contactnum, unitnum));
            }

            conn.close();
            
            return memberlist;
        } catch (SQLException e){
            return null;
        }
    }

    private ArrayList<Member> list_male_members(){
        ArrayList<Member> memberlist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM member WHERE gender = 'M'");
            ResultSet memberinfo = statement.executeQuery();

            String lname, fname, dateofbirth, gender, email;
            int contactnum,unitnum,memberId;
            
            while (memberinfo.next()) {
                memberId = memberinfo.getInt("memberid");
                lname = memberinfo.getString("lastname");
                fname = memberinfo.getString("firstname");
                dateofbirth = memberinfo.getString("dateofbirth");
                gender = memberinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = memberinfo.getString("email");
                contactnum = memberinfo.getInt("contactnum");
                unitnum = memberinfo.getInt("unitnum");

                memberlist.add(new Member(memberId, lname, fname, dateofbirth, gen, email, contactnum, unitnum));
            }

            conn.close();
            
            return memberlist;
        } catch (SQLException e){
            return null;
        }
    }

    private ArrayList<Member> list_female_members(){
        ArrayList<Member> memberlist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM member WHERE gender = 'F'");
            ResultSet memberinfo = statement.executeQuery();

            String lname, fname, dateofbirth, gender, email;
            int contactnum,unitnum,memberId;
            
            while (memberinfo.next()) {
                memberId = memberinfo.getInt("memberid");
                lname = memberinfo.getString("lastname");
                fname = memberinfo.getString("firstname");
                dateofbirth = memberinfo.getString("dateofbirth");
                gender = memberinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = memberinfo.getString("email");
                contactnum = memberinfo.getInt("contactnum");
                unitnum = memberinfo.getInt("unitnum");

                memberlist.add(new Member(memberId, lname, fname, dateofbirth, gen, email, contactnum, unitnum));
            }

            conn.close();
            
            return memberlist;
        } catch (SQLException e){
            return null;
        }
    }
}
