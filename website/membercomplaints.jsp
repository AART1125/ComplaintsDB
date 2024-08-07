<%-- 
    Document   : membermenu
    Created on : 11 13, 23, 10:39:55 PM
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
            
            boolean exists = memberid.memberExists(id);
            
            if(!exists && id != 1){
        %>
        <h1 class="header2"> Member does not exists </h1>
        <button class='button1' onclick="window.location.href = 'index.html';">go back</button>
        <%
            } else {
        %>
        <label class="text2">Please input the complaints that you may have</label>
        <div class='complaintsform'>
            <form action="membercomplaints_processing.jsp">
                <table>
                    <tr>
                        <th>
                            <label> Type of Complaint</label><br>
                            <input type="radio" id="personnel" value='P' name='complainttype' required>
                            <label for='personnel'>Personnel</label><br>
                            <input type="radio" id="infrastructure" value='I' name='complainttype'>
                            <label for='infrastructure'>Infrastructure</label><br>
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <label>Enter Complaint here:</label>
                            <textarea rows='30' cols="50" id='complaint' name="complaint" required></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="Submit Complaint">
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
