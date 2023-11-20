<%-- 
    Document   : modifypersonnel
    Created on : 11 15, 23, 1:26:00 AM
    Author     : ccslearner
--%>

<%@page import="DB_Complaints_src.*, java.time.LocalDate"%>
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
        <jsp:useBean id="personnel2" class="DB_Complaints_src.Personnel" scope="session"/>
        <%
            personnel.personnelid = (Integer)session.getAttribute("id");
            
            personnel2.personnelid = personnel.personnelid;
            personnel2.get_personnel();
            
            personnel.firstname = request.getParameter("firstname");
            personnel.middlename = request.getParameter("middlename");
            personnel.lastname = request.getParameter("lastname");
            personnel.password = request.getParameter("password");
            personnel.dateofbirth = request.getParameter("dateofbirth");
            personnel.gender = Gender.valueOf(request.getParameter("gender"));
            personnel.contactnumber = request.getParameter("contactnum");
            personnel.email = request.getParameter("email");
            personnel.hiredate = request.getParameter("hire");
            personnel.position = Position.valueOf(request.getParameter("positions"));
            
            boolean success = personnel.modify_personnel();
            
            
            if (success) {
        %>
        <h1 class="header1">Successfully Modified</h1>
        <h2 class="header1">NEW DATA:</h2>
        <h2 class="header2"><%=personnel.personnelid%>, 
                            <%=personnel.firstname%>, 
                            <%=personnel.middlename%>, 
                            <%=personnel.lastname%>, 
                            <%=personnel.password%>,
                            <%=personnel.dateofbirth%>, 
                            <%=personnel.gender.name()%>, 
                            <%=personnel.email%>, 
                            <%=personnel.contactnumber%>,  
                            <%=personnel.hiredate%>
                            <%=personnel.position%>
        </h2><br><br>
        <h2 class="header1">OLD DATA:</h2>
        <h2 class="header2"><%=personnel2.personnelid%>, 
                            <%=personnel2.firstname%>, 
                            <%=personnel2.middlename%>, 
                            <%=personnel2.lastname%>, 
                            <%=personnel2.password%>,
                            <%=personnel2.dateofbirth%>, 
                            <%=personnel2.gender.name()%>, 
                            <%=personnel2.email%>, 
                            <%=personnel2.contactnumber%>, 
                            <%=personnel2.hiredate%>, 
                            <%=personnel2.position%>
        </h2>
        <%
            } else {
        %>
        <h1 class="header1">Unsuccessfully Modified</h1>
        <%
            }
        %>
    <button class="button1" onclick="window.location.href='index.html'">Go Back to Home</button>
    </body>
</html>
