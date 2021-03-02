<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="/WEB-INF/TagFormComputer.tld" prefix="FormComputer" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Computer Database</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Bootstrap -->
	<link href="/computer-database/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/computer-database/css/font-awesome.css" rel="stylesheet" media="screen">
	<link href="/computer-database/css/main.css" rel="stylesheet" media="screen">
	
	<script type="text/javascript" src ="js/AddComputer.js"></script>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="/computer-database/ServletComputer"> Application - Computer Database </a>
        </div>
    </header>
    
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Update Computer</h1>
                    <form action="/computer-database/ServletUpdateComputer" method="POST">
                    	${sessionScope.errorSaisie}
                        <fieldset>
								<FormComputer:FormUpdateComputer listCompany="${sessionScope.listCompany}" computerFormUpdateDTO="${sessionScope.updateComputer}"/>                 
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Valider le form" class="btn btn-primary">
                            or
                            <a href="/computer-database/ServletComputer" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>