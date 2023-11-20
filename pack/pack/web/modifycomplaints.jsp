<%-- 
    Document   : modifycomplaints
    Created on : 11 15, 23, 10:22:35 PM
    Author     : ccslearner
--%>

<%@page import="DB_Complaints_src.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify Complaint</title>
    </head>
    <body>
        <jsp:useBean id="memberid" class="DB_Complaints_src.PeopleRecords" scope="request"/>
        <jsp:useBean id="info" class="DB_Complaints_src.Member" scope="request"/>
        <jsp:useBean id="compmanager" class="DB_Complaints_src.ComplaintManager" scope="request"/>
        <%
            int id = Integer.parseInt(request.getParameter("memberid"));
            String password = request.getParameter("password");
            session.setAttribute("mem", id);
            
            boolean exists = memberid.memberExists(id);
            if(exists){
                info.memberid = id;
                info.get_member();
            }
            if(exists && password.equals(info.password)){
                compmanager.list_for_one_member(id);
                if(compmanager.onememberscomplaints.size() > 0) {
        %>
        <div class="registermembersform">
            <form action="modifycomplaints_modify.jsp">
                <label for="complain" class="text2">Please pick a complaint to edit</label><br>
                <select id="complain" name="choice">
                <%
                    for(Integer compids: compmanager.onememberscomplaints){
                %>
                <option value="<%=compids%>"><%=compids%></option>
                <%
                    }
                %>
                </select>
                <input type="submit" value="Submit">
            </form>
        </div>
        <%
                } else {
        %>
            <h2 class="header2"> NO COMPLAINTS THAT CAN BE MODIFIED</h2>
            <button class="button1" onclick="window.location.href='modifycomplaintssignin.html'">Go Back</button>
            <button class="button1" onclick="window.location.href='index.html'">Go Home</button>
        <%
                } 
            
            } else if (exists && !password.equals(info.password)){
        %>
            <h1 class="header2">Wrong Password!!!</h1>
            <button class="button1" onclick="window.location.href='modifycomplaintssignin.html'">Go Back</button>
            <button class="button1" onclick="window.location.href='index.html'">Go Home</button>
        <%
            } else {
        %>
            <h1 class="header2">Id does not exists</h1>
            <button class="button1" onclick="window.location.href='modifycomplaintssignin.html'">Go Back</button>
            <button class="button1" onclick="window.location.href='index.html'">Go Home</button>
        <%
            }
        %>
    </body>
</html>
        
