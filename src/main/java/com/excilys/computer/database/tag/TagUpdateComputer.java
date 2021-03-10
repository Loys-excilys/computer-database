package com.excilys.computer.database.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.excilys.computer.database.dto.CompanyDTO;
import com.excilys.computer.database.dto.ComputerDTO;

public class TagUpdateComputer extends SimpleTagSupport {

	private static final String COMPUTER_NAME = "<input type=\"text\" class=\"form-control\" id=\"computerName\" "
			+ "placeholder=\"Computer name\" name=\"computerName\" maxlength=\"255\" required = \"required\" onchange=\"verifNameComputer(this.value)\" ";
	private static final String INTRODUCED_DATE = " \" >" + "</div>" + "<div class=\"form-group\">"
			+ "<label for=\"introduced\">Introduced date</label>"
			+ "<input type=\"date\" class=\"form-control\" id=\"introduced\" "
			+ "placeholder=\"Introduced date\" name=\"dateIntroduced\" onchange=\"limitMinDate(this.value)\" "
			+ "value=\"";

	private static final String DISCONTINUED_DATE = "\">" + "</div>" + "<div class=\"form-group\">"
			+ "<label for=\"discontinued\">Discontinued date</label>"
			+ "<input type=\"date\" class=\"form-control\" id=\"discontinued\" placeholder=\"Discontinued date\"name=\"dateDiscontinued\" "
			+ "onchange=\"limitMaxDate(this.value)\" value=\"";

	private List<CompanyDTO> listCompany;
	private ComputerDTO computerDTO;

	public void setListCompany(List<CompanyDTO> listCompany) {
		this.listCompany = listCompany;
	}

	public void setComputerFormUpdateDTO(ComputerDTO computerDTO) {
		this.computerDTO = computerDTO;
	}

	public void doTag() throws JspException {
		try {
			this.getJspContext().getOut()
					.println("<div class=\"form-group\">" + "<label for=\"computerName\">Computer name</label>"

							+ COMPUTER_NAME + "value=\"" + this.computerDTO.getName() + INTRODUCED_DATE
							+ this.computerDTO.getIntroduced() + DISCONTINUED_DATE + this.computerDTO.getDiscontinued()
							+ "\">" + "</div>" + "<div class=\"form-group\">"
							+ "<label for=\"companyId\">Company</label>"
							+ "<select class=\"form-control\" id=\"companyId\" name=\"companyName\">"
							+ "<option value=\"\">---</option>");
			for (CompanyDTO company : this.listCompany) {
				if (this.computerDTO != null
						&& this.computerDTO.getCompanyName().compareTo(String.valueOf(company.getName())) == 0) {
					this.getJspContext().getOut().println("<option selected=\"selected\" value=\"" + company.getId()
							+ "\">" + company.getName() + "</option>");
				} else {
					this.getJspContext().getOut()
							.println("<option value=\"" + company.getId() + "\">" + company.getName() + "</option>");
				}
			}
			this.getJspContext().getOut().println("</select></div>");
		} catch (IOException e) {
			throw new JspException("I/O Error", e);
		}
	}
}
