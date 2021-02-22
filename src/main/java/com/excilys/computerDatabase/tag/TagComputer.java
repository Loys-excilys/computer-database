package com.excilys.computerDatabase.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TagComputer extends TagSupport {
	
	Object numberComputer;
	
	public void setNumberComputer(Object numberComputer) {
		this.numberComputer = numberComputer;
	}
	
	public int doStartTag() throws JspException {
		try {
			int num = (int) numberComputer;
			pageContext.getOut().println (num + " Computers found");
		} catch (IOException e) {
			throw new JspException ("I/O Error", e);
		}
		return SKIP_BODY;
	}
}