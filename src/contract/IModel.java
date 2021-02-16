package contract;

import java.util.List;

import data.Computer;
import data.Company;

public interface IModel {
	
	public Computer getComputer(int id);

	public List<Computer> getListComputer();

	public List<Company> getListCompany();
	
	public void addComputer(Computer computer);
	
	public void updateComputer(Computer computer);

	public Company getCompany(String nameCompany);

	public void deleteComputer(int id);
}
