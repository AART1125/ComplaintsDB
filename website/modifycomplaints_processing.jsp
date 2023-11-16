<%-- 
    Document   : modifycomplaints_processing
    Created on : 11 15, 23, 10:40:41 PM
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
        <jsp:useBean id="modcomplaints" class="DB_Complaints_src.PeopleRecords" scope="session"/>
        <%
            int complaintid = Integer.valueOf(request.getParameter("compid"));
            String compdesc = request.getParameter("descarea");
            
            
            if (compdesc.equals("bgc")) {
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
