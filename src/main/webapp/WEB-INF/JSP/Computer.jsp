<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="/WEB-INF/TagComputer.tld" prefix="computer" %>

<!DOCTYPE html>
<html lang="fr">
<head>
	<title>Computer Database</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="utf-8">
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="css/font-awesome.css" rel="stylesheet" media="screen">
	<link href="css/main.css" rel="stylesheet" media="screen">
	
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
	
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>
</head>
<body>

    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="ServletComputer"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
            <computer:NumberComputer numberComputer ="${ sessionScope.numberComputer }" />
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="ServletComputer" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                	<a class="btn btn-success" id="addComputer" href="ServletAddComputer">Add Computer</a>
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="ServletComputer" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
            <caption>tableau computer</caption>
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th id="delete" class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <em class="fa fa-trash-o fa-lg"></em>
                                    </a>
                            </span>
                        </th>
                        <computer:OrderComputer sort="${sessionScope.sort}" orderField="${sessionScope.orderField}"/>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                
                	<computer:Computer listComputer="${sessionScope.listComputer}"/>
                    
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
              
              	<computer:NumberPage numberComputer="${ sessionScope.numberComputer }" currentPage="${ sessionScope.currentPage }" maxNumberPrint="${ sessionScope.maxNumberPrint }"/>

	        </ul>
	        <div class="btn-group btn-group-sm pull-right" role="group" >
	        	<ul class="pagination">
	        		<li><a href="?numberEntry=10&page=${sessionScope.currentPage}">10</a></li>
		        	<li><a href="?numberEntry=25&page=${sessionScope.currentPage}">25</a></li>
		        	<li><a href="?numberEntry=50&page=${sessionScope.currentPage}">50</a></li>
	        	</ul>
		    </div>
		</div>
    </footer>
</body>
</html>