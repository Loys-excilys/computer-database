package com.excilys.computerDatabase.data;

public class Page {

	private int numPage = 0;
	private int MaxPrint = 25;
	private int maxComputer = 0;
	
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

	public void setMaxPrint(int maxPrint) {
		MaxPrint = maxPrint;
	}
	
	public int getMaxPrint() {
		return this.MaxPrint;
	}

	public int getMaxComputer() {
		return maxComputer;
	}

	public void setMaxComputer(int maxComputer) {
		this.maxComputer = maxComputer;
	}
}
