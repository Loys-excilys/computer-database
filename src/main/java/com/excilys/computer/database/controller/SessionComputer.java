package com.excilys.computer.database.controller;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionComputer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int currentPage = 1;
	private int maxNumberPrint = 25;
	private String search;
	private String orderField;
	private String sort;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getMaxNumberPrint() {
		return maxNumberPrint;
	}

	public void setMaxNumberPrint(int maxNumberPrint) {
		this.maxNumberPrint = maxNumberPrint;
	}
}
