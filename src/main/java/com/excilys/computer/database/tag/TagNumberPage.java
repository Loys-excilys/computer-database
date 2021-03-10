package com.excilys.computer.database.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TagNumberPage extends SimpleTagSupport {

	private int numberComputer = 0;
	private int currentPage = 0;
	private int maxNumberPrint = 25;

	public void setNumberComputer(Integer numberComputer) {
		this.numberComputer = numberComputer;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setMaxNumberPrint(Integer maxNumberPrint) {
		this.maxNumberPrint = maxNumberPrint;
	}

	public void doTag() throws JspException {
		try {
			int numberPage;
			if (numberComputer < maxNumberPrint) {
				numberPage = 1;
			} else if ((numberComputer % maxNumberPrint) != 0) {
				numberPage = (numberComputer / maxNumberPrint) + 1;
			} else {
				numberPage = (numberComputer / maxNumberPrint);
			}

			if (this.currentPage > 1) {
				getJspContext().getOut()
						.println("<li>" + "<a href=\"?page=" + this.currentPage + "\" aria-label=\"Previous\">"
								+ "<span aria-hidden=\"true\">&laquo;</span>" + "</a>" + "</li>");
			}
			if (numberPage <= 5) {
				for (int i = 1; i <= numberPage; i++) {
					getJspContext().getOut().println("<li><a href=\"?page=" + i + "\">" + i + "</a></li>");
				}
			} else if (this.currentPage < 4) {
				getJspContext().getOut()
						.println("<li><a href=\"?page=1\">1</a></li>" + "<li><a href=\"?page=2\">2</a></li>"
								+ "<li><a href=\"?page=3\">3</a></li>" + "<li><a href=\"?page=4\">4</a></li>"
								+ "<li><a>...</a></li>" + "<li><a href=\"?page=" + numberPage + "\">" + numberPage
								+ "</a></li>");
			} else if (this.currentPage > numberPage - 3) {
				getJspContext().getOut()
						.println("<li><a href=\"?page=1\">1</a></li>" + "<li><a>...</a></li>" + "<li><a href=\"?page="
								+ (numberPage - 3) + "\">" + (numberPage - 3) + "</a></li>" + "<li><a href=\"?page="
								+ (numberPage - 2) + "\">" + (numberPage - 2) + "</a></li>" + "<li><a href=\"?page="
								+ (numberPage - 1) + "\">" + (numberPage - 1) + "</a></li>" + "<li><a href=\"?page="
								+ numberPage + "\">" + numberPage + "</a></li>");
			} else {
				getJspContext().getOut()
						.println("<li><a href=\"?page=1\">1</a></li>" + "<li><a>...</a></li>" + "<li><a href=\"?page="
								+ (this.currentPage - 1) + "\">" + (this.currentPage - 1) + "</a></li>"
								+ "<li><a href=\"?page=" + this.currentPage + "\">" + this.currentPage + "</a></li>"
								+ "<li><a href=\"?page=" + (this.currentPage + 1) + "\">" + (this.currentPage + 1)
								+ "</a></li>" + "<li><a>...</a></li>" + "<li><a href=\"?page=" + numberPage + "\">"
								+ numberPage + "</a></li>");
			}
			if (this.currentPage < numberPage) {
				getJspContext().getOut().println("<li>" + "<a href=\"?page=" + (this.currentPage + 1)
						+ "\" aria-label=\"Next\">" + "<span aria-hidden=\"true\">&raquo;</span>" + "</a>" + "</li>");
			}
		} catch (IOException e) {
			throw new JspException("I/O Error", e);
		}
	}
}
