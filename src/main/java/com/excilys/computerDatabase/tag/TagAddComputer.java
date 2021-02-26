package com.excilys.computerDatabase.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.excilys.computerDatabase.DTO.CompanyDTO;
import com.excilys.computerDatabase.DTO.ComputerFormAddDTO;

public class TagAddComputer extends SimpleTagSupport{
	
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
            	this.getJspContext().getOut().println(
            			"<input type=\"text\" class=\"form-control\" id=\"computerName\" "
                 + 		"placeholder=\"Computer name\" name=\"computerName\" maxlength=\"255\" onchange=\"verifNameComputer(this.value)\" "
                 + 		"value=\"" + this.computerFormAddDTO.getName() + " \" >"
                 +	"</div>"
                 +  "<div class=\"form-group\">"
                 +        "<label for=\"introduced\">Introduced date</label>"
                 +        "<input type=\"date\" class=\"form-control\" id=\"introduced\" "
                 + 			"placeholder=\"Introduced date\" name=\"dateIntroduced\" onchange=\"limitDate(this.value)\" "
                 + 			"value=\"" + this.computerFormAddDTO.getIntroduced() + "\">"
                 +    "</div>"
                 +    "<div class=\"form-group\">"
                 +       "<label for=\"discontinued\">Discontinued date</label>"
                 +       "<input type=\"date\" class=\"form-control\" id=\"discontinued\" placeholder=\"Discontinued date\"name=\"dateDiscontinued\" "
                 + 			"value=\"" + this.computerFormAddDTO.getDiscontinued() + "\">"
                 +    "</div>"
            			
            			);
            }else {
            	this.getJspContext().getOut().println(
            			"<input type=\"text\" class=\"form-control\" id=\"computerName\" "
                 + 		"placeholder=\"Computer name\" name=\"computerName\" maxlength=\"255\" onchange=\"verifNameComputer(this.value)\">"
                 +	"</div>"
                 +   "<div class=\"form-group\">"
                 +        "<label for=\"introduced\">Introduced date</label>"
                 +        "<input type=\"date\" class=\"form-control\" id=\"introduced\" "
                 + 			"placeholder=\"Introduced date\" name=\"dateIntroduced\" onchange=\"limitDate(this.value)\" "
                 + 			">"
                 +    "</div>"
                 +    "<div class=\"form-group\">"
                 +       "<label for=\"discontinued\">Discontinued date</label>"
                 +       "<input type=\"date\" class=\"form-control\" id=\"discontinued\" placeholder=\"Discontinued date\"name=\"dateDiscontinued\">"
                 +    "</div>"		
            			
            			);
            }
            this.getJspContext().getOut().println(
               "<div class=\"form-group\">"
            +        "<label for=\"companyId\">Company</label>"
            +        "<select class=\"form-control\" id=\"companyId\" name=\"companyName\">"		
			+			"<option value=\"\">---</option>");
			for(CompanyDTO company : this.listCompany){
				if(this.computerFormAddDTO != null && this.computerFormAddDTO.getCompanyId().compareTo(String.valueOf(company.getId())) == 0) {
					this.getJspContext().getOut().println("<option selected=\"selected\" value=\"" + company.getId() + "\">" + company.getName() + "</option>");
				}
				this.getJspContext().getOut().println("<option value=\"" + company.getId() + "\">" + company.getName() + "</option>");
			}
			this.getJspContext().getOut().println("</select></div>");
		} catch (IOException e) {
			throw new JspException ("I/O Error", e);
		}
	}
}
