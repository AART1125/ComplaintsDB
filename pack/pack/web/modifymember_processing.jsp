<%-- 
    Document   : modifymember_processing
    Created on : 11 15, 23, 12:53:05 AM
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
        <jsp:useBean id="member" class="DB_Complaints_src.Member" scope="request"/>
        <jsp:useBean id="member2" class="DB_Complaints_src.Member" scope="request"/>
        <%
            
            
            member.memberid = (Integer) session.getAttribute("id");
            
            member2.memberid = member.memberid;
            member2.get_member();
            
            member.firstname = request.getParameter("firstname");
            member.middlename = request.getParameter("middlename");
            member.lastname = request.getParameter("lastname");
            member.dateofbirth = request.getParameter("dateofbirth");
            member.password = request.getParameter("password");
            member.gender = Gender.valueOf(request.getParameter("gender"));
            member.email = request.getParameter("email");
            member.contactnumber = request.getParameter("contactnum");
            member.unitnumber = Integer.valueOf(request.getParameter("unitnum"));
            
            boolean success = member.modify_member();
            
            if (success) {
        %>
        <h1 class="header1">Successfully Modified</h1>
        <h2 class="header1">NEW DATA:</h2>
        <h2 class="header2"><%=member.memberid%>, 
                            <%=member.firstname%>, 
                            <%=member.middlename%>, 
                            <%=member.lastname%>, 
                            <%=member.dateofbirth%>, 
                            <%=member.password%>, 
                            <%=member.gender.name()%>, 
                            <%=member.email%>, 
                            <%=member.contactnumber%>, 
                            <%=member.unitnumber%></h2><br><br>
        <h2 class="header1">OLD DATA:</h2>
        <h2 class="header2"><%=member2.memberid%>, 
                            <%=member2.lastname%>, 
                            <%=member2.middlename%>, 
                            <%=member2.firstname%>, 
                            <%=member2.dateofbirth%>, 
                            <%=member2.password%>, 
                            <%=member2.gender.name()%>, 
                            <%=member2.email%>, 
                            <%=member2.contactnumber%>, 
                            <%=member2.unitnumber%></h2>
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
