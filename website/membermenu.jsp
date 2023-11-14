<%-- 
    Document   : membermenu
    Created on : 11 13, 23, 10:39:55 PM
    Author     : ccslearner
--%>

<%@page import="DB_Complaints_src.*, java.lang.NumberFormatException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Complaints Transaction</title>
    </head>
    <body>
        <jsp:useBean id="memberid" class="DB_Complaints_src.PeopleManager" scope="session"/>
        <%
            int id;
            try{
                id = Integer.parseInt(request.getParameter("getId"));
            } catch (NumberFormatException e){
                id = -1;
            }
            Member copy = new Member();
            boolean exists = memberid.doesMemberExist(id, copy);
            
            if(!exists && id != 1){
        %>
        <h1 class="header2"> INVALID MEMBER </h1>
        <button class='button1' onclick="window.location.href = 'member.jsp';">go back</button>
        <%
            } else {
        %>
        <label class="text1">Please input the complaints that you may have, <%=copy.getFirstname()%></label>
        <div class='complaintsform'>
            <form action="membercomplaintsresults.jsp">
                <table>
                    <tr>
                        <th>
                            <label> Type of Complaint</label><br>
                            <input type="radio" id="personnel" value='P' name='complainttype' required>
                            <lable for='personnel'>Personnel</lable><br>
                            <input type="radio" id="infrastructure" value='I' name='complainttype'>
                            <lable for='infrastructure'>Infrastructure</lable><br>
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
