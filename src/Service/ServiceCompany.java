package Service;

import java.sql.SQLException;

import DAO.DAODatabase;
import data.Company;

public class ServiceCompany {
	private DAODatabase database;
	private Page page = new Page();
	
	public ServiceCompany() {
		try {
			this.database = new DAODatabase();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Company getCompany(String nameCompany) {
		return this.database.getCompany(nameCompany);
	}
}
