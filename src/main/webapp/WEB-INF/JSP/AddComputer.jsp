<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title><fmt:message key="label.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="/computerDatabase/resources/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="/computerDatabase/resources/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="/computerDatabase/resources/css/main.css" rel="stylesheet"
	media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="Computer"> <fmt:message key="label.home" /> </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1><fmt:message key="label.form.add" /></h1>
					<form:form action="AddComputer" method="POST"
						modelAttribute="ComputerFormAddDTO">
						<fieldset>
							<legend><fmt:message key="label.form.add" /></legend>
							<div class="form-group">
								<form:label path="name"><fmt:message key="label.form.nameComputer" /></form:label>
								<form:input path="name" type="text" class="form-control"
									id="computerName" placeholder="Computer name"
									name="computerName" maxlength="255" required="required"
									onchange="verifNameComputer(this.value)" />
							</div>
							<div class="form-group">
								<form:label path="introduced"><fmt:message key="label.form.introduced" /></form:label>
								<form:input path="introduced" type="date" class="form-control"
									id="introduced" placeholder="Introduced date"
									name="dateIntroduced" onchange="limitMinDate(this.value)"
									value="" />
							</div>
							<div class="form-group">
								<form:label path="discontinued"><fmt:message key="label.form.discontinued" /></form:label>
								<form:input path="discontinued" type="date" class="form-control"
									id="discontinued" placeholder="Discontinued date"
									name="dateDiscontinued" onchange="limitMaxDate(this.value)"
									value="" />
							</div>
							<div class="form-group">
								<form:label path="companyId"><fmt:message key="label.form.company" /></form:label>
								<form:select path="CompanyId" class="form-control"
									id="companyId" name="companyName">
									<form:option value="" label="--Please Select" />
									<form:options items="${listCompany}"/>
								</form:select>
							</div>
							<div class="actions pull-right">
								<input type="submit" value="<fmt:message key="label.form.valide" />"
									class="btn btn-primary"> <fmt:message key="label.form.or" /> <a href="Computer"
									class="btn btn-default"><fmt:message key="label.form.cancel" /></a>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript"
	src="/computerDatabase/resources/js/AddComputer.js"></script>
</html>