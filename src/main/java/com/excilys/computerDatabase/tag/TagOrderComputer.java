package com.excilys.computerDatabase.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TagOrderComputer extends SimpleTagSupport{

	private String orderField;
	private String sort;
	
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public void doTag() throws JspException {
		String reverseSort = "";
		if(sort.compareTo("ASC") == 0) {
			reverseSort = "DESC";
		}else{
			reverseSort = "ASC";
		}
        try {
			getJspContext().getOut().println(
				"<th>"
	        +	"Computer name");
			if(orderField.compareTo("name") == 0) {
				getJspContext().getOut().println("<a href=\"?orderField=name&sort="+ reverseSort + "\" class=\"fa fa-fw fa-sort-" + sort.toLowerCase() + "\"></a>");
			}else {
				getJspContext().getOut().println("<a href=\"?orderField=name&sort=ASC\" class=\"fa fa-fw fa-sort\"></a>");
			}
			
			getJspContext().getOut().println(
				"</th>"
			+	"<th>"
	        +	"Introduced date");
			if(orderField.compareTo("introduced") == 0) {
				getJspContext().getOut().println("<a href=\"?orderField=introduced&sort="+ reverseSort + "\" class=\"fa fa-fw fa-sort-" + sort.toLowerCase() + "\"></a>");
			}else {
				getJspContext().getOut().println("<a href=\"?orderField=introduced&sort=ASC\" class=\"fa fa-fw fa-sort\"></a>");
			}
			getJspContext().getOut().println(
				"</th>"
			+	"<th>"
	        +	"Discontinued date");
			if(orderField.compareTo("discontinued") == 0) {
				getJspContext().getOut().println("<a href=\"?orderField=discontinued&sort="+ reverseSort + "\" class=\"fa fa-fw fa-sort-" + sort.toLowerCase() + "\"></a>");
			}else {
				getJspContext().getOut().println("<a href=\"?orderField=discontinued&sort=ASC\" class=\"fa fa-fw fa-sort\"></a>");
			}
			getJspContext().getOut().println(
				"</th>"
			+	"<th>"
			+	"Company");

			if(orderField.compareTo("company_id") == 0) {
				getJspContext().getOut().println("<a href=\"?orderField=company_id&sort="+ reverseSort + "\" class=\"fa fa-fw fa-sort-" + sort.toLowerCase() + "\"></a>");
			}else {
				getJspContext().getOut().println("<a href=\"?orderField=company_id&sort=ASC\" class=\"fa fa-fw fa-sort\"></a>");
			}
	        getJspContext().getOut().println("</th>");
		} catch (IOException e) {
			throw new JspException ("I/O Error", e);
		}
	}
}
