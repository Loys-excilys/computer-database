package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.Company;
import data.Computer;

class DAODatabase extends DAOEntity{
	
	public DAODatabase(final Connection connection) throws SQLException {
		super(connection);
	}
	
	public List<Computer> getComputer() {
		List<Computer> resultList = new ArrayList<>();
		ResultSet result = null;
		String requete = "SELECT computer.id as id,"
				+ " computer.name as name,"
				+ " computer.introduced as introduced,"
				+ " computer.discontinued as discontinued,"
				+ " company.name as company"
				+ " FROM computer "
				+ " LEFT JOIN company ON computer.company_id = company.id;";
		
		try {
		   Statement stmt = getConnection().createStatement();
		   result = stmt.executeQuery(requete);
		   while(result.next()) {
			   resultList.add(new Computer(result.getInt("id"), result.getString("name"), result.getDate("introduced"), result.getDate("discontinued"), result.getString("company")));
		   }
		} catch (SQLException e) {
		   //traitement de l'exception
		}
		
		return resultList;
	}
	
	public List<Company> getCompany() {
		List<Company> resultList = new ArrayList<>();
		ResultSet result = null;
		String requete = "SELECT * FROM company;";
		
		try {
		   Statement stmt = getConnection().createStatement();
		   result = stmt.executeQuery(requete);
		   while(result.next()) {
			   resultList.add(new Company(result.getInt("id"), result.getString("name")));
		   }
		} catch (SQLException e) {
		   //traitement de l'exception
		}
		
		return resultList;
	}
	
	public Computer getDetailComputer(int id) {
		ResultSet result = null;
		Computer resultComputer = null;
		String requete = "SELECT computer.id as id,"
				+ " computer.name as name,"
				+ " computer.introduced as introduced,"
				+ " computer.discontinued as discontinued,"
				+ " company.name as company"
				+ " FROM computer "
				+ " LEFT JOIN company ON computer.company_id = company.id"
				+ " WHERE computer.id = " + id + ";";
		try {
		   Statement stmt = getConnection().createStatement();
		   result = stmt.executeQuery(requete);
		   result.next();
		   resultComputer = new Computer(result.getInt("id"), result.getString("name"), result.getDate("introduced"), result.getDate("discontinued"), result.getString("company"));
		} catch (SQLException e) {
		}
		
		return resultComputer;
	}
}
