<%-- 
    Document   : searchmember_processing
    Created on : 11 20, 23, 1:14:48 AM
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
        <jsp:useBean id="search" class="DB_Complaints_src.Member" scope="session"/>
        <%  
            int id = Integer.valueOf(request.getParameter("choice"));
            search.memberid = id;
            search.get_member();
            
            
        %>
        <h1 class="header1">Successfully Retrieved Data</h1>
        <h2 class="header2"><%=search.memberid%>, 
                            <%=search.firstname%>, 
                            <%=search.middlename%>, 
                            <%=search.lastname%>, 
                            <%=search.password%>, 
                            <%=search.dateofbirth%>, 
                            <%=search.gender.name()%>, 
                            <%=search.email%>, 
                            <%=search.contactnumber%>, 
                            <%=search.unitnumber%>
        </h2><br><br>
    <button class="button1" onclick="window.location.href='index.html'">Go Back to Home</button>
    </body>
</html>
