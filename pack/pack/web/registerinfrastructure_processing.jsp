<%-- 
    Document   : registerinfrastructure_proccessing
    Created on : 11 15, 23, 12:07:20 AM
    Author     : ccslearner
--%>

<%@page import="DB_Complaints_src.*, java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Infrastructure Processing</title>
    </head>
    <body>
        <jsp:useBean id="infrastructure" class="DB_Complaints_src.Infrastructure" scope="session"/>
        <%
            infrastructure.infrastructurename = request.getParameter("infrastructurename");
            infrastructure.infrastructuretype = InfrastructureType.valueOf(request.getParameter("infrastructure"));
            infrastructure.status = Status.F;
            
            boolean success = infrastructure.register_infrastructure();
            
            
            if (success) {
        %>
        <h1 class="header1">Successfully Registered</h1>
        <h2 class="header2"><%=infrastructure.infrastructurename%>, 
                            <%=infrastructure.infrastructuretype%>, 
                            <%=infrastructure.status%>, 
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