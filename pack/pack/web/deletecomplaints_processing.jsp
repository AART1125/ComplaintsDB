<%-- 
    Document   : deletecomplaint_processing
    Created on : 11 15, 23, 10:48:17 PM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DB_Complaints_src.*" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Complaint Delete</title>
    </head>
    <body>
        <jsp:useBean id="regmember" class="DB_Complaints_src.PeopleRecords" scope="session"/>
        <%
            Member added = new Member();
            
            added.memberid = Integer.valueOf(request.getParameter("complaintid"));
            
            boolean success = added.delete_member();
            
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
    <button class="button1" onclick="window.location.href='index.html'">Go Back to Home</button>
    </body>
</html>
