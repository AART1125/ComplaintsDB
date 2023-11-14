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
public class Member {

    private String dbpath = "";

    private int memberid;//PK
    private String lastname;
    private String firstname;
    private String dateofbirth;
    private Gender gender;
    private String email;
    private int contactnumber;
    private int unitnumber;
    
    public Member(){
        this.memberid = 0;
        this.lastname = null;
        this.firstname = null;
        this.dateofbirth = null;
        this.gender = null;
        this.email = null;
        this.contactnumber = 0;
        this.unitnumber = 0;
    }
    

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
    public Member(int memberid, String lastname, String firstname, String dateofbirth, Gender gender, String email, int contactnumber, int unitnumber) {
        this.memberid = memberid;
        this.lastname = lastname;
        this.firstname = firstname;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
        this.email = email;
        this.contactnumber = contactnumber;
        this.unitnumber = unitnumber;
    }

    public int getMemberid() {
        return memberid;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public String getGender() {
        return gender.toString();
    }

    public String getEmail() {
        return email;
    }

    public int getContactnumber() {
        return contactnumber;
    }

    public int getUnitnumber() {
        return unitnumber;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactnumber(int contactnumber) {
        this.contactnumber = contactnumber;
    }

    public void setUnitnumber(int unitnumber) {
        this.unitnumber = unitnumber;
    }
}