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

public class DAOCompany{
	
	private static final int MAX_ENTRY_PRINT = 25;
	
	private static final String SELECT_COMPANY_NAME = "Select * FROM company WHERE name = ?";
	private static final String SELECT_COMPANY = "Select * FROM company LIMIT ? OFFSET ?";
	private static final String SELECT_COMPANY_NO_LIMIT = "Select * FROM company";
	
	private DBConnection dbConnection = DBConnection.getInstance();
	
	public DAOCompany(){}
		
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
}
