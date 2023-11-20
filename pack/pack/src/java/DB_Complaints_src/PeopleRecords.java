package DB_Complaints_src;

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
import java.util.List;
public class PeopleRecords {
    private String           dbPath = "jdbc:mysql://localhost:3306/dbapp?user=root&password=12345678&useTimezone=true&serverTimezone=UTC&useSSL=false";

    public ArrayList<Member> listbycriteriamember = new ArrayList<>();
    public ArrayList<Member> member_list = list_members();
    public ArrayList<Member> member_male_list = list_male_members();
    public ArrayList<Member> member_female_list = list_female_members();

    public ArrayList<Personnel> listbycriteriapersonnel = new ArrayList<>();
    public ArrayList<Personnel> personnel_list = list_personnel();
    public ArrayList<Personnel> personnel_security_list = list_security_personnel();
    public ArrayList<Personnel> personnel_admin_list = list_admin_personnel();
    public ArrayList<Personnel> personnel_nonadmin_list = list_nonadmin_personnel();
    public ArrayList<Personnel> personnel_maintenance_list = list_maintenance_personnel();
    public ArrayList<Personnel> personnel_male_list = list_male_personnel();
    public ArrayList<Personnel> personnel_female_list = list_female_personnel();
    
    public PeopleRecords() {}
    
    public static void main(String[] args){
        PeopleRecords hi = new PeopleRecords();
        
        for(Personnel h1: hi.personnel_nonadmin_list){
            System.out.println(h1.personnelid);
        }
    }
    
