<%-- 
    Document   : searchinfrastructure
    Created on : 11 20, 23, 1:59:12 AM
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
        <jsp:useBean id="infra" class="DB_Complaints_src.Infrastructure" scope="request"/>
        <%
            int id = Integer.valueOf(request.getParameter("adminid"));
            String password = request.getParameter("password");
            
            boolean exists = checker.isPersonnelAdmin(id);
            
            if (exists){
                info.personnelid = id;
                info.get_personnel();
            } 
            
            
            if(password.equals(info.password) && exists){
                infra.list_infrastructure();
        %>
        <div class="registermembersform">
            <form action="searchinfrastructure_processing.jsp">
                <label for="infras" class="text2">Please pick an infrastructure to get a report on</label><br>
                <select id="infras" name="choice">
                <%
                    for(Infrastructure infrastruct: infra.list){
                %>
                <option value="<%=infrastruct.infrastructureid%>"><%=infrastruct.infrastructureid%>|<%=infrastruct.infrastructurename%></option>
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
            <button class="button1" onclick="window.location.href='searchinfrastructuresignin.html'">Go Back</button>
            <button class="button1" onclick="window.location.href='index.html'">Go Home</button>
        <%
            } else {
        %>
            <h1 class="header2">Id does not exists</h1>
            <%=id%>, <%=info.personnelid%>, <%=exists%>
            <button class="button1" onclick="window.location.href='searchinfrastructuresignin.html'">Go Back</button>
            <button class="button1" onclick="window.location.href='index.html'">Go Home</button>
        <%
            }
        %>
    </body>
</html>
