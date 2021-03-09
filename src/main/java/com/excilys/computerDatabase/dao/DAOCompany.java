package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.data.Company;
import com.excilys.computerDatabase.error.ErrorDAOCompany;

@Component
@Scope("singleton")
public class DAOCompany{
	
	private static final int MAX_ENTRY_PRINT = 25;
	
	private static final String SELECT_COMPANY_NAME = "Select * FROM company WHERE name = ?";
	private static final String SELECT_COMPANY = "Select * FROM company LIMIT ? OFFSET ?";
	private static final String SELECT_COMPANY_NO_LIMIT = "Select * FROM company";
	
	private static final String DELETE_COMPUTER_BY_COMPANY = "DELETE FROM computer WHERE company_id = ?";
	private static final String DELETE_COMPANY_BY_ID = "DELETE FROM company WHERE id = ?";
	
	@Autowired
	private DBConnection dbConnection;
		
	public Optional<Company> getCompany(String name){
		Optional<Company> company = Optional.empty();
		try(Connection connection = this.dbConnection.getConnection();
	        	PreparedStatement query = connection.prepareStatement(SELECT_COMPANY_NAME);) {
        	query.setString(1, name);
            ResultSet result = query.executeQuery();
            result.next();
            company = Optional.ofNullable(new Company(result.getInt("id"),
 				   result.getString("name")));
            return company;
        } catch (SQLException exceptionSQL) {
            new ErrorDAOCompany().connectionLost(exceptionSQL);
        }
		return company;
	}
	
	public List<Company> getListCompany(int page){
		List<Company> resultList = new ArrayList<>();
		
		try(Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(SELECT_COMPANY);){
			query.setInt(1, MAX_ENTRY_PRINT);
			query.setInt(2, page*MAX_ENTRY_PRINT);
	        ResultSet result = query.executeQuery();
		   while(result.next()) {
			   resultList.add(new Company(result.getInt("id"), result.getString("name")));
		   }
		} catch (SQLException exceptionSQL) {
			new ErrorDAOCompany().connectionLost(exceptionSQL);
		}
		
		return resultList;
	}

	public List<Company> getListCompany(){
		List<Company> resultList = new ArrayList<>();
		
		try(Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(SELECT_COMPANY_NO_LIMIT);){
			
	        ResultSet result = query.executeQuery();
		   while(result.next()) {
			   resultList.add(new Company(result.getInt("id"), result.getString("name")));
		   }
		} catch (SQLException exceptionSQL) {
			new ErrorDAOCompany().connectionLost(exceptionSQL);
		}
		
		return resultList;
	}
	
	public void deleteCompanyById(int id){
		Connection connection = null;
		try {
			connection = this.dbConnection.getConnection();
		} catch (SQLException errorSQL) {
			new ErrorDAOCompany().connectionLost(errorSQL);
		}
		try (PreparedStatement queryDeleteComputer = connection.prepareStatement(DELETE_COMPUTER_BY_COMPANY);
				PreparedStatement queryDeleteCompany = connection.prepareStatement(DELETE_COMPANY_BY_ID);){
			connection.setAutoCommit(false);
			
			queryDeleteComputer.setLong(1, id);
			queryDeleteComputer.executeUpdate();
			
			queryDeleteCompany.setLong(1, id);
			queryDeleteCompany.executeUpdate();
			
			connection.commit();
		}catch(SQLException errorSQL){
			try {
				connection.rollback();
		        } catch (SQLException roolbackExcpetion) {
		        	new ErrorDAOCompany().connectionLost(roolbackExcpetion);
		        }
			new ErrorDAOCompany().idInvalid(errorSQL);
		}finally {
			try {
				connection.close();
			} catch (SQLException errorSQL) {
				new ErrorDAOCompany().connectionLost(errorSQL);
			}
		}
	}
}
