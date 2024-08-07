<%-- 
    Document   : searchpersonnel
    Created on : 11 20, 23, 1:38:36 AM
    Author     : ccslearner
--%>

<%@page import="DB_Complaints_src.*, java.time.LocalDate, java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Personnel</title>
    </head>
    <body>
        <jsp:useBean id="info" class="DB_Complaints_src.Personnel" scope="request"/>
        <jsp:useBean id="checker" class="DB_Complaints_src.PeopleRecords" scope="request"/>
        <%
            int id = Integer.valueOf(request.getParameter("adminid"));
            String password = request.getParameter("password");
            
            boolean exists = checker.isPersonnelAdmin(id);
            
            if (exists){
                info.personnelid = id;
                info.get_personnel();
            } 
            
            
            if(password.equals(info.password) && exists){
        %>
        <div class="registermembersform">
            <form action="searchpersonnel_processing.jsp">
                <label for="infras" class="text2">Please pick a member to get a report on</label><br>
                <select id="infras" name="choice">
                <%
                    for(Personnel personnel: checker.personnel_list){
                %>
                <option value="<%=personnel.personnelid%>"><%=personnel.personnelid%>|<%=personnel.lastname%> <%=personnel.firstname%></option>
                <%
                    }
                %>
                </select>
                <input type="submit" value="Submit">
            </form>
        </div>
        <%
            } else if (exists && !password.equals(info.password)){
        %>
            <h1 class="header2">Wrong Password!!!</h1>
            <button class="button1" onclick="window.location.href='modifyinfrastructuresignin.html'">Go Back</button>
            <button class="button1" onclick="window.location.href='index.html'">Go Home</button>
        <%
            } else {
        %>
            <h1 class="header2">Id does not exists</h1>
            <%=id%>, <%=info.personnelid%>, <%=exists%>
            <button class="button1" onclick="window.location.href='modifymembersignin.html'">Go Back</button>
            <button class="button1" onclick="window.location.href='index.html'">Go Home</button>
        <%
            }
        %>
    </body>
</html>
