<%-- 
    Document   : registermember_proccessing
    Created on : 11 14, 23, 10:48:02 PM
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
        <jsp:useBean id="added" class="DB_Complaints_src.Member" scope="session"/>
        <%  
            added.firstname = request.getParameter("firstname");
            added.middlename = request.getParameter("middlename");
            added.lastname = request.getParameter("lastname");
            added.password = request.getParameter("password");
            added.dateofbirth = request.getParameter("dateofbirth");
            added.gender = Gender.valueOf(request.getParameter("gender"));
            added.email = request.getParameter("email");
            added.contactnumber = request.getParameter("contactnum");
            added.unitnumber = Integer.valueOf(request.getParameter("unitnum"));
            
            boolean success = added.register_member();
            
            if (success) {
        %>
        <h1 class="header1">Successfully Registered</h1>
        <h2 class="header2"><%=added.memberid%>, 
                            <%=added.firstname%>, 
                            <%=added.middlename%>, 
                            <%=added.lastname%>, 
                            <%=added.password%>, 
                            <%=added.dateofbirth%>, 
                            <%=added.gender.name()%>, 
                            <%=added.email%>, 
                            <%=added.contactnumber%>, 
                            <%=added.unitnumber%>, 
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
