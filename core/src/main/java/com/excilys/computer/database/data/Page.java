package com.excilys.computer.database.data;

public class Page {

	private int numPage = 0;
	private int maxPrint = 25;
	private long maxComputer = 0;

	public void next() {
		this.numPage++;
	}

	public void previous() {
		if (this.numPage > 0) {
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
		this.maxPrint = maxPrint;
	}

	public int getMaxPrint() {
		return this.maxPrint;
	}

	public long getMaxComputer() {
		return maxComputer;
	}

	public void setMaxComputer(long maxComputer) {
		this.maxComputer = maxComputer;
	}
}
