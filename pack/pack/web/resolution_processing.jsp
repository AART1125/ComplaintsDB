<%-- 
    Document   : resolution_processing
    Created on : 11 16, 23, 12:08:54 AM
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
        <jsp:useBean id="complaints" class="DB_Complaints_src.PeopleRecords" scope="session"/>
        <%
            int personnelincharge = (Integer) session.getAttribute("personnelid");
            String date = LocalDate.now().toString();
            String desc = request.getParameter("resolve");
            
            if(desc.equals("hello")) {
        %>
        <h1>Successfully Submitted!</h1><br>
        <%
            } else {
        %>
        <h1>Unsuccessfully Submitted!</h1><br>
        <%
            }
        %>
    <button class="button1" onclick="window.location.href='index.html'">Go Back to Home</button>
    </body>
</html>
