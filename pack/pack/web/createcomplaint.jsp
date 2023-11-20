<%-- 
    Document   : createcomplaint
    Created on : 11 19, 23, 8:12:10 PM
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
        <jsp:useBean id="infras" class="DB_Complaints_src.Infrastructure" scope="request"/>
        <%
            int id = Integer.parseInt(request.getParameter("memberid"));
            String password = request.getParameter("password");
            session.setAttribute("mem", id);
            
            boolean exists = memberid.memberExists(id);
            
            if(exists){
                infras.list_infrastructure();
        %>
        <label class="text2">Please input the complaints that you may have</label>
        <div class='complaintsform'>
            <form  action="createcomplaint_processing.jsp">
                <table>
                    <tr>
                        <th>
                            <label> Type of Complaint</label><br>
                            <input type="radio" value='P' name='complainttype' onchange="getOption()" id="complainttype" required>
                            <label for='complainttype'>Personnel</label><br>
                            <input type="radio" value='I' name='complainttype' onchange="getOption()" id="complainttype">
                            <label for='complainttype'>Infrastructure</label><br>
                        </th>
                    </tr>
                </table>
                <div id="hiddenP" class='hidden'>
                    <select id="people" name="personnels">
                     <%
                         for(Personnel personnel: memberid.personnel_nonadmin_list){
                     %>
                     <option value="<%=personnel.personnelid%>"><%=personnel.personnelid%>|<%=personnel.lastname%> <%=personnel.firstname%></option>
                     <%
                         }
                     %>
                    </select><br><br>
                </div>
                <div id="hiddenI" class='hidden'>
                    <select id="people" name="infrastructures">
                     <%
                         for(Infrastructure infrastruct: infras.list){
                     %>
                     <option value="<%=infrastruct.infrastructureid%>"><%=infrastruct.infrastructureid%>|<%=infrastruct.infrastructurename%></option>
                     <%
                         }
                     %>
                    </select><br><br>
                    <label class="text2">
                        Type of infrastructure complaint<br>
                        <input type="radio" name="personneeded" id="personneeded" value="S">S<br>
                        <input type="radio" name="personneeded" id="personneeded" value="M">M<br>
                    </label>
                </div>
                <table>
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
            } else {
        %>
        <h1 class="header2"> Member does not exists </h1>
        <button class='button1' onclick="window.location.href = 'index.html';">go back</button>
        <%
            }
        %>
        <script>
            function getOption(){
                var value = document.querySelector('input[name="complainttype"]:checked').value
                var p = document.getElementById("hiddenP");
                var i = document.getElementById("hiddenI");
                
                var rad1 = p.querySelectorAll('input[type="radio"]');
                var rad2 = i.querySelectorAll('input[type="radio"]');
                if(value === 'P'){
                    p.style.display = 'block';
                    i.style.display = 'none';
                    setRequiredAttribute(rad1,true);
                    setRequiredAttribute(rad2,false);
                } else if (value === 'I'){
                    i.style.display = 'block';
                    p.style.display = 'none';
                    setRequiredAttribute(rad1,false);
                    setRequiredAttribute(rad2,true);
                } else {
                    p.style.display = 'none';
                    i.style.display = 'none';
                    setRequiredAttribute(rad1,false);
                    setRequiredAttribute(rad2,false);
                }
            }
            function setRequiredAttribute(inputs, isRequired){
                inputs.forEach(function (input) {
                    input.required = isRequired;
                    input.checked = false;
                });
            }
        </script>
    </body>
</html>


