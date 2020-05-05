<%@page import="model.Patient"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Details</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.5.0.min.js"></script>
<script src="Components/patients.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-8">
				<h1 class="m-3">Patient Details</h1>
				<form id="formPatient" name="formPatient" method="post" action="patients.jsp">
					First Name: 
					<input id="first_name" name="first_name" type="text" class="form-control form-control-sm"> 
					<br> Last Name:
					<input id="last_name" name="last_name" type="text" class="form-control form-control-sm">
					<br> Address: 
					<input id="address" name="address" type="text" class="form-control form-control-sm">
					 <br> Email: 
					 <input id="email" name="email" type="text" class="form-control form-control-sm"> 
					 <br> 
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 					<input type="hidden" id="hidPatientIDSave" name="hidPatientIDSave" value="">
				</form>
				<br>
					<div id="alertSuccess" class="alert alert-success"></div>
			
					<br>
					<div id="alertError" class="alert alert-danger"></div>
					


				
				
	
			<div id="divPatientsGrid">
				<%
					Patient patientObj = new Patient();
					out.print(patientObj.readPatientDetails());
				%>
			</div>
		


			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-12" id="colStudents"></div>
		</div>
	</div>

</body>
</html>