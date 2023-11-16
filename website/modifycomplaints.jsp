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
        <jsp:useBean id="memberid" class="DB_Complaints_src.PeopleRecords" scope="session" />
        <%
            int id = Integer.parseInt(request.getParameter("getid"));
            
            boolean exists = memberid.memberExists(id);
            
            if(!exists && id != 1){
        %>
        <h1 class="header2"> Member does not exists </h1>
        <button class='button1' onclick="window.location.href = 'index.html';">go back</button>
        <%
            } else {
        %>
        <h1 class="header1">Modify a complaint!</h1>
        <div class="registermembersform">
            <form action="modifycomplaints_processing.jsp">
                <p class="row">
                    <label class="text1" for="compid">Complaint ID: </label>
                    <input class='inputregister' id="compid" name="compid" required>
                </p>
                <p class='row'>
                    <label class="text1" for='descarea'>Change Description</label>
                    <textarea rows='30' cols='50' id='descarea' name='descarea'></textarea>
                </p>
                
                
                <input type="submit" value="Submit">
            </form>
        </div>
        <%
            }
        %>
    </body>
</html>
