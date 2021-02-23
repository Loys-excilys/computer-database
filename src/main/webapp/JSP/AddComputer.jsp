<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="/WEB-INF/TagCompany.tld" prefix="company" %>
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
                    <h1>Add Computer</h1>
                    <form action="/computer-database/ServletComputer" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" placeholder="Computer name" name="computerName" maxlength="255" onchange="verifNameComputer(this.value)">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" id="introduced" placeholder="Introduced date" name="dateIntroduced" onchange="limitDate(this.value)">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" id="discontinued" placeholder="Discontinued date"name="dateDiscontinued">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyName">

								<company:Company listCompany="${sessionScope.listCompany}"/>

								</select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" name="userChoiceAction" value="Valider le form" class="btn btn-primary">
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