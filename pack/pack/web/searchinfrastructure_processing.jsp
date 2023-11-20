<%-- 
    Document   : searchinfrastructure_processing
    Created on : 11 20, 23, 2:04:12 AM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DB_Complaints_src.*" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Infrastructure Searching</title>
    </head>
    <body>
        <jsp:useBean id="search" class="DB_Complaints_src.Infrastructure" scope="session"/>
        <%  
            int id = Integer.valueOf(request.getParameter("choice"));
            search.infrastructureid = id;
            search.get_infrastructure();
            
            
        %>
        <h1 class="header1">Successfully Retrieved Data</h1>
        <h2 class="header2"><%=search.infrastructureid%>, 
                            <%=search.infrastructurename%>, 
                            <%=search.infrastructuretype.name()%>
        </h2><br><br>
    <button class="button1" onclick="window.location.href='index.html'">Go Back to Home</button>
    </body>
</html>
