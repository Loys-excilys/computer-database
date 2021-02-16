package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.Company;
import data.Computer;

public class DAODatabase{
	
	private static final int MAX_ENTRY_PRINT = 25;
	
	private static final String SELECT_COMPUTER = "SELECT computer.id as id,"
			+ " computer.name as name,"
			+ " computer.introduced as introduced,"
			+ " computer.discontinued as discontinued,"
			+ " company.id as companyId,"
			+ " company.name as companyName"
			+ " FROM computer "
			+ " LEFT JOIN company ON computer.company_id = company.id"
			+ " LIMIT ? OFFSET ?";
	
	private static final String SELECT_COMPUTER_ID = "SELECT computer.id as id,"
			+ " computer.name as name,"
			+ " computer.introduced as introduced,"
			+ " computer.discontinued as discontinued,"
			+ " company.id as companyId,"
			+ " company.name as companyName"
			+ " FROM computer "
			+ " LEFT JOIN company ON computer.company_id = company.id"
			+ " WHERE computer.id = ?";
	
	private static final String INSERT_COMPUTER = 
			"INSERT INTO computer(name, introduced, discontinued, company_id)"
			+ " values(?,?,?,?) ";
	private static final String UPDATE_COMPUTER = "UPDATE computer"
			+ " SET name = ?,"
			+ " introduced = ?,"
			+ " discontinued = ?,"
			+ " company_id = ?"
			+ " WHERE id = ?";
	
	private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE id = ? ";
	
	private static final String SELECT_COMPANY_NAME = "Select * FROM company WHERE name = ?";
	private static final String SELECT_COMPANY = "Select * FROM company LIMIT ? OFFSET ?";
	
	public DAODatabase() throws SQLException {}
		
	
	public Computer getComputer(int id) {
		Computer computer = null;
		try(Connection connection = DAO.DBConnection.getInstance().getConnection()){
        	PreparedStatement query = connection.prepareStatement(SELECT_COMPUTER_ID);
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
		try(Connection connection = DAO.DBConnection.getInstance().getConnection()) {
        	PreparedStatement query = connection.prepareStatement(SELECT_COMPANY_NAME);
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
	
	public List<Computer> getListComputer(int page) {
		List<Computer> resultList = new ArrayList<>();
		
		try (Connection connection = DAO.DBConnection.getInstance().getConnection()){
			PreparedStatement query = connection.prepareStatement(SELECT_COMPUTER);
			query.setInt(1, MAX_ENTRY_PRINT);
			query.setInt(2, page*MAX_ENTRY_PRINT);
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
	
	public List<Company> getListCompany(int page) {
		List<Company> resultList = new ArrayList<>();
		
		try(Connection connection = DAO.DBConnection.getInstance().getConnection()){
			PreparedStatement query = connection.prepareStatement(SELECT_COMPANY);
			query.setInt(1, MAX_ENTRY_PRINT);
			query.setInt(2, page*MAX_ENTRY_PRINT);
	        ResultSet result = query.executeQuery();
		   while(result.next()) {
			   resultList.add(new Company(result.getInt("id"), result.getString("name")));
		   }
		} catch (SQLException e) {
		   e.printStackTrace();
		}
		
		return resultList;
	}
	
	public void insertComputer(Computer computer) {
        if(computer != null) {
            try (Connection connection = DAO.DBConnection.getInstance().getConnection()){
            	PreparedStatement query = connection.prepareStatement(INSERT_COMPUTER, Statement.RETURN_GENERATED_KEYS);
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
			try (Connection connection = DAO.DBConnection.getInstance().getConnection()){
				PreparedStatement query = connection.prepareStatement(UPDATE_COMPUTER);
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
	
	public void deleteComputer(int id) {
		try (Connection connection = DAO.DBConnection.getInstance().getConnection()){
			PreparedStatement query = connection.prepareStatement(DELETE_COMPUTER);
           	query.setLong(1, id);
            query.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
