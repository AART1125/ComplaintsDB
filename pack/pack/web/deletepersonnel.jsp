<%-- 
    Document   : deletepersonnel
    Created on : 11 15, 23, 8:33:55 PM
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
        <jsp:useBean id="personnel" class="DB_Complaints_src.Personnel" scope="session"/>
        <%
            int id = Integer.parseInt(request.getParameter("personnelid"));
            String password = request.getParameter("password");
            
            boolean exists = personnelid.personnelExists(id);
            
            if(exists){
                personnel.personnelid = id;
                personnel.get_personnel();
            }
            
            session.setAttribute("id", id);
            if(exists && password.equals(personnel.password)){
        %>
        <form action="deletepersonnel_processing.jsp">
            <label class="text2">Are you sure you want to delete your account?:</label><br>
            <input type="radio" id="yes" name="option" value="Y" required><label class="text2" for="yes">Yes</label><br>
            <input type="radio" id="no" name="option" value="N"><label class="text2" for="no">No</label><br>
            <input type="submit" value="submit">
        </form>
        <%
            } else if (exists && !password.equals(personnel.password)) {
        %>
            <h1 class="header2">Wrong Password!!!</h1>
            <button class="button1" onclick="window.location.href='deletepersonnelsignin.html'">Go Back</button>
            <button class="button1" onclick="window.location.href='index.html'">Go Home</button>
        <%
            } else {
        %>
            <h1 class="header2">Personnel does not exists</h1>
            <button class="button1" onclick="window.location.href='deletepersonnelsignin.html'">Go Back</button>
            <button class="button1" onclick="window.location.href='index.html'">Go Home</button>
        <%
            }
        %>
    </body>
</html>
