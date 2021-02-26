package com.excilys.computerDatabase.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.excilys.computerDatabase.DTO.ComputerDTO;

public class TagComputer extends SimpleTagSupport{
	
	private List<ComputerDTO> listComputer;
	
	public void setListComputer(List<ComputerDTO> listComputer){
		this.listComputer = listComputer;
	}
	public void doTag() throws JspException {
		try {
			List<ComputerDTO> listComputer = this.listComputer;
			for(ComputerDTO computer : listComputer){
				getJspContext().getOut().println(
						"<tr>"
			    	+		"<td class=\"editMode\">"
			        +       	"<input type=\"checkbox\" name=\"cb\" class=\"cb\" value=\"0\">"
			        +   	"</td>"
			        +   	"<td>"
			        +       	"<a href=\"editComputer.html\" onclick=\"\">" + computer.getName() + "</a>"
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