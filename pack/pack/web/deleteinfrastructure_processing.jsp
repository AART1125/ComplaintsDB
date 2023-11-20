<%-- 
    Document   : deleteinfrastructure_processing
    Created on : 11 15, 23, 10:15:53 PM
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
        <jsp:useBean id="infra" class="DB_Complaints_src.Infrastructure" scope="session"/>
        <jsp:useBean id="infra2" class="DB_Complaints_src.Infrastructure" scope="session"/>
        <%
            int id = Integer.valueOf(request.getParameter("choice"));
            infra.infrastructureid = id;
            infra2.infrastructureid = infra.infrastructureid;
            infra2.get_infrastructure();
            
            boolean success = infra.delete_infrastructure();
            
            if (success) {
        %>
        <h1 class="header1">Successfully Deleted</h1>
        <h2 class="header1">DELETED DATA:</h2>
        <h2 class="header2"><%=infra2.infrastructureid%>, 
                            <%=infra2.infrastructurename%>, 
                            <%=infra2.infrastructuretype.name()%>, 
                            <%=infra2.status%>
                            
        </h2><br><br>
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
