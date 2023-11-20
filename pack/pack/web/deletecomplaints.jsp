<%-- 
    Document   : deletecomplaint
    Created on : 11 15, 23, 10:46:41 PM
    Author     : ccslearner
--%>

<%@page import="DB_Complaints_src.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Complaints Delete</title>
    </head>
    <body>
        <jsp:useBean id="personnelid" class="DB_Complaints_src.PeopleRecords" scope="session"/>
        <%
            int id = Integer.parseInt(request.getParameter("getid"));
            Member copy = new Member();
            
            boolean exists = personnelid.memberExists(id);
            
            if(!exists && id != 1){
        %>
        <h1 class="header2"> Admin does not exist </h1>
        <button class='button1' onclick="window.location.href = 'index.html';">go back</button>
        <%
            } else {
        %>
        <form action="deletecomplaints_processing.jsp">
            <label class="text2">Please input the complaint ID to be deleted:</label><br>
            <input id="memberid" name="complaintid"><br>
            <input type="submit" value="submit">
        </form>
        
        <%
            }
        %>
    </body>
</html>
