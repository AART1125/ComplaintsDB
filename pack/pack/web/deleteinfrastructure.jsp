<%-- 
    Document   : deletemember
    Created on : 11 15, 23, 8:10:42 PM
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
        <jsp:useBean id="info" class="DB_Complaints_src.Personnel" scope="request"/>
        <jsp:useBean id="checker" class="DB_Complaints_src.PeopleRecords" scope="request"/>
        <jsp:useBean id="infra" class="DB_Complaints_src.Infrastructure" scope="request"/>
        <%
            int id = Integer.valueOf(request.getParameter("personnelid"));
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
            <form action="deleteinfrastructure_processing.jsp">
                <label for="infras" class="text2">Please pick an infrastructure to delete</label><br>
                <select id="infras" name="choice">
                <%
                    for(Infrastructure build: infra.list){
                %>
                <option value="<%=build.infrastructureid%>"><%=build.infrastructureid%> | <%=build.infrastructurename%></option>
                <%
                    }
                %>
                </select>
                <input type="submit" value="Delete">
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
            <button class="button1" onclick="window.location.href='modifyinfrastructuresignin.html'">Go Back</button>
            <button class="button1" onclick="window.location.href='index.html'">Go Home</button>
        <%
            }
        %>
    </body>
</html>
