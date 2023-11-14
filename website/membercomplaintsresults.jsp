<%-- 
    Document   : membercomplaintsresults
    Created on : 11 14, 23, 1:59:55 PM
    Author     : ccslearner
--%>
<%@page import="DB_Complaints_src.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results of complaints</title>
    </head>
    <body>
        <%
            String type = request.getParameter("complainttype");
            String complaint = request.getParameter("complaint");
        %>
        <h1>Successfully Submitted!</h1><br>
        <h2>Resulting Complaint:</h2><br>
        <label>Type of Complaint: <%=type%></label><br>
        <label>Complaint: </label><br>
        <p style="font-style: bold;"><%=complaint%><p><br><br>
    <buton class="button1" onclick="window.location.href='index.html'">Go Back to Home</buton>
    </body>
</html>
