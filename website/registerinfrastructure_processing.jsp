<%-- 
    Document   : registerinfrastructure_proccessing
    Created on : 11 15, 23, 12:07:20 AM
    Author     : ccslearner
--%>

<%@page import="DB_Complaints_src.*, java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Infrastructure Processing</title>
    </head>
    <body>
        <jsp:useBean id="complaints" class="DB_Complaints_src.PeopleManager" scope="session"/>
        <%
            String infrastructurename = request.getParameter("infrastructurename");
            Infrastructures type = Infrastructures.valueOf(request.getParameter("infrastructure"));
            
            
            if (type.name().equals("U")) {
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