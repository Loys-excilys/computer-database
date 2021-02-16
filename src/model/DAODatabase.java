package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.Company;
import data.Computer;

class DAODatabase extends DAOEntity{
	
	private static final String SELECT_COMPUTER = "SELECT computer.id as id,"
			+ " computer.name as name,"
			+ " computer.introduced as introduced,"
			+ " computer.discontinued as discontinued,"
			+ " company.id as companyId,"
			+ " company.name as companyName"
			+ " FROM computer "
			+ " LEFT JOIN company ON computer.company_id = company.id;";
	
	private static final String SELECT_COMPUTER_ID = "SELECT computer.id as id,"
			+ " computer.name as name,"
			+ " computer.introduced as introduced,"
			+ " computer.discontinued as discontinued,"
			+ " company.id as companyId,"
			+ " company.name as companyName"
			+ " FROM computer "
			+ " LEFT JOIN company ON computer.company_id = company.id"
			+ " WHERE computer.id = ?;";
	
	private static final String INSERT_COMPUTER = 
			"INSERT INTO computer(name, introduced, discontinued, company_id)"
			+ " values(?,?,?,?) ";
	private static final String UPDATE_COMPUTER = "UPDATE computer"
			+ " SET name = ?,"
			+ " introduced = ?,"
			+ " discontinued = ?,"
			+ " company_id = ?"
			+ " WHERE id = ?";
	
	private static final String SELECT_COMPANY_NAME = "Select * FROM company WHERE name = ?";
	
	public DAODatabase(final Connection connection) throws SQLException {
		super(connection);
	}
	
	public Computer getComputer(int id) {
		Computer computer = null;
		try {
        	PreparedStatement query = getConnection().prepareStatement(SELECT_COMPUTER_ID);
        	query.setLong(1, id);
            ResultSet result = query.executeQuery();
            result.next();
            computer = new Computer(result.getInt("id"),
	 				   result.getString("name"),
	 				   result.getDate("introduced") != null ?
	 						   result.getDate("introduced").toLocalDate() : result.getDate("introduced"),
	 				   result.getDate("discontinued") != null ?
	 						   result.getDate("discontinued").toLocalDate() : result.getDate("discontinued"),
	 				   new Company(result.getLong("companyID"), result.getString("companyName")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return computer;
	}
	
	public Company getCompany(String name) {
		Company company = null;
		try {
        	PreparedStatement query = getConnection().prepareStatement(SELECT_COMPANY_NAME);
        	query.setString(1, name);
            ResultSet result = query.executeQuery();
            result.next();
            company = new Company(result.getInt("id"),
 				   result.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return company;
	}
	
	public List<Computer> getListComputer() {
		List<Computer> resultList = new ArrayList<>();
		
		try {
			PreparedStatement query = getConnection().prepareStatement(SELECT_COMPUTER);
	        ResultSet result = query.executeQuery();
	        while(result.next()) {
			   resultList.add(new Computer(result.getInt("id"),
	 				   result.getString("name"),
	 				   result.getDate("introduced") != null ?
	 						   result.getDate("introduced").toLocalDate() : result.getDate("introduced"),
	 				   result.getDate("discontinued") != null ?
	 						   result.getDate("discontinued").toLocalDate() : result.getDate("discontinued"),
	 				   new Company(result.getLong("companyID"), result.getString("companyName"))));
		   }
		} catch (SQLException e) {
		   e.printStackTrace();
		}
		
		return resultList;
	}
	
	public List<Company> getListCompany() {
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
	
	public void insertComputer(Computer computer) {
        if(computer != null) {
            try {
            	PreparedStatement query = getConnection().prepareStatement(INSERT_COMPUTER, Statement.RETURN_GENERATED_KEYS);
            	query.setString(1, computer.getName());
            	query.setDate(2, java.sql.Date.valueOf(computer.getIntroduced()));
            	query.setDate(3, java.sql.Date.valueOf(computer.getDiscontinued()));
            	query.setLong(4, computer.getCompany().getId());
                query.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	public void updateComputer(Computer computer) {
		if(computer != null) {
			try {
				PreparedStatement query = getConnection().prepareStatement(UPDATE_COMPUTER);
				query.setString(1, computer.getName());
            	query.setDate(2, java.sql.Date.valueOf(computer.getIntroduced()));
            	query.setDate(3, java.sql.Date.valueOf(computer.getDiscontinued()));
            	query.setLong(4, computer.getCompany().getId());
            	query.setLong(5, computer.getId());
                query.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
