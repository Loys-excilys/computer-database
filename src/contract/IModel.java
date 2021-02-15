package contract;

import java.util.List;

import data.Computer;
import data.Company;

public interface IModel {

	List<Computer> getListComputer();

	List<Company> getListCompany();
	
	public Computer getDetailComputer(int id);

}