    public boolean memberExists(int memberId){
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM member WHERE memberid = ?");
            statement.setInt(1, memberId);
            ResultSet results = statement.executeQuery();
            boolean exists = false;

            if (results.next()) {
                exists = true;
            } 
            
            statement.close();
            conn.close();
            
            statement.close();
            conn.close();

            return exists;
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

            String lname, mname, fname, password, dateofbirth, email, contactnum;
            int unitnum,memberId;
            Gender gender;
            
            while (memberinfo.next()) {
                memberId = memberinfo.getInt("memberid");
                lname = memberinfo.getString("lastname");
                mname = memberinfo.getString("middlename");
                fname = memberinfo.getString("firstname");
                password = memberinfo.getString("password");
                dateofbirth = memberinfo.getString("dateofbirth");
                gender = Gender.valueOf(memberinfo.getString("gender"));
                email = memberinfo.getString("email");
                contactnum = memberinfo.getString("contactnumber");
                unitnum = memberinfo.getInt("unitnumber");

                memberlist.add(new Member(memberId, lname, mname, fname, password, dateofbirth, gender, email, contactnum, unitnum));
            }

            statement.close();
            conn.close();
            
            statement.close();
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

            statement.close();
            conn.close();
            
            statement.close();
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

            statement.close();
            conn.close();
            
            return memberlist;
        } catch (SQLException e){
            return null;
        }
    }
    
    public void listMemberByCriteria(String lastname, int unit, String gender){
        String query = "SELECT * FROM member";
        
        if(lastname.equals("") || unit == 0 || gender.equals("")){
            query = "SELECT * FROM member";
        } else {
            query = "SELECT * FROM member WHERE ";
            
            if(!lastname.equals("") && unit != 0 && !gender.equals("")){
                query += "lastname LIKE '%"+lastname+"%' AND unitnumber="+unit+" AND gender='"+gender+"'";
            } else if (!lastname.equals("") && unit != 0 && !gender.equals("")) {
                query += "lastname LIKE '%"+lastname+"%' AND unitnumber="+unit;
            } else if (lastname.equals("") && unit != 0 && !gender.equals("")) {
                query += "unitnumber="+unit+" AND gender='"+gender+"'";
            } else if (!lastname.equals("") && unit == 0 && gender.equals("")) {
                query += "lastname LIKE '%"+lastname+"%'";
            } else if (lastname.equals("") && unit != 0 && gender.equals("")) {
                query += "unitnumber="+unit;
            } else if (lastname.equals("") && unit != 0 && !gender.equals("")) {
                query += "gender='"+gender+"'";
            }
        }
        
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet memberinfo = statement.executeQuery();
            
            String lname, mname, fname, password, dateofbirth, email, contactnum;
            int unitnum,memberId;
            Gender gen;
            
            while (memberinfo.next()) {
                memberId = memberinfo.getInt("memberid");
                lname = memberinfo.getString("lastname");
                mname = memberinfo.getString("middlename");
                fname = memberinfo.getString("firstname");
                password = memberinfo.getString("password");
                dateofbirth = memberinfo.getString("dateofbirth");
                gen = Gender.valueOf(memberinfo.getString("gender"));
                email = memberinfo.getString("email");
                contactnum = memberinfo.getString("contactnumber");
                unitnum = memberinfo.getInt("unitnumber");

                listbycriteriamember.add(new Member(memberId, lname, mname, fname, password, dateofbirth, gen, email, contactnum, unitnum));
            }
            
            statement.close();
            conn.close();
           
        } catch (SQLException e) {
            System.err.println(e);
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

    public boolean isPersonnelAdmin(int personnelId){
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM personnel p JOIN admin a ON p.personnelid = a.personnelid WHERE a.personnelid = ? AND p.personnelid = ?");
            statement.setInt(1, personnelId);
            statement.setInt(2, personnelId);
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

    public void listPersonnelByCriteria(String position, String lastname, String gender) {
        String query = "SELECT * FROM personnel WHERE 1 = 1";

        if (!position.isEmpty()) {
            query += " AND position LIKE ?";
        }
        if (!lastname.isEmpty()) {
            query += " AND lastname LIKE ?";
        }
        if (!gender.isEmpty()) {
            query += " AND gender = ?";
        }

        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement(query);

            int parameterIndex = 1;

            if (!position.isEmpty()) {
                statement.setString(parameterIndex++, "%" + position + "%");
            }
            if (!lastname.isEmpty()) {
                statement.setString(parameterIndex++, "%" + lastname + "%");
            }
            if (!gender.isEmpty()) {
                statement.setString(parameterIndex++, gender);
            }

            ResultSet personnelInfo = statement.executeQuery();

            String lname, mname, fname, dateofbirth, email, hiredate, contactnum, password;
            int personnelid;
            List<Personnel> listbycriteriapersonnel = new ArrayList<>();

            while (personnelInfo.next()) {
                personnelid = personnelInfo.getInt("personnelid");
                lname = personnelInfo.getString("lastname");
                mname = personnelInfo.getString("middlename");
                fname = personnelInfo.getString("firstname");
                password = personnelInfo.getString("password");
                dateofbirth = personnelInfo.getString("dateofbirth");
                String retrievedGender = personnelInfo.getString("gender");
                Gender gen = Gender.valueOf(retrievedGender);
                email = personnelInfo.getString("email");
                contactnum = personnelInfo.getString("contactnumber");
                hiredate = personnelInfo.getString("hiredate");
                String retrievedPosition = personnelInfo.getString("position");
                Position positionValue = Position.valueOf(retrievedPosition);

                listbycriteriapersonnel.add(new Personnel(personnelid, lname, mname, fname, password, dateofbirth, gen, email, contactnum, hiredate, positionValue));
            }

            statement.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public ArrayList<Personnel> list_personnel(){
        ArrayList<Personnel> personnellist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM personnel");
            ResultSet personnelinfo = statement.executeQuery();

            String lname, mname, fname, dateofbirth, gender, email, hiredate, position, contactnum, password;
            int personnelid;
            
            while (personnelinfo.next()) {
                personnelid = personnelinfo.getInt("personnelid");
                lname = personnelinfo.getString("lastname");
                mname = personnelinfo.getString("middlename");
                fname = personnelinfo.getString("firstname");
                password = personnelinfo.getString("password");
                dateofbirth = personnelinfo.getString("dateofbirth");
                gender = personnelinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = personnelinfo.getString("email");
                contactnum = personnelinfo.getString("contactnumber");
                hiredate = personnelinfo.getString("hiredate");
                position = personnelinfo.getString("position");
                Position positionValue = Position.valueOf(position);

                personnellist.add(new Personnel(personnelid, lname, mname, fname, password, dateofbirth, gen, email, contactnum, hiredate, positionValue));

            }

            conn.close();
            
            return personnellist;
        } catch (SQLException e){
            return null;
        }
    }

    public ArrayList<Personnel> list_personnel_by_lastname(String lastname){
        ArrayList<Personnel> personnellist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM personnel WHERE lastname = ?");
            statement.setString(1, lastname);
            ResultSet personnelinfo = statement.executeQuery();

            String lname, mname, fname, dateofbirth, gender, email, hiredate, position, contactnum, password;
            int personnelid;
            
            while (personnelinfo.next()) {
                personnelid = personnelinfo.getInt("personnelid");
                lname = personnelinfo.getString("lastname");
                mname = personnelinfo.getString("middlename");
                fname = personnelinfo.getString("firstname");
                password = personnelinfo.getString("password");
                dateofbirth = personnelinfo.getString("dateofbirth");
                gender = personnelinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = personnelinfo.getString("email");
                contactnum = personnelinfo.getString("contactnumber");
                hiredate = personnelinfo.getString("hiredate");
                position = personnelinfo.getString("position");
                Position positionValue = Position.valueOf(position);

                personnellist.add(new Personnel(personnelid, lname, mname, fname, password, dateofbirth, gen, email, contactnum, hiredate, positionValue));

            }

            conn.close();
            
            return personnellist;
        } catch (SQLException e){
            return null;
        }
    }

        public ArrayList<Personnel> list_personnel_by_firstname(String firstname){
        ArrayList<Personnel> personnellist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM personnel WHERE firstname = ?");
            statement.setString(1, firstname);
            ResultSet personnelinfo = statement.executeQuery();

            String lname, mname, fname, dateofbirth, gender, email, hiredate, position, contactnum, password;
            int personnelid;
            
            while (personnelinfo.next()) {
                personnelid = personnelinfo.getInt("personnelid");
                lname = personnelinfo.getString("lastname");
                mname = personnelinfo.getString("middlename");
                fname = personnelinfo.getString("firstname");
                password = personnelinfo.getString("password");
                dateofbirth = personnelinfo.getString("dateofbirth");
                gender = personnelinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = personnelinfo.getString("email");
                contactnum = personnelinfo.getString("contactnumber");
                hiredate = personnelinfo.getString("hiredate");
                position = personnelinfo.getString("position");
                Position positionValue = Position.valueOf(position);

                personnellist.add(new Personnel(personnelid, lname, mname, fname, password, dateofbirth, gen, email, contactnum, hiredate, positionValue));

            }

            conn.close();
            
            return personnellist;
        } catch (SQLException e){
            return null;
        }
    }

    public ArrayList<Personnel> list_personnel_by_middlename(String middlename){
        ArrayList<Personnel> personnellist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM personnel WHERE middlename = ?");
            statement.setString(1, middlename);
            ResultSet personnelinfo = statement.executeQuery();

            String lname, mname, fname, dateofbirth, gender, email, hiredate, position, contactnum, password;
            int personnelid;
            
            while (personnelinfo.next()) {
                personnelid = personnelinfo.getInt("personnelid");
                lname = personnelinfo.getString("lastname");
                mname = personnelinfo.getString("middlename");
                fname = personnelinfo.getString("firstname");
                password = personnelinfo.getString("password");
                dateofbirth = personnelinfo.getString("dateofbirth");
                gender = personnelinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = personnelinfo.getString("email");
                contactnum = personnelinfo.getString("contactnumber");
                hiredate = personnelinfo.getString("hiredate");
                position = personnelinfo.getString("position");
                Position positionValue = Position.valueOf(position);

                personnellist.add(new Personnel(personnelid, lname, mname, fname, password, dateofbirth, gen, email, contactnum, hiredate, positionValue));

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

            String lname, mname, fname, dateofbirth, gender, email, hiredate, position, contactnum, password;
            int personnelid;
            
            while (personnelinfo.next()) {
                personnelid = personnelinfo.getInt("personnelid");
                lname = personnelinfo.getString("lastname");
                mname = personnelinfo.getString("middlename");
                fname = personnelinfo.getString("firstname");
                password = personnelinfo.getString("password");
                dateofbirth = personnelinfo.getString("dateofbirth");
                gender = personnelinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = personnelinfo.getString("email");
                contactnum = personnelinfo.getString("contactnumber");
                hiredate = personnelinfo.getString("hiredate");
                position = personnelinfo.getString("position");
                Position positionValue = Position.valueOf(position);

                personnellist.add(new Personnel(personnelid, lname, mname, fname, password, dateofbirth, gen, email, contactnum, hiredate, positionValue));

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

            String lname, mname, fname, dateofbirth, gender, email, hiredate, position, contactnum, password;
            int personnelid;
            
            while (personnelinfo.next()) {
                personnelid = personnelinfo.getInt("personnelid");
                lname = personnelinfo.getString("lastname");
                mname = personnelinfo.getString("middlename");
                fname = personnelinfo.getString("firstname");
                password = personnelinfo.getString("password");
                dateofbirth = personnelinfo.getString("dateofbirth");
                gender = personnelinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = personnelinfo.getString("email");
                contactnum = personnelinfo.getString("contactnumber");
                hiredate = personnelinfo.getString("hiredate");
                position = personnelinfo.getString("position");
                Position positionValue = Position.valueOf(position);

                personnellist.add(new Personnel(personnelid, lname, mname, fname, password, dateofbirth, gen, email, contactnum, hiredate, positionValue));

            }

            conn.close();
            
            return personnellist;
        } catch (SQLException e){
            return null;
        }
    }

    public ArrayList<Personnel> list_nonadmin_personnel(){
        ArrayList<Personnel> personnellist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT p.* FROM personnel p JOIN nonadmin n ON p.personnelid = n.personnelid");
            ResultSet personnelinfo = statement.executeQuery();

            String lname, mname, fname, dateofbirth, gender, email, hiredate, position, contactnum, password;
            int personnelid;
            
            while (personnelinfo.next()) {
                personnelid = personnelinfo.getInt("personnelid");
                lname = personnelinfo.getString("lastname");
                mname = personnelinfo.getString("middlename");
                fname = personnelinfo.getString("firstname");
                password = personnelinfo.getString("password");
                dateofbirth = personnelinfo.getString("dateofbirth");
                gender = personnelinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = personnelinfo.getString("email");
                contactnum = personnelinfo.getString("contactnumber");
                hiredate = personnelinfo.getString("hiredate");
                position = personnelinfo.getString("position");
                Position positionValue = Position.valueOf(position);

                personnellist.add(new Personnel(personnelid, lname, mname, fname, password, dateofbirth, gen, email, contactnum, hiredate, positionValue));

            }
            statement.close();

            conn.close();
            
            return personnellist;
        } catch (SQLException e){
            System.err.println(e);
            return null;
        }
    }
    
    
    public ArrayList<Personnel> list_maintenance_personnel(){
        ArrayList<Personnel> personnellist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM personnel WHERE position = 'M'");
            ResultSet personnelinfo = statement.executeQuery();

            String lname, mname, fname, dateofbirth, gender, email, hiredate, position, contactnum, password;
            int personnelid;
            
            while (personnelinfo.next()) {
                personnelid = personnelinfo.getInt("personnelid");
                lname = personnelinfo.getString("lastname");
                mname = personnelinfo.getString("middlename");
                fname = personnelinfo.getString("firstname");
                password = personnelinfo.getString("password");
                dateofbirth = personnelinfo.getString("dateofbirth");
                gender = personnelinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = personnelinfo.getString("email");
                contactnum = personnelinfo.getString("contactnumber");
                hiredate = personnelinfo.getString("hiredate");
                position = personnelinfo.getString("position");
                Position positionValue = Position.valueOf(position);

                personnellist.add(new Personnel(personnelid, lname, mname, fname, password, dateofbirth, gen, email, contactnum, hiredate, positionValue));

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

            String lname, mname, fname, dateofbirth, gender, email, hiredate, position, contactnum, password;
            int personnelid;
            
            while (personnelinfo.next()) {
                personnelid = personnelinfo.getInt("personnelid");
                lname = personnelinfo.getString("lastname");
                mname = personnelinfo.getString("middlename");
                fname = personnelinfo.getString("firstname");
                password = personnelinfo.getString("password");
                dateofbirth = personnelinfo.getString("dateofbirth");
                gender = personnelinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = personnelinfo.getString("email");
                contactnum = personnelinfo.getString("contactnumber");
                hiredate = personnelinfo.getString("hiredate");
                position = personnelinfo.getString("position");
                Position positionValue = Position.valueOf(position);

                personnellist.add(new Personnel(personnelid, lname, mname, fname, password, dateofbirth, gen, email, contactnum, hiredate, positionValue));

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

            String lname, mname, fname, dateofbirth, gender, email, hiredate, position, contactnum, password;
            int personnelid;
            
            while (personnelinfo.next()) {
                personnelid = personnelinfo.getInt("personnelid");
                lname = personnelinfo.getString("lastname");
                mname = personnelinfo.getString("middlename");
                fname = personnelinfo.getString("firstname");
                password = personnelinfo.getString("password");
                dateofbirth = personnelinfo.getString("dateofbirth");
                gender = personnelinfo.getString("gender");
                Gender gen = Gender.valueOf(gender);
                email = personnelinfo.getString("email");
                contactnum = personnelinfo.getString("contactnumber");
                hiredate = personnelinfo.getString("hiredate");
                position = personnelinfo.getString("position");
                Position positionValue = Position.valueOf(position);

                personnellist.add(new Personnel(personnelid, lname, mname, fname, password, dateofbirth, gen, email, contactnum, hiredate, positionValue));

            }

            conn.close();
            
            return personnellist;
        } catch (SQLException e){
            return null;
        }
    }
     
}