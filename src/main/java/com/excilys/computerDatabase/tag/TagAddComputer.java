package com.excilys.computerDatabase.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.excilys.computerDatabase.dto.CompanyDTO;
import com.excilys.computerDatabase.dto.ComputerFormAddDTO;

public class TagAddComputer extends SimpleTagSupport{
	
	private final static String COMPUTER_NAME = "<input type=\"text\" class=\"form-control\" id=\"computerName\" "
            + 		"placeholder=\"Computer name\" name=\"computerName\" maxlength=\"255\" required = \"required\" onchange=\"verifNameComputer(this.value)\" ";
	private final static String INTRODUCED_DATE = " \" >"
            +	"</div>"
            +  "<div class=\"form-group\">"
            +        "<label for=\"introduced\">Introduced date</label>"
            +        "<input type=\"date\" class=\"form-control\" id=\"introduced\" "
            + 			"placeholder=\"Introduced date\" name=\"dateIntroduced\" onchange=\"limitMinDate(this.value)\" "
            + 			"value=\"";
	
	private final static String DISCONTINUED_DATE = "\">"
            +    "</div>"
            +    "<div class=\"form-group\">"
            +       "<label for=\"discontinued\">Discontinued date</label>"
            +       "<input type=\"date\" class=\"form-control\" id=\"discontinued\" placeholder=\"Discontinued date\"name=\"dateDiscontinued\" "
            + 			"onchange=\"limitMaxDate(this.value)\" value=\"";
	
	
	private List<CompanyDTO> listCompany;
	private ComputerFormAddDTO computerFormAddDTO;
	
	public void setListCompany(List<CompanyDTO> listCompany){
		this.listCompany = listCompany;
	}
	public void setComputerFormAddDTO(ComputerFormAddDTO computerFormAddDTO){
		this.computerFormAddDTO = computerFormAddDTO;
	}
	
	public void doTag() throws JspException {
		try {
			this.getJspContext().getOut().println(
                "<div class=\"form-group\">"
            +   "<label for=\"computerName\">Computer name</label>");
                		
            if(this.computerFormAddDTO != null) {
            	this.getJspContext().getOut().println(COMPUTER_NAME + "value=\"" + this.computerFormAddDTO.getName() +
            			INTRODUCED_DATE + this.computerFormAddDTO.getIntroduced() + DISCONTINUED_DATE + this.computerFormAddDTO.getDiscontinued());
            }else {
            	this.getJspContext().getOut().println(COMPUTER_NAME + INTRODUCED_DATE + DISCONTINUED_DATE );
            }
            this.getJspContext().getOut().println(
            	"\">"
            +    "</div>"
            +  "<div class=\"form-group\">"
            +        "<label for=\"companyId\">Company</label>"
            +        "<select class=\"form-control\" id=\"companyId\" name=\"companyName\">"		
			+			"<option value=\"\">---</option>");
			for(CompanyDTO company : this.listCompany){
				if(this.computerFormAddDTO != null && this.computerFormAddDTO.getCompanyId().compareTo(String.valueOf(company.getId())) == 0) {
					this.getJspContext().getOut().println("<option selected=\"selected\" value=\"" + company.getId() + "\">" + company.getName() + "</option>");
				}else {
					this.getJspContext().getOut().println("<option value=\"" + company.getId() + "\">" + company.getName() + "</option>");
				}
			}
			this.getJspContext().getOut().println("</select></div>");
		} catch (IOException e) {
			throw new JspException ("I/O Error", e);
		}
	}
}
