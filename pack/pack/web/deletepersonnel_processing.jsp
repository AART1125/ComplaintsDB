<%-- 
    Document   : deletepersonnel_processing
    Created on : 11 15, 23, 8:37:10 PM
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
        <jsp:useBean id="checker" class="DB_Complaints_src.PeopleRecords" scope="session"/>
        <jsp:useBean id="personnel" class="DB_Complaints_src.Personnel" scope="session"/>
        <%
            personnel.personnelid = (Integer) session.getAttribute("id");
            boolean success = personnel.delete_personnel();
            
            if(success) {
        %>
        
         <h1 class="header1">Delete Successful!</h1>
         <h2 class="header2"> Personnel with ID : <%=personnel.personnelid%> has been deleted</h2>
         
         <button class="button1" onclick="window.location.href='index.html'">Go Back to Home</button>
         
        <%
            } else {
        %>
        <h1 class="header1">Unsuccessfully Deleted</h1>
        <button class="button1" onclick="window.location.href='index.html'">Go Back to Home</button>
        <%
            }
        %>
    
    </body>
</html>
