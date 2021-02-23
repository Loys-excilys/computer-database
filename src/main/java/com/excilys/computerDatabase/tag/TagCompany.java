package com.excilys.computerDatabase.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.excilys.computerDatabase.data.Company;

public class TagCompany extends SimpleTagSupport{
	
	private List<Company> listCompany;
	
	public void setListCompany(List<Company> listCompany){
		this.listCompany = listCompany;
	}
	public void doTag() throws JspException {
		try {
			List<Company> listCompany = this.listCompany;
			for(Company company : listCompany){
				this.getJspContext().getOut().println("<option value=\"" + company.getId() + "\">" + company.getName() + "</option>");
			}
		} catch (IOException e) {
			throw new JspException ("I/O Error", e);
		}
	}
}
