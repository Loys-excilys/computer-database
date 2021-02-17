package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.data.Company;
import com.excilys.computerDatabase.data.Computer;

public class DAOComputer{
	
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
	
	public DAOComputer(){}
		
	
	public Computer getComputer(int id) {
		Computer computer = null;
		try(Connection connection = com.excilys.computerDatabase.dao.DBConnection.getInstance().getConnection()){
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
            System.out.println("Id : " + id + " doesn't exist");
        }
		return computer;
	}
	
	public List<Computer> getListComputer(int page) {
		List<Computer> resultList = new ArrayList<>();
		
		try (Connection connection = com.excilys.computerDatabase.dao.DBConnection.getInstance().getConnection()){
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
			System.out.println("erreur system : List Computer inaccessible");
		}
		
		return resultList;
	}
	
	public void insertComputer(Computer computer) {
        if(computer != null) {
            try (Connection connection = com.excilys.computerDatabase.dao.DBConnection.getInstance().getConnection()){
            	PreparedStatement query = connection.prepareStatement(INSERT_COMPUTER, Statement.RETURN_GENERATED_KEYS);
            	query.setString(1, computer.getName());
            	query.setDate(2, computer.getIntroduced() != null ? java.sql.Date.valueOf(computer.getIntroduced()) : null);
            	query.setDate(3, computer.getDiscontinued() != null ? java.sql.Date.valueOf(computer.getDiscontinued()) : null);
            	query.setLong(4, computer.getCompany().getId());
                query.executeUpdate();
            } catch (SQLException e) {
            	System.out.println("erreur system : insert computer impossible, vérifier les valeurs données");
            }
        }
    }
	
	public void updateComputer(Computer computer) {
		if(computer != null) {
			try (Connection connection = com.excilys.computerDatabase.dao.DBConnection.getInstance().getConnection()){
				PreparedStatement query = connection.prepareStatement(UPDATE_COMPUTER);
				query.setString(1, computer.getName());
            	query.setDate(2, computer.getIntroduced() != null ? java.sql.Date.valueOf(computer.getIntroduced()) : null);
            	query.setDate(3, computer.getDiscontinued() != null ? java.sql.Date.valueOf(computer.getDiscontinued()) : null);
            	query.setLong(4, computer.getCompany().getId());
            	query.setLong(5, computer.getId());
                query.executeUpdate();
			}catch(SQLException e){
				System.out.println("erreur system : update computer impossible, vérifier les valeurs données");
			}
		}
	}
	
	public void deleteComputer(int id) {
		try (Connection connection = com.excilys.computerDatabase.dao.DBConnection.getInstance().getConnection()){
			PreparedStatement query = connection.prepareStatement(DELETE_COMPUTER);
           	query.setLong(1, id);
            query.executeUpdate();
		}catch(SQLException e){
			System.out.println("erreur system : Delete computer impossible, vérifier les valeurs données");
		}
	}
}
