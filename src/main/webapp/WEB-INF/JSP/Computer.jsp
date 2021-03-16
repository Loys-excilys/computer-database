<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/TagComputer.tld" prefix="computer"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<title><fmt:message key="label.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="/computerDatabase/resources/css/bootstrap.min.css"
	rel="stylesheet" media="screen" type="text/css">
<link href="/computerDatabase/resources/css/font-awesome.css"
	rel="stylesheet" media="screen" type="text/css">
<link href="/computerDatabase/resources/css/main.css" rel="stylesheet"
	media="screen" type="text/css">

<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">

<script src="/computerDatabase/resources/js/jquery.min.js"></script>
<script src="/computerDatabase/resources/js/bootstrap.min.js"></script>
<script src="/computerDatabase/resources/js/dashboard.js"></script>
</head>
<body>

	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="Computer"> <fmt:message
					key="label.home" />
			</a>
			<div class = "pull-right">
			<ul>
				<li><a href="?lang=en"><fmt:message key="label.lang.en" /></a></li>
				<li><a href="?lang=fr"><fmt:message key="label.lang.fr" /></a></li>
			</ul>
		</div>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				${ numberComputer }
				<fmt:message key="label.dashboard.computerNumber" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="Computer" method="GET"
						class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit"
							value="<fmt:message
					key="label.dashboard.filter" />"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="AddComputer"><fmt:message
							key="label.dashboard.addComputer" /></a> <a class="btn btn-default"
						id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><fmt:message
							key="label.dashboard.edit" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="Computer" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<caption></caption>
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th id="delete" class="editMode"
							style="width: 60px; height: 22px;"><input type="checkbox"
							id="selectall" /> <span style="vertical-align: top;"> - <a
								href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
									<em class="fa fa-trash-o fa-lg"></em>
							</a>
						</span></th>
						<%-- 						<computer:OrderComputer sort="${sessionScope.session.sort}"
							orderField="${sessionScope.session.orderField}" /> --%>
						<c:choose>
							<c:when test="${sessionScope.session.orderField != \"name\"}">
								<th><fmt:message key="label.dashboard.nameComputer" /><a
									href="?orderField=name&sort=ASC" class="fa fa-fw fa-sort"></a></th>
							</c:when>
							<c:when
								test="${sessionScope.session.orderField == \"name\" && sessionScope.session.sort == \"ASC\"}">
								<th><fmt:message key="label.dashboard.nameComputer" /><a
									href="?orderField=name&sort=DESC" class="fa fa-fw fa-sort-asc"></a></th>
							</c:when>
							<c:when
								test="${sessionScope.session.orderField == \"name\" && sessionScope.session.sort == \"DESC\"}">
								<th><fmt:message key="label.dashboard.nameComputer" /><a
									href="?orderField=name&sort=ASC" class="fa fa-fw fa-sort-desc"></a></th>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when
								test="${sessionScope.session.orderField != \"introduced\"}">
								<th><fmt:message key="label.dashboard.introduced" /><a
									href="?orderField=introduced&sort=ASC" class="fa fa-fw fa-sort"></a></th>
							</c:when>
							<c:when
								test="${sessionScope.session.orderField == \"introduced\" && sessionScope.session.sort == \"ASC\"}">
								<th><fmt:message key="label.dashboard.introduced" /><a
									href="?orderField=introduced&sort=DESC"
									class="fa fa-fw fa-sort-asc"></a></th>
							</c:when>
							<c:when
								test="${sessionScope.session.orderField == \"introduced\" && sessionScope.session.sort == \"DESC\"}">
								<th><fmt:message key="label.dashboard.introduced" /><a
									href="?orderField=introduced&sort=ASC"
									class="fa fa-fw fa-sort-desc"></a></th>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when
								test="${sessionScope.session.orderField != \"discontinued\"}">
								<th><fmt:message key="label.dashboard.discontinued" /><a
									href="?orderField=discontinued&sort=ASC"
									class="fa fa-fw fa-sort"></a></th>
							</c:when>
							<c:when
								test="${sessionScope.session.orderField == \"discontinued\" && sessionScope.session.sort == \"ASC\"}">
								<th><fmt:message key="label.dashboard.discontinued" /><a
									href="?orderField=discontinued&sort=DESC"
									class="fa fa-fw fa-sort-asc"></a></th>
							</c:when>
							<c:when
								test="${sessionScope.session.orderField == \"discontinued\" && sessionScope.session.sort == \"DESC\"}">
								<th><fmt:message key="label.dashboard.discontinued" /><a
									href="?orderField=discontinued&sort=ASC"
									class="fa fa-fw fa-sort-desc"></a></th>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when
								test="${sessionScope.session.orderField != \"company_id\"}">
								<th><fmt:message key="label.dashboard.company" /><a href="?orderField=company_id&sort=ASC"
									class="fa fa-fw fa-sort"></a></th>
							</c:when>
							<c:when
								test="${sessionScope.session.orderField == \"company_id\" && sessionScope.session.sort == \"ASC\"}">
								<th><fmt:message key="label.dashboard.company" /><a href="?orderField=company_id&sort=DESC"
									class="fa fa-fw fa-sort-asc"></a></th>
							</c:when>
							<c:when
								test="${sessionScope.session.orderField == \"company_id\" && sessionScope.session.sort == \"DESC\"}">
								<th><fmt:message key="label.dashboard.company" /><a href="?orderField=company_id&sort=ASC"
									class="fa fa-fw fa-sort-desc"></a></th>
							</c:when>
						</c:choose>
					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">

					<computer:Computer listComputer="${listComputer}" />

				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">

				<computer:NumberPage numberComputer="${numberComputer}"
					currentPage="${currentPage}" maxNumberPrint="${maxNumberPrint}" />

			</ul>
			<div class="btn-group btn-group-sm pull-right" role="group">
				<ul class="pagination">
					<li><a href="?numberEntry=10&page=${currentPage}">10</a></li>
					<li><a href="?numberEntry=25&page=${currentPage}">25</a></li>
					<li><a href="?numberEntry=50&page=${currentPage}">50</a></li>
				</ul>
			</div>
		</div>
	</footer>
</body>
</html>