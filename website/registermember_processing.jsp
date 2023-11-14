<%-- 
    Document   : registermember_proccessing
    Created on : 11 14, 23, 10:48:02 PM
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
        <jsp:useBean id="regmember" class="DB_Complaints_src.PeopleManager" scope="session"/>
        <%
            Member added = new Member();
            
            added.firstname = request.getParameter("firstname");
            added.middlename = request.getParameter("middlename");
            added.lastname = request.getParameter("lastname");
            added.dateofbirth = request.getParameter("dateofbirth");
            added.gender = Gender.valueOf(request.getParameter("gender"));
            added.email = request.getParameter("email");
            added.contactnumber = Integer.valueOf(request.getParameter("contactnum"));
            added.unitnumber = Integer.valueOf(request.getParameter("unitnum"));
            
            boolean success = added.register_member();
            
            if (success || added.unitnumber == 1) {
        %>
        <h1 class="header1">Successfully Registered</h1>
        <%
            } else {
        %>
        <h1 class="header1">Unsuccessfully Registered</h1>
        <%
            }
        %>
    <buton class="button1" onclick="window.location.href='index.html'">Go Back to Home</buton>
    </body>
</html>
