package com.excilys.computerDatabase.view;

public class Page {

	private int numPage = 1;
	
	public Page() {}
	
	public void next() {
		this.numPage++;
	}
	
	public void previous() {
		if(this.numPage > 0) {
			this.numPage--;
		}
	}
	public void setPage(int page) {
		this.numPage = page;
	}
	
	public int getPage() {
		return this.numPage;
	}
}
