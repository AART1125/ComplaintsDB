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
        <jsp:useBean id="delpersonnel" class="DB_Complaints_src.PeopleRecords" scope="session"/>
        <%
            Member added = new Member();
            int adminid = (Integer)session.getAttribute("adminid");
            int personnelid = Integer.valueOf(request.getParameter("personnelid"));
            boolean exists = delpersonnel.personnelExists(personnelid);
            
            boolean success = added.delete_member();
            if (adminid == personnelid) {
        %>
        
         <h1 class="header1">You can't delete yourself! Please input a different ID</h1>
         <button class="button1" onclick="window.location.href='index.html'">Go Back to Home</button>
         
        <%    }
            else if (exists || (adminid != personnelid)) {
        %>
        <h1 class="header1">Successfully Registered</h1>
        <button class="button1" onclick="window.location.href='index.html'">Go Back to Home</button>
        <%
            } else {
        %>
        <h1 class="header1">Unsuccessfully Registered</h1>
        <button class="button1" onclick="window.location.href='index.html'">Go Back to Home</button>
        <%
            }
        %>
    
    </body>
</html>
