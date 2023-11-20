<%-- 
    Document   : searchpersonnel_processing
    Created on : 11 20, 23, 1:50:47 AM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DB_Complaints_src.*" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Searching</title>
    </head>
    <body>
        <jsp:useBean id="search" class="DB_Complaints_src.Personnel" scope="session"/>
        <%  
            int id = Integer.valueOf(request.getParameter("choice"));
            search.personnelid = id;
            search.get_personnel();
            
            
        %>
        <h1 class="header1">Successfully Retrieved Data</h1>
        <h2 class="header2"><%=search.personnelid%>, 
                            <%=search.firstname%>, 
                            <%=search.middlename%>, 
                            <%=search.lastname%>,  
                            <%=search.dateofbirth%>, 
                            <%=search.gender.name()%>, 
                            <%=search.email%>, 
                            <%=search.contactnumber%>, 
                            <%=search.hiredate%>, 
                            <%=search.position.name()%>
                            
        </h2><br><br>
    <button class="button1" onclick="window.location.href='index.html'">Go Back to Home</button>
    </body>
</html>
