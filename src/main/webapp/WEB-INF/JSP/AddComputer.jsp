<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="/WEB-INF/TagFormComputer.tld" prefix="FormComputer"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="/computerDatabase/resources/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="/computerDatabase/resources/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="/computerDatabase/resources/css/main.css" rel="stylesheet"
	media="screen">

<script type="text/javascript"
	src="/computerDatabase/resources/js/AddComputer.js"></script>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="Computer"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form:form action="AddComputer" method="POST"
						modelAttribute="ComputerFormAddDTO">
						<fieldset>
							<legend>Add Computer</legend>

							<div class="form-group">
								<form:label path="name">Computer name</form:label>
								<form:input path="name" type="text" class="form-control"
									id="computerName" placeholder="Computer name"
									name="computerName" maxlength="255" required="required"
									onchange="verifNameComputer(this.value)" />
							</div>
							<div class="form-group">
								<form:label path="introduced">introduced</form:label>
								<form:input path="introduced" type="date" class="form-control"
									id="introduced" placeholder="Introduced date"
									name="dateIntroduced" onchange="limitMinDate(this.value)"
									value="" />
							</div>
							<div class="form-group">
								<form:label path="discontinued">discontinued</form:label>
								<form:input path="discontinued" type="date" class="form-control"
									id="discontinued" placeholder="Discontinued date"
									name="dateDiscontinued" onchange="limitMaxDate(this.value)"
									value="" />
							</div>
							<div class="form-group">
								<form:label path="companyId">companyId</form:label>
								<form:select path="CompanyId" class="form-control"
									id="companyId" name="companyName">
									<form:option value="" label="--Please Select" />
									<form:options items="${listCompany}"/>
								</form:select>
							</div>
							<div class="actions pull-right">
								<input type="submit" value="Valider le form"
									class="btn btn-primary"> or <a href="Computer"
									class="btn btn-default">Cancel</a>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>