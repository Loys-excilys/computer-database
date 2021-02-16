package contract;

import java.util.List;

import data.Company;
import data.Computer;

public interface IView {

	void printListComputer(List<Computer> listComputer);

	void printListCompany(List<Company> listCompany);
	
	public void printAskIdDetailComputer();
	
	void printDetailComputer(Computer computer);
	
	public void printAddComputer();
	
	public void printUpdateComputer();
	
	public void printDeleteComputer();

}
