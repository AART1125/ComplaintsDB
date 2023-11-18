package src;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron Ace Toledo
 * @author Jalene Siazon
 */
import java.sql.*;
import java.util.ArrayList;
public class PeopleRecords {
    private String           dbPath = "jdbc:mysql://localhost:3306/dbapp?user=root&password=12345678&useTimezone=true&serverTimezone=UTC&useSSL=false";

    public ArrayList<Member> member_list = list_members();
    public ArrayList<Member> member_male_list = list_male_members();
    public ArrayList<Member> member_female_list = list_female_members();

    public ArrayList<Personnel> personnel_list = list_personnel();
    public ArrayList<Personnel> personnel_security_list = list_security_personnel();
    public ArrayList<Personnel> personnel_admin_list = list_admin_personnel();
    public ArrayList<Personnel> personnel_maintenance_list = list_maintenance_personnel();
    public ArrayList<Personnel> personnel_male_list = list_male_personnel();
    public ArrayList<Personnel> personnel_female_list = list_female_personnel();
    
    public PeopleRecords() {}
    
    public boolean memberExists(int memberId){
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM member WHERE memberid = ?");
            statement.setInt(1, memberId);
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

            String lname, mname, fname, password, dateofbirth, gender, email, contactnum;
            int unitnum,memberId;
            
            while (memberinfo.next()) {
                memberId = memberinfo.getInt("memberid");
                lname = memberinfo.getString("lastname");
                mname = memberinfo.getString("middlename");
                fname = memberinfo.getString("firstname");
                password = memberinfo.getString("password");
                dateofbirth = memberinfo.getDate("dateofbirth").toLocalDate().toString();
                gender = memberinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = memberinfo.getString("email");
                contactnum = memberinfo.getString("contactnumber");
                unitnum = memberinfo.getInt("unitnumber");

                memberlist.add(new Member(memberId, lname, mname, fname, password, dateofbirth, gen, email, contactnum, unitnum));
            }

            conn.close();
            
            return memberlist;
        } catch (SQLException e){
            System.err.print(e);
            return null;
        }
    }

    private ArrayList<Member> list_male_members(){
        ArrayList<Member> memberlist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM member WHERE gender = 'M'");
            ResultSet memberinfo = statement.executeQuery();

            String lname, mname, fname, password, dateofbirth, gender, email, contactnum;
            int unitnum,memberId;
            
            while (memberinfo.next()) {
                memberId = memberinfo.getInt("memberid");
                lname = memberinfo.getString("lastname");
                mname = memberinfo.getString("middlename");
                fname = memberinfo.getString("firstname");
                password = memberinfo.getString("password");
                dateofbirth = memberinfo.getString("dateofbirth");
                gender = memberinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = memberinfo.getString("email");
                contactnum = memberinfo.getString("contactnum");
                unitnum = memberinfo.getInt("unitnum");

                memberlist.add(new Member(memberId, lname, mname, fname, password, dateofbirth, gen, email, contactnum, unitnum));
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

            String lname, mname, fname, password, dateofbirth, gender, email, contactnum;
            int unitnum,memberId;
            
            while (memberinfo.next()) {
                memberId = memberinfo.getInt("memberid");
                lname = memberinfo.getString("lastname");
                mname = memberinfo.getString("middlename");
                fname = memberinfo.getString("firstname");
                password = memberinfo.getString("password");
                dateofbirth = memberinfo.getString("dateofbirth");
                gender = memberinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = memberinfo.getString("email");
                contactnum = memberinfo.getString("contactnum");
                unitnum = memberinfo.getInt("unitnum");

                memberlist.add(new Member(memberId, lname, mname, fname, password, dateofbirth, gen, email, contactnum, unitnum));
            }

            conn.close();
            
            return memberlist;
        } catch (SQLException e){
            return null;
        }
    }

    public boolean personnelExists(int personnelId){
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM personnel WHERE personnelid = ?");
            statement.setInt(1, personnelId);
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

    public ArrayList<Personnel> list_personnel(){
        ArrayList<Personnel> personnellist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM personnel");
            ResultSet personnelinfo = statement.executeQuery();

            String lname, mname, fname, dateofbirth, gender, email, undertaking, hiredate, position;
            int contactnum, personnelid;
            
            while (personnelinfo.next()) {
                personnelid = personnelinfo.getInt("personnelid");
                lname = personnelinfo.getString("lastname");
                mname = personnelinfo.getString("middlename");
                fname = personnelinfo.getString("firstname");
                dateofbirth = personnelinfo.getString("dateofbirth");
                gender = personnelinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = personnelinfo.getString("email");
                contactnum = personnelinfo.getInt("contactnum");
                undertaking = personnelinfo.getString("undertaking");
                Undertaking undertakingValue = Undertaking.valueOf(undertaking);
                hiredate = personnelinfo.getString("hiredate");
                position = personnelinfo.getString("position");
                Position positionValue = Position.valueOf(position);

                personnellist.add(new Personnel(personnelid, lname, mname, fname, dateofbirth, gen, email, contactnum, undertakingValue, hiredate, positionValue));

            }

            conn.close();
            
            return personnellist;
        } catch (SQLException e){
            return null;
        }
    }

    public ArrayList<Personnel> list_security_personnel(){
        ArrayList<Personnel> personnellist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM personnel WHERE position = 'S'");
            ResultSet personnelinfo = statement.executeQuery();

            String lname, mname, fname, dateofbirth, gender, email, undertaking, hiredate, position;
            int contactnum, personnelid;
            
            while (personnelinfo.next()) {
                personnelid = personnelinfo.getInt("personnelid");
                lname = personnelinfo.getString("lastname");
                mname = personnelinfo.getString("middlename");
                fname = personnelinfo.getString("firstname");
                dateofbirth = personnelinfo.getString("dateofbirth");
                gender = personnelinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = personnelinfo.getString("email");
                contactnum = personnelinfo.getInt("contactnum");
                undertaking = personnelinfo.getString("undertaking");
                Undertaking undertakingValue = Undertaking.valueOf(undertaking);
                hiredate = personnelinfo.getString("hiredate");
                position = personnelinfo.getString("position");
                Position positionValue = Position.valueOf(position);

                personnellist.add(new Personnel(personnelid, lname, mname, fname, dateofbirth, gen, email, contactnum, undertakingValue, hiredate, positionValue));

            }

            conn.close();
            
            return personnellist;
        } catch (SQLException e){
            return null;
        }
    }

    public ArrayList<Personnel> list_admin_personnel(){
        ArrayList<Personnel> personnellist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM personnel WHERE position = 'A'");
            ResultSet personnelinfo = statement.executeQuery();

            String lname, mname, fname, dateofbirth, gender, email, undertaking, hiredate, position;
            int contactnum, personnelid;
            
            while (personnelinfo.next()) {
                personnelid = personnelinfo.getInt("personnelid");
                lname = personnelinfo.getString("lastname");
                mname = personnelinfo.getString("middlename");
                fname = personnelinfo.getString("firstname");
                dateofbirth = personnelinfo.getString("dateofbirth");
                gender = personnelinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = personnelinfo.getString("email");
                contactnum = personnelinfo.getInt("contactnum");
                undertaking = personnelinfo.getString("undertaking");
                Undertaking undertakingValue = Undertaking.valueOf(undertaking);
                hiredate = personnelinfo.getString("hiredate");
                position = personnelinfo.getString("position");
                Position positionValue = Position.valueOf(position);

                personnellist.add(new Personnel(personnelid, lname, mname, fname, dateofbirth, gen, email, contactnum, undertakingValue, hiredate, positionValue));

            }

            conn.close();
            
            return personnellist;
        } catch (SQLException e){
            return null;
        }
    }

    public ArrayList<Personnel> list_maintenance_personnel(){
        ArrayList<Personnel> personnellist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM personnel WHERE position = 'M'");
            ResultSet personnelinfo = statement.executeQuery();

            String lname, mname, fname, dateofbirth, gender, email, undertaking, hiredate, position;
            int contactnum, personnelid;
            
            while (personnelinfo.next()) {
                personnelid = personnelinfo.getInt("personnelid");
                lname = personnelinfo.getString("lastname");
                mname = personnelinfo.getString("middlename");
                fname = personnelinfo.getString("firstname");
                dateofbirth = personnelinfo.getString("dateofbirth");
                gender = personnelinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = personnelinfo.getString("email");
                contactnum = personnelinfo.getInt("contactnum");
                undertaking = personnelinfo.getString("undertaking");
                Undertaking undertakingValue = Undertaking.valueOf(undertaking);
                hiredate = personnelinfo.getString("hiredate");
                position = personnelinfo.getString("position");
                Position positionValue = Position.valueOf(position);

                personnellist.add(new Personnel(personnelid, lname, mname, fname, dateofbirth, gen, email, contactnum, undertakingValue, hiredate, positionValue));

            }

            conn.close();
            
            return personnellist;
        } catch (SQLException e){
            return null;
        }
    }

    public ArrayList<Personnel> list_male_personnel(){
        ArrayList<Personnel> personnellist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM personnel WHERE gender = 'M'");
            ResultSet personnelinfo = statement.executeQuery();

            String lname, mname, fname, dateofbirth, gender, email, undertaking, hiredate, position;
            int contactnum, personnelid;
            
            while (personnelinfo.next()) {
                personnelid = personnelinfo.getInt("personnelid");
                lname = personnelinfo.getString("lastname");
                mname = personnelinfo.getString("middlename");
                fname = personnelinfo.getString("firstname");
                dateofbirth = personnelinfo.getString("dateofbirth");
                gender = personnelinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = personnelinfo.getString("email");
                contactnum = personnelinfo.getInt("contactnum");
                undertaking = personnelinfo.getString("undertaking");
                Undertaking undertakingValue = Undertaking.valueOf(undertaking);
                hiredate = personnelinfo.getString("hiredate");
                position = personnelinfo.getString("position");
                Position positionValue = Position.valueOf(position);

                personnellist.add(new Personnel(personnelid, lname, mname, fname, dateofbirth, gen, email, contactnum, undertakingValue, hiredate, positionValue));

            }

            conn.close();
            
            return personnellist;
        } catch (SQLException e){
            return null;
        }
    }

     public ArrayList<Personnel> list_female_personnel(){
        ArrayList<Personnel> personnellist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM personnel WHERE gender = 'F'");
            ResultSet personnelinfo = statement.executeQuery();

            String lname, mname, fname, dateofbirth, gender, email, undertaking, hiredate, position;
            int contactnum, personnelid;
            
            while (personnelinfo.next()) {
                personnelid = personnelinfo.getInt("personnelid");
                lname = personnelinfo.getString("lastname");
                mname = personnelinfo.getString("middlename");
                fname = personnelinfo.getString("firstname");
                dateofbirth = personnelinfo.getString("dateofbirth");
                gender = personnelinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = personnelinfo.getString("email");
                contactnum = personnelinfo.getInt("contactnum");
                undertaking = personnelinfo.getString("undertaking");
                Undertaking undertakingValue = Undertaking.valueOf(undertaking);
                hiredate = personnelinfo.getString("hiredate");
                position = personnelinfo.getString("position");
                Position positionValue = Position.valueOf(position);

                personnellist.add(new Personnel(personnelid, lname, mname, fname, dateofbirth, gen, email, contactnum, undertakingValue, hiredate, positionValue));

            }

            conn.close();
            
            return personnellist;
        } catch (SQLException e){
            return null;
        }
    }
     
}
