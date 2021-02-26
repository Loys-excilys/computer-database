package com.excilys.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.builder.ComputerBuilder;
import com.excilys.computerDatabase.data.Company;
import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.data.Page;
import com.excilys.computerDatabase.error.ErrorDAOComputer;
import com.excilys.computerDatabase.error.ErrorSaisieUser;
import com.excilys.computerDatabase.mappeur.MappeurDate;

public class DAOComputer{
	
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
		try(Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(COUNT_COMPUTER);){
        	
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
		Optional<Computer> computer = Optional.empty();
		try(Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(SELECT_COMPUTER_ID);){
        	
        	query.setLong(1, id);
            ResultSet result = query.executeQuery();
            result.next();
            computer = this.toComputer(result);
        } catch (SQLException errorSQL) {
        	throw new ErrorDAOComputer();
        }
		return computer;
	}
	
	public List<Computer> getListComputer(Page page) throws ErrorDAOComputer, ErrorSaisieUser {
		List<Computer> resultList = new ArrayList<>();
		
		try (Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(SELECT_COMPUTER);){
			query.setInt(1, page.getMaxPrint());
			query.setInt(2, page.getPage()*page.getMaxPrint());
	        ResultSet result = query.executeQuery();
	        Optional<Computer> optionalComputer;
	        while(result.next()) {
	        	if((optionalComputer = this.toComputer(result)).isPresent()) {
	        		resultList.add(optionalComputer.get());
	        	}
		   }
		} catch (SQLException e) {
			throw new ErrorDAOComputer();
		}
		
		return resultList;
	}
	
	public long insertComputer(Computer computer) throws ErrorDAOComputer {
		long newKey = 0;
        if(computer != null) {
            try (Connection connection = this.dbConnection.getConnection();
                	PreparedStatement query = connection.prepareStatement(INSERT_COMPUTER, Statement.RETURN_GENERATED_KEYS);){
            	query.setString(1, computer.getName());
            	query.setDate(2, MappeurDate.OptionalLocalDateToDate(computer.getIntroduced()));
            	query.setDate(3, MappeurDate.OptionalLocalDateToDate(computer.getDiscontinued()));
            	query.setLong(4, computer.getCompany().isPresent() ? computer.getCompany().get().getId() : null);
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
			try (Connection connection = this.dbConnection.getConnection();
					PreparedStatement query = connection.prepareStatement(UPDATE_COMPUTER);){
				query.setString(1, computer.getName());
            	query.setDate(2, MappeurDate.OptionalLocalDateToDate(computer.getIntroduced()));
            	query.setDate(3, MappeurDate.OptionalLocalDateToDate(computer.getDiscontinued()));
            	query.setLong(4, computer.getCompany().isPresent() ? computer.getCompany().get().getId() : null);
            	query.setLong(5, computer.getId());
                query.executeUpdate();
			}catch(SQLException e){
				throw new ErrorDAOComputer();
			}
		}
	}
	
	public void deleteComputer(int id) throws ErrorDAOComputer {
		try (Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(DELETE_COMPUTER);){
			
           	query.setLong(1, id);
            query.executeUpdate();
		}catch(SQLException e){
			throw new ErrorDAOComputer();
		}
	}
	
	private Optional<Computer> toComputer(ResultSet result) {
		Optional<Computer> optionalComputer = Optional.empty();
		try {
			Optional<LocalDate> introduced = MappeurDate.dateToOptionalLocalDate(result.getDate("introduced"));
			Optional<LocalDate> discontinued = MappeurDate.dateToOptionalLocalDate(result.getDate("discontinued"));
			Computer computer = new ComputerBuilder()
					.addId(result.getInt("id"))
					.addName(result.getString("name"))
					.addIntroduced(introduced)
					.addDiscontinued(discontinued)
					.addCompany(Optional.of(new Company(result.getLong("companyID"), result.getString("companyName"))))
					.getComputer();
			optionalComputer =  Optional.of(computer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return optionalComputer;
	}
}
