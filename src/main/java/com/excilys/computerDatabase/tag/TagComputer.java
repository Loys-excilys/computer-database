package com.excilys.computerDatabase.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.excilys.computerDatabase.dto.ComputerDTO;

public class TagComputer extends SimpleTagSupport{
	
	private List<ComputerDTO> listComputer;
	
	public void setListComputer(List<ComputerDTO> listComputer){
		this.listComputer = listComputer;
	}
	public void doTag() throws JspException {
		try {
			for(ComputerDTO computer : this.listComputer){
				getJspContext().getOut().println(
						"<tr>"
			    	+		"<td class=\"editMode\">"
			        +       	"<input type=\"checkbox\" name=\"cb\" class=\"cb\" value=\""+ computer.getId() + "\">"
			        +   	"</td>"
			        +   	"<td>"
			        +       	"<a href=\"ServletUpdateComputer?id=" + computer.getId() + "\">" + computer.getName() + "</a>"
			        +   	"</td>"
			        +   	"<td>" + (computer.getIntroduced() != null ? computer.getIntroduced() : "") + "</td>"
			        +   	"<td>" + (computer.getDiscontinued() != null ? computer.getDiscontinued() : "") + "</td>"
			        +   	"<td>" + (computer.getCompanyName()!= null ? computer.getCompanyName() : "") + "</td>"
			        +	"</tr>"	
						);
			}
		} catch (IOException e) {
			throw new JspException ("I/O Error", e);
		}
	}
}