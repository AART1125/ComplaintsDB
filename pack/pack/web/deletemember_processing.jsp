<%-- 
    Document   : deletemember_processing
    Created on : 11 15, 23, 8:24:08 PM
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
        <jsp:useBean id="member" class="DB_Complaints_src.Member" scope="session"/>
        <jsp:useBean id="member2" class="DB_Complaints_src.Member" scope="session"/>
        <%  
            member.memberid = Integer.valueOf(request.getParameter("members"));
            member2.memberid = member.memberid;
            member2.get_member();
            
            boolean success = member.delete_member();
            
            if (success) {
        %>
        <h1 class="header1">Successfully Deleted</h1>
        <h2 class="header1">DELETED DATA:</h2>
        <h2 class="header2"><%=member2.memberid%>, 
                            <%=member2.firstname%>, 
                            <%=member2.middlename%>, 
                            <%=member2.lastname%>, 
                            <%=member2.password%>,
                            <%=member2.dateofbirth%>, 
                            <%=member2.gender.name()%>,
                            <%=member2.email%>,
                            <%=member2.contactnumber%>,
                            <%=member2.unitnumber%>
                           </h2><br><br>
        <%
            } else {
        %>
        <h1 class="header1">Unsuccessfully Deleted</h1>
        <%
            }
        %>
    <button class="button1" onclick="window.location.href='index.html'">Go Back to Home</button>
    </body>
</html>
