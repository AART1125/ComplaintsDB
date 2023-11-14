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
    private String dbPath = "";
    
    public boolean doesMemberExist(int memberID, Member memberinfo){
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM member WHERE memberid=?");
            statement.setInt(1, memberID);
            ResultSet results = statement.executeQuery();

            if (results.next()){
                String lname = results.getString("lastname");
                String fname = results.getString("firstname");
                String dateofbirth = results.getString("dateofbirth");
                String gender = results.getString("gender");
                Gender gen = Gender.valueOf(gender);
                String email = results.getString("email");
                int contactnum = results.getInt("contactnum");
                int unitnum = results.getInt("unitnum");
                memberinfo = new Member(memberID, lname, fname, dateofbirth, gen, email, contactnum, unitnum);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean addMember(String lastname, String firstname, String dateofbirth, Gender gender, String email, int contactnum, int unitnum){
        int baseId = 1000;

        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT MAX(memberId) + 1 as newMemberId FROM member");
            ResultSet results = statement.executeQuery();

            while(results.next()){
                baseId = results.getInt("newMemberId");
            }

            Member newMember = new Member(baseId, lastname, firstname, dateofbirth, gender, email, contactnum, unitnum);

            statement = conn.prepareStatement("INSERT INTO member VALUES(?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setInt(1, newMember.getMemberid());
            statement.setString(2, newMember.getLastname());
            statement.setString(3, newMember.getFirstname());
            statement.setString(4, newMember.getDateofbirth());
            statement.setString(5, newMember.getGender());
            statement.setString(6, newMember.getEmail());
            statement.setInt(7, newMember.getContactnumber());
            statement.setInt(8, newMember.getUnitnumber());

            statement.executeUpdate();
            conn.close();

            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public boolean updateMember(int memberId, String lastname, String firstname, String dateofbirth, Gender gender, String email, int contactnum, int unitnum){
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("UPDATE member SET lastname=?, firstname=?, dateofbirth=?, gender=?, email=?, contactnum=?, unitnum=?  WHERE memberId = ?");

            statement.setString(1, lastname);
            statement.setString(2, firstname);
            statement.setString(3, dateofbirth);
            statement.setString(4, gender.name());
            statement.setString(5, email);
            statement.setInt(6, contactnum);
            statement.setInt(7, unitnum);
            statement.setInt(8, memberId);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteMember(int memberId){
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("DELETE FROM member m WHERE m.memberid = ?");

            statement.setInt(1, memberId);

            statement.executeUpdate();
            conn.close();
            
            return true;
        } catch (SQLException e){
            return false;
        }

    }

    public ArrayList<Member> list_members(){
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

    public ArrayList<Member> list_male_members(){
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

    public ArrayList<Member> list_female_members(){
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
