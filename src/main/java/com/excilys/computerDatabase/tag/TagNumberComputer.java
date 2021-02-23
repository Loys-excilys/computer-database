package com.excilys.computerDatabase.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TagNumberComputer extends SimpleTagSupport{
	
	private int numberComputer = 0;
	
	public void setNumberComputer(Integer numberComputer){
		this.numberComputer = numberComputer;
	}
	public void doTag() throws JspException {
		try {
			
			getJspContext().getOut().println (numberComputer + " Computers found");
		} catch (IOException e) {
			throw new JspException ("I/O Error", e);
		}
	}
}
