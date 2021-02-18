package com.excilys.computerDatabase.view;

public class Page {

	private int numPage = 0;
	
	public Page() {}
	
	public void next() {
		this.numPage++;
	}
	
	public void previous() {
		this.numPage--;
	}
	
	public int getPage() {
		return this.numPage;
	}
}
