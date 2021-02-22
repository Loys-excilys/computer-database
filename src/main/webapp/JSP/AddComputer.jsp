<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script type="text/javascript" src="/computer-database/Javascript/AddComputer.js"></script>

<%@ page import ="com.excilys.computerDatabase.data.Company" %>
<%@ page import = "java.util.List" %>
<html>
<head>
<meta charset="UTF-8">
<title>AddComputer</title>
</head>
<body>
	
	<form action="/computer-database/ServletComputer" method="post">
		<label>Computer Name</label>
		<input name="computerName" size="20" maxlength="255" onchange="verifNameComputer(this.value)">
		
		<label>Date Introduced</label>
		<input type="date" name="dateIntroduced" size="20" onchange="limitDate(this.value)">
		
		<label>Date Discontinued</label>
		<input type="date" name="dateDiscontinued" size="20">
		
		<label>Company Name</label>
		<select name="companyName">
		<%
		List<Company> listCompany = (List) session.getAttribute("listCompany");
		for(Company company : listCompany){
			out.println("<option value=\"" + company.getId() + "\">" + company.getName() + "</option>");
		}
		
		%>
		</select>
		
		<input type ="submit" name="UserChoiceAction" value="Valider le form"> 
	
	</form>
</body>
</html>