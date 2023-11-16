<%-- 
    Document   : resolution
    Created on : 11 16, 23, 12:02:46 AM
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
        <jsp:useBean id="memberid" class="DB_Complaints_src.PeopleRecords" scope="session"/>
        <%
            int id = Integer.parseInt(request.getParameter("getid"));
            
            session.setAttribute("personnelid", id);
            
            boolean exists = memberid.personnelExists(id);
            
            if(!exists && id != 1){
        %>
        <h1 class="header2"> Personnel does not exists </h1>
        <button class='button1' onclick="window.location.href = 'index.html';">go back</button>
        <%
            } else {
        %>
        <label class="text2">Please input the complaints that you may have</label>
        <div class='complaintsform'>
            <form action="resolution_processing.jsp">
                <table>
                    <tr>
                        <td>
                            <label>Enter description here:</label>
                            <textarea rows='30' cols="50" id='resolve' name="resolve" required></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="submit resolution">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <%
            }
        %>
    </body>
</html>
