package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.Company;

public class DAOCompany{
	
	private static final int MAX_ENTRY_PRINT = 25;
	
	private static final String SELECT_COMPANY_NAME = "Select * FROM company WHERE name = ?";
	private static final String SELECT_COMPANY = "Select * FROM company LIMIT ? OFFSET ?";
	
	public DAOCompany(){}
		
	
	
	
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
}
