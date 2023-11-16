<%-- 
    Document   : modifymember_processing
    Created on : 11 15, 23, 12:53:05 AM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DB_Complaints_src.*" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Registration</title>
    </head>
    <body>
        <jsp:useBean id="regmember" class="DB_Complaints_src.PeopleRecords" scope="session"/>
        <%
            Member added = new Member();
            
            added.memberid = Integer.valueOf(request.getParameter("memid"));
            added.get_member();
            
            String firstname = request.getParameter("firstname");
            String middlename = request.getParameter("middlename");
            String lastname = request.getParameter("lastname");
            String dateofbirth = request.getParameter("dateofbirth");
            Gender gender = Gender.valueOf(request.getParameter("gender"));
            String email = request.getParameter("email");
            int contactnumber = Integer.valueOf(request.getParameter("contactnum"));
            int unitnumber = Integer.valueOf(request.getParameter("unitnum"));
            
            boolean success = added.modify_member(lastname, middlename, firstname, dateofbirth, gender, email, contactnumber, unitnumber);
            
            if (success || added.unitnumber == 1) {
        %>
        <h1 class="header1">Successfully Modified</h1>
        <%
            } else {
        %>
        <h1 class="header1">Unsuccessfully Modified</h1>
        <%
            }
        %>
    <buton class="button1" onclick="window.location.href='index.html'">Go Back to Home</buton>
    </body>
</html>
