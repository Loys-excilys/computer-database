<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@ page import ="com.excilys.computerDatabase.data.Computer" %>
<%@ page import = "java.util.List" %>


<!DOCTYPE html>
<html>
<head>
	<title>Computer Database</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="utf-8">
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="css/font-awesome.css" rel="stylesheet" media="screen">
	<link href="css/main.css" rel="stylesheet" media="screen">
	
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>
</head>
<body>

    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="/computer-database/ServletComputer"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
            
                            	<%
	
		int numberComputer = (int) session.getAttribute("numberComputer");
			out.println(numberComputer + " Computers found");
	
	%>
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                <form action="/computer-database/ServletComputer" method="post">
                	<input type="submit" class="btn btn-success" id="addComputer" name="userChoiceAction" value="Add Computer"> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </form>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="#" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            Computer name
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                
                	<%
	
		List<Computer> listComputer = (List) session.getAttribute("listComputer");
		for(Computer computer : listComputer){
			out.println(
					"<tr>"
            	+		"<td class=\"editMode\">"
                +       	"<input type=\"checkbox\" name=\"cb\" class=\"cb\" value=\"0\">"
                +   	"</td>"
                +   	"<td>"
                +       	"<a href=\"editComputer.html\" onclick=\"\">" + computer.getName() + "</a>"
                +   	"</td>"
                +   	"<td>" + computer.getIntroduced() + "</td>"
                +   	"<td>" + computer.getDiscontinued() + "</td>"
                +   	"<td>" + computer.getCompany().getName() + "</td>"
                +	"</tr>"
					
					);
		}
	
	%>
                    
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
              
              <% 
              
              int numberPage = (numberComputer/25);
              int currentPage = (int) session.getAttribute("currentPage");
              if(currentPage < 4){
            	  out.println(
            			  	"<li><a href=\"?page=1\">1</a></li>"
            			+  	"<li><a href=\"?page=2\">2</a></li>"
            			+ 	"<li><a href=\"?page=3\">3</a></li>"
            			+ 	"<li><a href=\"?page=4\">4</a></li>"
            			+	"<li><a>...</a></li>"
            			+	"<li><a href=\"?page=" + numberPage + "\">" + numberPage + "</a></li>"
            			  );
              }else if(currentPage > numberPage-3){
            	  out.println(
          			  	"<li><a href=\"?page=1\">1</a></li>"
          			+	"<li><a>...</a></li>"
          			+  	"<li><a href=\"?page=" + (numberPage-3) + "\">" + (numberPage-3) + "</a></li>"
                  	+  	"<li><a href=\"?page=" + (numberPage-2) + "\">" + (numberPage-2) + "</a></li>"
                  	+ 	"<li><a href=\"?page=" + (numberPage-1) + "\">" + (numberPage-1) + "</a></li>"
          			+	"<li><a href=\"?page=" + numberPage + "\">" + numberPage + "</a></li>"
          			  );
            }else{
              	  out.println(
              			  	"<li><a href=\"?page=1\">1</a></li>"
              			+	"<li><a>...</a></li>"
              			+  	"<li><a href=\"?page=" + (currentPage-1) + "\">" + (currentPage-1) + "</a></li>"
              			+  	"<li><a href=\"?page=" + currentPage + "\">" + currentPage + "</a></li>"
              			+  	"<li><a href=\"?page=" + (currentPage+1) + "\">" + (currentPage+1) + "</a></li>"
              			+	"<li><a>...</a></li>"
              			+	"<li><a href=\"?page=" + numberPage + "\">" + numberPage + "</a></li>"
              			  );
            }
              
              %>
              	<li>
	                <a href="#" aria-label="Next">
	                    <span aria-hidden="true">&raquo;</span>
	                </a>
	            </li>
	        </ul>
		</div>
        <div class="btn-group btn-group-sm pull-right" role="group" >
            <button type="button" class="btn btn-default">10</button>
            <button type="button" class="btn btn-default">50</button>
            <button type="button" class="btn btn-default">100</button>
        </div>

    </footer>
</body>
</html>