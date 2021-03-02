package com.excilys.computerDatabase.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TagNumberPage extends SimpleTagSupport{
	
	private int numberComputer = 0;
	private int currentPage = 0;
	private int maxNumberPrint = 25;
	
	public void setNumberComputer(Integer numberComputer){
		this.numberComputer = numberComputer;
	}
	
	public void setCurrentPage(Integer currentPage){
		this.currentPage = currentPage;
	}
	
	public void setMaxNumberPrint(Integer maxNumberPrint) {
		this.maxNumberPrint = maxNumberPrint;
	}
	
	public void doTag() throws JspException {
		try {
            int numberPage = (numberComputer/maxNumberPrint)+1;
            int currentPage = this.currentPage;
            if(currentPage >= 1) {
	            getJspContext().getOut().println(
	            			"<li>"
	            		+	"<a href=\"?page=" + currentPage + "\" aria-label=\"Previous\">"
		              	+	"<span aria-hidden=\"true\">&laquo;</span>"
		              	+	"</a>"
		          		+	"</li>"
	            		);
            }
            if(currentPage < 3){
            	getJspContext().getOut().println(
          			  	"<li><a href=\"?page=1\">1</a></li>"
          			+  	"<li><a href=\"?page=2\">2</a></li>"
          			+ 	"<li><a href=\"?page=3\">3</a></li>"
          			+ 	"<li><a href=\"?page=4\">4</a></li>"
          			+	"<li><a>...</a></li>"
          			+	"<li><a href=\"?page=" + numberPage + "\">" + numberPage + "</a></li>"
          			  );
            }else if(currentPage > numberPage-3){
            	getJspContext().getOut().println(
        			  	"<li><a href=\"?page=1\">1</a></li>"
        			+	"<li><a>...</a></li>"
        			+  	"<li><a href=\"?page=" + (numberPage-3) + "\">" + (numberPage-3) + "</a></li>"
                	+  	"<li><a href=\"?page=" + (numberPage-2) + "\">" + (numberPage-2) + "</a></li>"
                	+ 	"<li><a href=\"?page=" + (numberPage-1) + "\">" + (numberPage-1) + "</a></li>"
        			+	"<li><a href=\"?page=" + numberPage + "\">" + numberPage + "</a></li>"
        			  );
          }else{
        	  getJspContext().getOut().println(
            			  	"<li><a href=\"?page=1\">1</a></li>"
            			+	"<li><a>...</a></li>"
            			+  	"<li><a href=\"?page=" + currentPage + "\">" + currentPage + "</a></li>"
            			+  	"<li><a href=\"?page=" + (currentPage + 1) + "\">" + (currentPage + 1) + "</a></li>"
            			+  	"<li><a href=\"?page=" + (currentPage + 2) + "\">" + (currentPage + 2) + "</a></li>"
            			+	"<li><a>...</a></li>"
            			+	"<li><a href=\"?page=" + numberPage + "\">" + numberPage + "</a></li>"
            			  );
          }
          if(currentPage < numberPage) {
	          getJspContext().getOut().println(
	        		  			"<li>"
	        		  		+	"<a href=\"?page=" + (currentPage + 2) + "\" aria-label=\"Next\">"
	        		  		+	"<span aria-hidden=\"true\">&raquo;</span>"
	        		  		+ 	"</a>"
	        		  		+	"</li>"
	        		  		);
          }
		} catch (IOException e) {
			throw new JspException ("I/O Error", e);
		}
	}
}
