package com.excilys.computerDatabase.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.excilys.computerDatabase.DTO.CompanyDTO;

public class TagCompany extends SimpleTagSupport{
	
	private List<CompanyDTO> listCompany;
	
	public void setListCompany(List<CompanyDTO> listCompany){
		this.listCompany = listCompany;
	}
	public void doTag() throws JspException {
		try {
			this.getJspContext().getOut().println("<option value=\"\">---</option>");
			for(CompanyDTO company : this.listCompany){
				this.getJspContext().getOut().println("<option value=\"" + company.getId() + "\">" + company.getName() + "</option>");
			}
		} catch (IOException e) {
			throw new JspException ("I/O Error", e);
		}
	}
}
