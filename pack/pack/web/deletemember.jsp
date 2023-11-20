<%-- 
    Document   : deletemember
    Created on : 11 15, 23, 8:10:42 PM
    Author     : ccslearner
--%>

<%@page import="DB_Complaints_src.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Complaints Transaction</title>
    </head>
    <body>
        <jsp:useBean id="personnelid" class="DB_Complaints_src.PeopleRecords" scope="request"/>
        <jsp:useBean id="personnel" class="DB_Complaints_src.Personnel" scope="request"/>
        <%
            int id = Integer.parseInt(request.getParameter("getid"));
            String password = request.getParameter("password");
            
            boolean exists = personnelid.isPersonnelAdmin(id);
            if(exists){
                personnel.personnelid = id;
                personnel.get_personnel();
            }
            
            if(exists && password.equals(personnel.password)){
        %>
        <form action="deletemember_processing.jsp">
            <label class="text2">Please input the member ID to be deleted:</label><br>
            <select id="members" name="members">
                <%
                    for(Member member: personnelid.member_list){
                %>
                <option value="<%=member.memberid%>"><%=member.memberid%></option>
                <%
                    }
                %>
            </select>
            <input type="submit" value="submit">
        </form>
        <%
            } else if (exists && !password.equals(personnel.password)) {
        %>
            <h1 class="header2">Wrong Password!!!</h1>
            <button class="button1" onclick="window.location.href='deletemembersignin.html'">Go Back</button>
            <button class="button1" onclick="window.location.href='index.html'">Go Home</button>
        
        <%
            } else {
        %>
        <h1 class="header2"> Admin does not exist </h1>
        <button class="button1" onclick="window.location.href='deletemembersignin .html'">Go Back</button>
        <button class='button1' onclick="window.location.href = 'index.html';">go back</button>
        
        <%
            }
        %>
    </body>
</html>
