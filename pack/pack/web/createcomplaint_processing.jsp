<%-- 
    Document   : createcomplaint_processing
    Created on : 11 19, 23, 11:01:38 PM
    Author     : ccslearner
--%>

<%@page import="DB_Complaints_src.*, java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results of complaints</title>
    </head>
    <body>
        <jsp:useBean id="complaints" class="DB_Complaints_src.Complaint" scope="session"/>
        <jsp:useBean id="person" class="DB_Complaints_src.Personnel" scope="session"/>
        <jsp:useBean id="infra" class="DB_Complaints_src.Infrastructure" scope="session"/>
        <%
            String type = request.getParameter("complainttype");
            String complaint = request.getParameter("complaint");
            String date = LocalDate.now().toString();
            String personnelneeded;
            int complainerid = (Integer) session.getAttribute("mem");
            int complainingid = 0;
            if(type.equals("P")){
                complainingid = Integer.valueOf(request.getParameter("personnels"));
                person.personnelid = complainingid;
                person.get_personnel();
                personnelneeded = person.position.name();
            } else {
                complainingid = Integer.valueOf(request.getParameter("infrastructures"));
                infra.infrastructureid = complainingid;
                infra.get_infrastructure();
                personnelneeded = request.getParameter("personneeded");
            }
            
            complaints = new Complaint(complainerid, date, type, personnelneeded, complainingid, complaint);
            
            boolean success = complaints.create_complaint();
            
            if(success){
        %>
        <h1>Successfully Submitted!</h1><br>
        <h2>Resulting Complaint:</h2><br>
        <h2>Member ID: <%=complainerid%></h2><br>
        <h2>Date: <%=date%></h2><br>
        <h2>Type of Complaint: <%=type%></h2><br>
        <h2>Complaint on ID: <%=complainingid%></h2><br>
        <h2>Complaint: </h2><br>
        <h2 style="font-style: bold;"><%=complaint%></h2><br><br>
        <%
            } else {
        %>
        
        <%
            }
        %>
    <button class="button1" onclick="window.location.href='index.html'">Go Back to Home</button>
    </body>
</html>
