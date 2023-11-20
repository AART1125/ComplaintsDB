<%-- 
    Document   : modifycomplaints_modify
    Created on : 11 20, 23, 7:32:03 PM
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
        <jsp:useBean id="memberid" class="DB_Complaints_src.PeopleRecords" scope="request"/>
        <jsp:useBean id="personnel" class="DB_Complaints_src.Personnel" scope="request"/>
        <jsp:useBean id="infras" class="DB_Complaints_src.Infrastructure" scope="request"/>
        <jsp:useBean id="complaints" class="DB_Complaints_src.Complaint" scope="request"/>
        <%
            int id = Integer.parseInt(request.getParameter("choice"));
            complaints.complaintid = id;
            boolean success = complaints.get_complaint();
            
            if(success){
        %>
        <label class="text2">Please input the complaints that you may have</label>
        <div class='complaintsform'>
            <form  action="modifycomplaints_processing.jsp">
                <table>
                    <tr>
                        <td>
                            <label>Enter Complaint here:</label>
                            <textarea rows='30' cols="50" id='complaint' name="complaint"><%=complaints.description%></textarea>
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
            } else {
        %>
            <h2 class="header2">Problem Getting Description from Database</h2>
        <%
            }
        %>
    </body>
</html>
