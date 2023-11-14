<%-- 
    Document   : member
    Created on : 11 13, 23, 9:41:58 PM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, DB_Complaints_src.*" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 class="header2">Please enter your ID</h1>
        <form action="membermenu.jsp">
            <jsp:useBean id="memberid" class="DB_Complaints_src.PeopleManager" scope="session"/>
            <input type="text" name="getId" onfocus="this.value=''" id="getId" value="Enter member ID" required>
            <input type="submit" value="submit">
        </form>
        
    </body>
</html>