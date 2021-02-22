<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@ page import ="com.excilys.computerDatabase.data.Computer" %>
<%@ page import = "java.util.List" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ListComputer</title>
</head>
<body>

	<%
	
		List<Computer> listComputer = (List) session.getAttribute("listComputer");
		for(Computer computer : listComputer){
			out.println(computer.toString());
		}
	
	%>
	
	<form action="/computer-database/ServletComputer" method="post">
	    <input type="submit" name="UserChoiceAction" value="previousPage"></button>
	    <input type="submit" name="UserChoiceAction" value="nextPage"></button>
	    <input type="submit" name="UserChoiceAction" value="addComputer"></button>
	</form>
	
</body>
</html>