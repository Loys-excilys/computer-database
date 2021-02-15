package contract;

import java.util.List;

import data.Company;
import data.Computer;

public interface IView {

	void printListComputer(List<Computer> listComputer);

	void printListCompany(List<Company> listCompany);
	
	public void askIdDetailComputer();
	
	void printDetailComputer(Computer computer);
	
	public void addComputer();

}
