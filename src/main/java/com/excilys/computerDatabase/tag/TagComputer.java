package com.excilys.computerDatabase.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.excilys.computerDatabase.data.Computer;

public class TagComputer extends SimpleTagSupport{
	
	private List<Computer> listComputer;
	
	public void setListComputer(List<Computer> listComputer){
		this.listComputer = listComputer;
	}
	public void doTag() throws JspException {
		try {
			List<Computer> listComputer = this.listComputer;
			for(Computer computer : listComputer){
				getJspContext().getOut().println(
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
		} catch (IOException e) {
			throw new JspException ("I/O Error", e);
		}
	}
}