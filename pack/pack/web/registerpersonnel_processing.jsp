<%-- 
    Document   : registerpersonnel_proccessing
    Created on : 11 14, 23, 11:39:50 PM
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
        <jsp:useBean id="personnel" class="DB_Complaints_src.Personnel" scope="session"/>
        <%
            personnel.firstname = request.getParameter("firstname");
            personnel.middlename = request.getParameter("middlename");
            personnel.lastname = request.getParameter("lastname");
            personnel.password = request.getParameter("password");
            personnel.dateofbirth = request.getParameter("dateofbirth");
            personnel.gender = Gender.valueOf(request.getParameter("gender"));
            personnel.contactnumber = request.getParameter("contact");
            personnel.email = request.getParameter("email");
            personnel.hiredate = request.getParameter("hire");
            personnel.position = Position.valueOf(request.getParameter("positions"));
            
            boolean added = personnel.register_personnel();
            
            if (added) {
        %>
        <h1 class="header1">Successfully Registered</h1>
        <h2 class="header2"><%=personnel.firstname%>, 
                            <%=personnel.middlename%>, 
                            <%=personnel.lastname%>, 
                            <%=personnel.dateofbirth%>, 
                            <%=personnel.gender.name()%>, 
                            <%=personnel.email%>, 
                            <%=personnel.contactnumber%>, 
                            <%=personnel.hiredate%>, 
                            <%=personnel.position.name()%> 
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
