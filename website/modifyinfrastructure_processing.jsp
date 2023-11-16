<%-- 
    Document   : modifyinfrastructur_processing
    Created on : 11 15, 23, 1:34:43 AM
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
        <jsp:useBean id="complaints" class="DB_Complaints_src.PeopleRecords" scope="session"/>
        <%
            int infraid = Integer.valueOf(request.getParameter("infraid"));
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
    <button class="button1" onclick="window.location.href='index.html'">Go Back to Home</button>
    </body>
</html>
