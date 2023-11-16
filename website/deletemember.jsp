<%-- 
    Document   : deletemember
    Created on : 11 15, 23, 8:10:42 PM
    Author     : ccslearner
--%>

<%@page import="DB_Complaints_src.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Complaints Transaction</title>
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
        <form action="deletemember_processing.jsp">
            <label class="text2">Please input the member ID to be deleted:</label><br>
            <input id="memberid" name="memberid"><br>
            <input type="submit" value="submit">
        </form>
        
        <%
            }
        %>
    </body>
</html>
