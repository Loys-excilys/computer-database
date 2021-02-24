package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.data.Company;
import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.data.ComputerFactory;
import com.excilys.computerDatabase.error.ErrorDAOComputer;
import com.excilys.computerDatabase.error.ErrorSaisieUser;

public class DAOComputer{
		
	private int maxPrint = 25;
	
	private final String SELECT_COMPUTER = "SELECT computer.id as id,"
			+ " computer.name as name,"
			+ " computer.introduced as introduced,"
			+ " computer.discontinued as discontinued,"
			+ " company.id as companyId,"
			+ " company.name as companyName"
			+ " FROM computer "
			+ " LEFT JOIN company ON computer.company_id = company.id"
			+ " LIMIT ? OFFSET ?";
	
	private final String SELECT_COMPUTER_ID = "SELECT computer.id as id,"
			+ " computer.name as name,"
			+ " computer.introduced as introduced,"
			+ " computer.discontinued as discontinued,"
			+ " company.id as companyId,"
			+ " company.name as companyName"
			+ " FROM computer "
			+ " LEFT JOIN company ON computer.company_id = company.id"
			+ " WHERE computer.id = ?";
	
	private final String INSERT_COMPUTER = 
			"INSERT INTO computer(name, introduced, discontinued, company_id)"
			+ " values(?,?,?,?) ";
	private final String UPDATE_COMPUTER = "UPDATE computer"
			+ " SET name = ?,"
			+ " introduced = ?,"
			+ " discontinued = ?,"
			+ " company_id = ?"
			+ " WHERE id = ?";
	
	private final String DELETE_COMPUTER = "DELETE FROM computer WHERE id = ? ";
	
	private final String COUNT_COMPUTER = "SELECT COUNT(*) FROM computer";
	
	private DBConnection dbConnection= DBConnection.getInstance();
	
	public DAOComputer(){}
	
	
	public int getNumberComputer() throws ErrorDAOComputer {
		int numberComputer = 0;
		try(Connection connection = this.dbConnection.getConnection()){
        	PreparedStatement query = connection.prepareStatement(COUNT_COMPUTER);
            ResultSet result = query.executeQuery();
            result.next();
            numberComputer = result.getInt(1);
            
        } catch (SQLException errorSQL) {
        	errorSQL.printStackTrace();
        	throw new ErrorDAOComputer();
        }
		return numberComputer;
	}
	
	
	
	public Optional<Computer> getComputer(int id) throws ErrorDAOComputer, ErrorSaisieUser {
		Computer computer = null;
		try(Connection connection = this.dbConnection.getConnection()){
        	PreparedStatement query = connection.prepareStatement(SELECT_COMPUTER_ID);
        	query.setLong(1, id);
            ResultSet result = query.executeQuery();
            result.next();
            computer = new ComputerFactory().getComputer(result.getInt("id"),
	 				   result.getString("name"),
	 				   result.getDate("introduced") != null ?
	 						   result.getDate("introduced").toLocalDate() : result.getDate("introduced"),
	 				   result.getDate("discontinued") != null ?
	 						   result.getDate("discontinued").toLocalDate() : result.getDate("discontinued"),
	 				   new Company(result.getLong("companyID"), result.getString("companyName")));
        } catch (SQLException errorSQL) {
        	throw new ErrorDAOComputer();
        }
		return Optional.ofNullable(computer);
	}
	
	public List<Computer> getListComputer(int page) throws ErrorDAOComputer, ErrorSaisieUser {
		List<Computer> resultList = new ArrayList<>();
		
		try (Connection connection = this.dbConnection.getConnection()){
			PreparedStatement query = connection.prepareStatement(SELECT_COMPUTER);
			query.setInt(1, maxPrint);
			query.setInt(2, page*maxPrint);
	        ResultSet result = query.executeQuery();
	        while(result.next()) {
			   resultList.add(new ComputerFactory().getComputer(result.getInt("id"),
	 				   result.getString("name"),
	 				   result.getDate("introduced") != null ?
	 						   result.getDate("introduced").toLocalDate() : result.getDate("introduced"),
	 				   result.getDate("discontinued") != null ?
	 						   result.getDate("discontinued").toLocalDate() : result.getDate("discontinued"),
	 				   new Company(result.getLong("companyID"), result.getString("companyName"))));
		   }
		} catch (SQLException e) {
			throw new ErrorDAOComputer();
		}
		
		return resultList;
	}
	
	public long insertComputer(Computer computer) throws ErrorDAOComputer {
		long newKey = 0;
        if(computer != null) {
            try (Connection connection = this.dbConnection.getConnection()){
            	PreparedStatement query = connection.prepareStatement(INSERT_COMPUTER, Statement.RETURN_GENERATED_KEYS);
            	query.setString(1, computer.getName());
            	query.setDate(2, computer.getIntroduced() != null ? java.sql.Date.valueOf(computer.getIntroduced()) : null);
            	query.setDate(3, computer.getDiscontinued() != null ? java.sql.Date.valueOf(computer.getDiscontinued()) : null);
            	query.setLong(4, computer.getCompany().getId());
                query.executeUpdate();
                ResultSet key = query.getGeneratedKeys();
                key.next();
                newKey = key.getLong(1);
            } catch (SQLException e) {
            	throw new ErrorDAOComputer();
            }
        }
        return newKey;
    }
	
	public void updateComputer(Computer computer) throws ErrorDAOComputer {
		if(computer != null) {
			try (Connection connection = this.dbConnection.getConnection()){
				PreparedStatement query = connection.prepareStatement(UPDATE_COMPUTER);
				query.setString(1, computer.getName());
            	query.setDate(2, computer.getIntroduced() != null ? java.sql.Date.valueOf(computer.getIntroduced()) : null);
            	query.setDate(3, computer.getDiscontinued() != null ? java.sql.Date.valueOf(computer.getDiscontinued()) : null);
            	query.setLong(4, computer.getCompany().getId());
            	query.setLong(5, computer.getId());
                query.executeUpdate();
			}catch(SQLException e){
				throw new ErrorDAOComputer();
			}
		}
	}
	
	public void deleteComputer(int id) throws ErrorDAOComputer {
		try (Connection connection = this.dbConnection.getConnection()){
			PreparedStatement query = connection.prepareStatement(DELETE_COMPUTER);
           	query.setLong(1, id);
            query.executeUpdate();
		}catch(SQLException e){
			throw new ErrorDAOComputer();
		}
	}
	
	public void setMaxPrint(int number) {
		this.maxPrint = number;
	}


	public int getMaxPrint() {
		return this.maxPrint;
	}
}
