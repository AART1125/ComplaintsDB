<%-- 
    Document   : registerpersonnel_proccessing
    Created on : 11 14, 23, 11:39:50 PM
    Author     : ccslearner
--%>

<%@page import="DB_Complaints_src.*, java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results of complaints</title>
    </head>
    <body>
        <jsp:useBean id="complaints" class="DB_Complaints_src.PeopleManager" scope="session"/>
        <%
            String firstname = request.getParameter("firstname");
            String middlename = request.getParameter("middlename");
            String lastname = request.getParameter("lastname");
            String dateofbirth = request.getParameter("dateofbirth");
            Gender gender = Gender.valueOf(request.getParameter("gender"));
            String undertaking = request.getParameter("undertaking");
            String hiredate = LocalDate.now().toString();
            String availdays = request.getParameter("availdays");
            String availtimes = request.getParameter("availtimes");
            Position pos = Position.valueOf(request.getParameter("positions"));
            
            
            if (pos.name().equals("M")) {
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
