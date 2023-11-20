<%-- 
    Document   : modifymember
    Created on : 11 17, 23, 2:12:51 AM
    Author     : ccslearner
--%>
<%@page import="DB_Complaints_src.*, java.time.LocalDate, java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify Member</title>
    </head>
    <body>
        <jsp:useBean id="info" class="DB_Complaints_src.Member" scope="request"/>
        <jsp:useBean id="checker" class="DB_Complaints_src.PeopleRecords" scope="request"/>
        <%
            int id = Integer.valueOf(request.getParameter("memberid"));
            String password = request.getParameter("password");
            session.setAttribute("id", id);
            
            boolean exists = checker.memberExists(id);
            
            if (exists){
                info.memberid = id;
                info.get_member();
            } 
            
            
            if(password.equals(info.password) && exists){
        %>
        <h1 class="header1">Modify a Member to the Community!</h1>
        <div class="registermembersform">
            <form action="modifymember_processing.jsp">
                <p class="row">
                    <label class="text1" for="firstname">First Name: </label>
                    <input class='inputregister' id="firstname" name="firstname" value="<%=info.firstname%>">
                </p>
                <p class="row">
                    <label class="text1" for="middlename">Middle Name: </label>
                    <input class='inputregister' id="middlename" name="middlename" value="<%=info.middlename%>">
                </p>
                <p class="row">
                    <label class="text1" for="lastname">Last Name:</label>
                    <input class='inputregister' id="lastname" name="lastname" value="<%=info.lastname%>">
                </p>
                <p class="row">
                    <label class="text1" for="dateofbirth">Date of Birth</label>
                    <input class='inputregister' id="dateofbirth" type="date" name="dateofbirth" value="<%=info.dateofbirth%>">  
                </p>
                <p class="row">
                    <label class="text1" for="password">Password: </label>
                    <input class='inputregister' id="password" type="password" name="password" value="<%=info.password%>">  
                </p>
                <p class="row">
                    <label class="text1">Gender: </label><br>
                </p>
                <%
                    if("M".equals(info.gender.name())){
                %>
                <p class="row">
                    <input id="male" type="radio" name="gender" value="M" checked><label class="text2" for="male">Male</label>
                </p>
                <p class="row">
                    <input id="female" type="radio" name="gender" value="F"><label class="text2" for="female">Female</label>
                </p>
                <%
                    } else {
                %>
                <p class="row">
                    <input id="male" type="radio" name="gender" value="M"><label class="text2" for="male">Male</label>
                </p>
                <p class="row">
                    <input id="female" type="radio" name="gender" value="F" checked><label class="text2" for="female">Female</label>
                </p>
                <%
                    }
                %>
                <p class="row">
                    <label class="text1" for="email">Email Address: </label>
                    <input class="inputregister" id="email" type="email" name="email" value="<%=info.email%>">
                </p>
                <p class="row">
                    <label class="text1" for="contactnum">Contact Number:</label>
                    <input class="inputregister" id="contactnum" name="contactnum" value="<%=info.contactnumber%>">
                </p>
                <p class="row">
                    <label class="text1" for="unitnum">Unit Number:</label>
                    <input class="inputregister" id="unitnum" name="unitnum" value="<%=info.unitnumber%>">
                </p>
                
                <input type="submit" value="Submit">
            </form>
        </div>
        <%
            } else if (exists && !password.equals(info.password)){
        %>
            <h1 class="header2">Wrong Password!!!</h1>
            <button class="button1" onclick="window.location.href='modifymembersignin.html'">Go Back</button>
            <button class="button1" onclick="window.location.href='index.html'">Go Home</button>
        <%
            } else {
        %>
            <h1 class="header2">Id does not exists</h1>
            <button class="button1" onclick="window.location.href='modifymembersignin.html'">Go Back</button>
            <button class="button1" onclick="window.location.href='index.html'">Go Home</button>
        <%
            }
        %>
    </body>
</html>
