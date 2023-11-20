<%-- 
    Document   : modifymembersignin
    Created on : 11 17, 23, 1:29:59 AM
    Author     : ccslearner
--%>
<%@page import="DB_Complaints_src.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign In</title>
    </head>
    <body>
        <h1 class="header2">Please type your member ID and sign in for modification</h1>
        <form action="modifymember.jsp">
            <label class="text2" for="memberid">Member ID: </label>
            <input id="memberid" name="memberid">
            <br>
            <label class="text2" for="password"></label>
            <input type="password" id="password" name="password">
            <input type="submit" value="submit">
        </form>
        
    </body>
</html>
