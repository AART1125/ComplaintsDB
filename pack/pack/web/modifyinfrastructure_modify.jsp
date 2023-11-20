<%-- 
    Document   : modifyinfrastructure_modify
    Created on : 11 19, 23, 5:15:58 PM
    Author     : ccslearner
--%>

<%@page import="DB_Complaints_src.*, java.time.LocalDate, java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Infrastructure Modify</title>
    </head>
    <body>
        <jsp:useBean id="info" class="DB_Complaints_src.Infrastructure" scope="request"/>
        <%
            int id = Integer.valueOf(request.getParameter("choice"));
            session.setAttribute("id", id);
            info.infrastructureid = id;
            info.get_infrastructure();
        %>
        <h1 class="header1">Register a Infrastructure to the Community!</h1>
        <div class="registermembersform">
            <form action="modifyinfrastructure_processing.jsp">
                <p class="row">
                    <label class="text1" for="infrastructurename">Infrastructure Name: </label>
                    <input class='inputregister' id="infrastructurename" name="infrastructurename" value="<%=info.infrastructurename%>">
                </p>
                <p class="row">
                    <label class="text1">Status </label><br>
                </p>
                <%
                    if("F".equals(info.status.name())){
                %>
                <p class="row">
                    <input id="fine" type="radio" name="status" value="F" required checked><label class="text2" for="fine">Fine</label>
                </p>
                <p class="row">
                    <input id="not" type="radio" name="status" value="N"><label class="text2" for="not">Not</label>
                </p>
                <%
                    } else {
                %>
                <p class="row">
                    <input id="fine" type="radio" name="status" value="F" required ><label class="text2" for="amenities">Fine</label>
                </p>
                <p class="row">
                    <input id="not" type="radio" name="status" value="N" checked><label class="text2" for="rental">Not</label>
                </p>
                <%
                    }
                %>
                <p class="row">
                    <label class="text1">Type of Infrastructure </label><br>
                </p>
                <%
                    if("A".equals(info.infrastructuretype.name())){
                %>
                <p class="row">
                    <input id="amenities" type="radio" name="infrastructure" value="A" checked><label class="text2" for="amenities">Amenities</label>
                </p>
                <p class="row">
                    <input id="rental" type="radio" name="infrastructure" value="R"><label class="text2" for="rental">Rental</label>
                </p>
                <p class="row">
                    <input id="unit" type="radio" name="infrastructure" value="U"><label class="text2" for="unit">Unit</label>
                </p>
                <%
                   } else if("R".equals(info.infrastructuretype.name())){
                %>
                <p class="row">
                    <input id="amenities" type="radio" name="infrastructure" value="A" ><label class="text2" for="amenities">Amenities</label>
                </p>
                <p class="row">
                    <input id="rental" type="radio" name="infrastructure" value="R" checked><label class="text2" for="rental">Rental</label>
                </p>
                <p class="row">
                    <input id="unit" type="radio" name="infrastructure" value="U"><label class="text2" for="unit">Unit</label>
                </p>
                <%
                   } else {
                %>
                <p class="row">
                    <input id="amenities" type="radio" name="infrastructure" value="A" ><label class="text2" for="amenities">Amenities</label>
                </p>
                <p class="row">
                    <input id="rental" type="radio" name="infrastructure" value="R" ><label class="text2" for="rental">Rental</label>
                </p>
                <p class="row">
                    <input id="unit" type="radio" name="infrastructure" value="U" checked><label class="text2" for="unit">Unit</label>
                </p>
                <%
                    }
                %>
               
                <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>
