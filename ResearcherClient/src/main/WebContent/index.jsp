<%@page import="com.Researcher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Researcher details</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.6.0.min.js"></script>
		<script src="Components/Researchers.js"></script>
	</head>
	<body>

		<div class="container"> 
 			<div class="row">
 				<div class="col-8"> 
 					<h1 class="m-3">Researcher details</h1> 

 					<form id="formResearcher" name="formResearcher" method="post" action="index.jsp">
 						Name: 
						<input id="rechName" name="rechName" type="text" class="form-control form-control-sm">
						<br> NIC: 
						<input id="rechNIC" name="rechNIC" type="text" class="form-control form-control-sm">
						<br> Address: 
						<input id="rechAddress" name="rechAddress" type="text" class="form-control form-control-sm">
						<br> Email: 
						<input id="rechEmail" name="rechEmail" type="email" class="form-control form-control-sm">
						<br> PhoneNo:
						<input id="rechPhoneNo" name="rechPhoneNo" type="text" class="form-control form-control-sm">
						<br>
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
						<input type="hidden" id="hidResearcherIDSave" name="hidResearcherIDSave" value="">
					</form>
					
					<div id="alertSuccess" class="alert alert-success"></div>
 					<div id="alertError" class="alert alert-danger"></div>
 					
 					<br>
 					<div id="divResearcherGrid">
 					
 					<%
 						Researcher ResearcherObj = new Researcher();
 						out.print(ResearcherObj.readItems());
 					%>
 					</div>
 				</div>
 			</div>
		</div>

	</body>
</html>