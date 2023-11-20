<%-- 
    Document   : modifyinfrastructur_processing
    Created on : 11 15, 23, 1:34:43 AM
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
        <jsp:useBean id="struct" class="DB_Complaints_src.Infrastructure" scope="session"/>
        <jsp:useBean id="struct2" class="DB_Complaints_src.Infrastructure" scope="session"/>
        <%
            
            int id = (Integer) session.getAttribute("id");
            struct.infrastructureid = id;
            
            struct2.infrastructureid = struct.infrastructureid;
            struct2.get_infrastructure();
            
            struct.infrastructurename = request.getParameter("infrastructurename");
            struct.status = Status.valueOf(request.getParameter("status"));
            struct.infrastructuretype = InfrastructureType.valueOf(request.getParameter("infrastructure"));
            
            boolean success = struct.register_infrastructure();
            
            if (success) {
        %>
        <h1 class="header1">Successfully Registered</h1>
        <h2 class="header1">NEW DATA:</h2>
        <h2 class="header2"><%=struct.infrastructureid%>, 
                            <%=struct.infrastructurename%>, 
                            <%=struct.infrastructuretype%>, 
                            <%=struct.status%>, 
            
        </h2><br><br>
        <h2 class="header1">OLD DATA:</h2>
        <h2 class="header2"><%=struct.infrastructureid%>, 
                            <%=struct.infrastructurename%>, 
                            <%=struct.infrastructuretype%>, 
                            <%=struct.status%>, 
            
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
