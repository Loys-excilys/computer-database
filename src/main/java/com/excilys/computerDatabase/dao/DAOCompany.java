package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.data.Company;
import com.excilys.computerDatabase.error.ErrorDAOCompany;
import com.excilys.computerDatabase.error.ErrorDAOComputer;

public class DAOCompany{
	
	private static final int MAX_ENTRY_PRINT = 25;
	
	private static final String SELECT_COMPANY_NAME = "Select * FROM company WHERE name = ?";
	private static final String SELECT_COMPANY = "Select * FROM company LIMIT ? OFFSET ?";
	private static final String SELECT_COMPANY_NO_LIMIT = "Select * FROM company";
	
	private final String DELETE_COMPUTER_BY_COMPANY = "DELETE FROM computer WHERE company_id = ?";
	private final String DELETE_COMPANY_BY_ID = "DELETE FROM company WHERE id = ?";

	private static DAOCompany INSTANCE;
	
	private DBConnection dbConnection = DBConnection.getInstance();
	
	private DAOCompany(){}
	
	public static synchronized DAOCompany getInstance() {
		if(DAOCompany.INSTANCE == null) {
			DAOCompany.INSTANCE = new DAOCompany();
		}
	return DAOCompany.INSTANCE;
	}
		
	public Optional<Company> getCompany(String name) throws ErrorDAOCompany {
		try(Connection connection = this.dbConnection.getConnection();
	        	PreparedStatement query = connection.prepareStatement(SELECT_COMPANY_NAME);) {
        	query.setString(1, name);
            ResultSet result = query.executeQuery();
            result.next();
            Company company = new Company(result.getInt("id"),
 				   result.getString("name"));
            return Optional.of(company);
        } catch (SQLException e) {
            throw new ErrorDAOCompany();
        }
	}
	
	public List<Company> getListCompany(int page) throws ErrorDAOCompany {
		List<Company> resultList = new ArrayList<>();
		
		try(Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(SELECT_COMPANY);){
			query.setInt(1, MAX_ENTRY_PRINT);
			query.setInt(2, page*MAX_ENTRY_PRINT);
	        ResultSet result = query.executeQuery();
		   while(result.next()) {
			   resultList.add(new Company(result.getInt("id"), result.getString("name")));
		   }
		} catch (SQLException e) {
			throw new ErrorDAOCompany();
		}
		
		return resultList;
	}

	public List<Company> getListCompany() throws ErrorDAOCompany {
		List<Company> resultList = new ArrayList<>();
		
		try(Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(SELECT_COMPANY_NO_LIMIT);){
			
	        ResultSet result = query.executeQuery();
		   while(result.next()) {
			   resultList.add(new Company(result.getInt("id"), result.getString("name")));
		   }
		} catch (SQLException e) {
			throw new ErrorDAOCompany();
		}
		
		return resultList;
	}
	
	public void deleteCompanyById(int id) throws SQLException, ErrorDAOCompany {
		Connection connection = this.dbConnection.getConnection();
		try (PreparedStatement queryDeleteComputer = connection.prepareStatement(DELETE_COMPUTER_BY_COMPANY);
				PreparedStatement queryDeleteCompany = connection.prepareStatement(DELETE_COMPANY_BY_ID);){
			connection.setAutoCommit(false);
			
			queryDeleteComputer.setLong(1, id);
			queryDeleteComputer.executeUpdate();
			
			queryDeleteCompany.setLong(1, id);
			queryDeleteCompany.executeUpdate();
			
			connection.commit();
		}catch(ErrorDAOCompany errorSQL){
			try {
				connection.rollback();
		        } catch (ErrorDAOCompany excep) {
		          excep.printStackTrace();
		        }
			throw new ErrorDAOComputer();
		}finally {
			connection.close();
		}
	}
}
