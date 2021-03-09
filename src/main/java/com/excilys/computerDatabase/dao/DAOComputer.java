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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.builder.ComputerBuilder;
import com.excilys.computerDatabase.data.Company;
import com.excilys.computerDatabase.data.Computer;
import com.excilys.computerDatabase.data.Page;
import com.excilys.computerDatabase.error.ErrorDAOComputer;
import com.excilys.computerDatabase.error.ErrorSaisieUser;
import com.excilys.computerDatabase.mappeur.MappeurDate;


@Component
@Scope("singleton")
public class DAOComputer{
	
	private static final String SELECT_COMPUTER = "SELECT computer.id as id,"
			+ " computer.name as name,"
			+ " computer.introduced as introduced,"
			+ " computer.discontinued as discontinued,"
			+ " company.id as companyId,"
			+ " company.name as companyName"
			+ " FROM computer "
			+ " LEFT JOIN company ON computer.company_id = company.id";
	
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
	private static final String SEARCH_COMPUTER_JOIN = " WHERE computer.name LIKE ?";
	
	private static final String SEARCH_COMPUTER = " WHERE name LIKE ?";
	
	private static final String ORDER_BY = " ORDER BY ";
	
	private static final String LIMIT_OFFSET = " LIMIT ? OFFSET ?";
	
	private static final String DELETE_COMPUTER_BY_ID = "DELETE FROM computer WHERE id = ?";
	
	private static final String COUNT_COMPUTER = "SELECT COUNT(*) FROM computer";
	
	@Autowired
	private DBConnection dbConnection;

	
	public int getNumberComputer() {
		int numberComputer = 0;
		try(Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(COUNT_COMPUTER);){
        	
            ResultSet result = query.executeQuery();
            result.next();
            numberComputer = result.getInt(1);
            
        } catch (SQLException errorSQL) {
        	new ErrorDAOComputer().connectionLost(errorSQL);
        }
		return numberComputer;
	}
	
	public int getSearchNumberComputer(String search) {
		int numberComputer = 0;
		search = "%" + search + "%";
		try(Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(COUNT_COMPUTER + SEARCH_COMPUTER);){
        	query.setString(1, search);
            ResultSet result = query.executeQuery();
            result.next();
            numberComputer = result.getInt(1);
            
        } catch (SQLException errorSQL) {
        	new ErrorDAOComputer().connectionLost(errorSQL);
        }
		return numberComputer;
	}
	
	public int getNumberComputerOrder(String orderField, String sort) {
		int numberComputer = 0;
		try(Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(COUNT_COMPUTER + ORDER_BY + orderField + " " + sort);){
        	
            ResultSet result = query.executeQuery();
            result.next();
            numberComputer = result.getInt(1);
            
        } catch (SQLException errorSQL) {
        	new ErrorDAOComputer().connectionLost(errorSQL);
        }
		return numberComputer;
	}
	
	public int getSearchNumberComputerOrder(String search, String orderField, String sort) {
		int numberComputer = 0;
		search = "%" + search + "%";
		try(Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(COUNT_COMPUTER + SEARCH_COMPUTER + ORDER_BY + orderField + " " + sort);){
			query.setString(1, search);
            ResultSet result = query.executeQuery();
            result.next();
            numberComputer = result.getInt(1);
            
        } catch (SQLException errorSQL) {
        	new ErrorDAOComputer().connectionLost(errorSQL);
        }
		return numberComputer;
	}
	
	public Optional<Computer> getComputer(int id) throws ErrorSaisieUser {
		Optional<Computer> computer = Optional.empty();
		try(Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(SELECT_COMPUTER_ID);){
        	
        	query.setLong(1, id);
            ResultSet result = query.executeQuery();
            result.next();
            computer = this.toComputer(result);
        } catch (SQLException errorSQL) {
        	new ErrorDAOComputer().connectionLost(errorSQL);
        	throw new ErrorSaisieUser(this.getClass());
        }
		return computer;
	}
	
	public List<Computer> getListComputer(Page page) throws ErrorSaisieUser {
		List<Computer> resultList = new ArrayList<>();
		
		try (Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(SELECT_COMPUTER + LIMIT_OFFSET);){
			query.setInt(1, page.getMaxPrint());
			query.setInt(2, page.getPage()*page.getMaxPrint());
	        ResultSet result = query.executeQuery();
	        while(result.next()) {
	        	resultList.add(this.toComputer(result).get());
		   }
		} catch (SQLException errorSQL) {
			new ErrorDAOComputer().connectionLost(errorSQL);
		}
		
		return resultList;
	}
	
	public List<Computer> getSearchComputer(String search, Page page) throws ErrorSaisieUser {
		List<Computer> resultList = new ArrayList<>();
		search = "%" + search + "%";
		try (Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(SELECT_COMPUTER + SEARCH_COMPUTER_JOIN + LIMIT_OFFSET);){
			query.setString(1,search);
			query.setInt(2, page.getMaxPrint());
			query.setInt(3, page.getPage()*page.getMaxPrint());
	        ResultSet result = query.executeQuery();
	        while(result.next()) {
	        	resultList.add(this.toComputer(result).get());
		   }
		} catch (SQLException errorSQL) {
			new ErrorDAOComputer().connectionLost(errorSQL);
			throw new ErrorSaisieUser(this.getClass());
		}
		
		return resultList;
	}
	
	public List<Computer> getListComputerOrder(String orderField, String sort, Page page) throws ErrorSaisieUser {
		List<Computer> resultList = new ArrayList<>();
		orderField = "computer." + orderField;
		try (Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(SELECT_COMPUTER + ORDER_BY + orderField + " " + sort + LIMIT_OFFSET);){
			query.setInt(1, page.getMaxPrint());
			query.setInt(2, page.getPage()*page.getMaxPrint());
	        ResultSet result = query.executeQuery();
	        while(result.next()) {
	        	resultList.add(this.toComputer(result).get());
		   }
		} catch (SQLException errorSQL) {
			new ErrorDAOComputer().connectionLost(errorSQL);
			throw new ErrorSaisieUser(this.getClass());
		}
		return resultList;
	}
	
	public List<Computer> getSearchComputerOrder(String search, String orderField, String sort, Page page) throws ErrorSaisieUser {
		List<Computer> resultList = new ArrayList<>();
		orderField = "computer." + orderField;
		search = "%" + search + "%";
		try (Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(SELECT_COMPUTER + SEARCH_COMPUTER_JOIN + ORDER_BY + orderField + " " + sort + LIMIT_OFFSET);){
			query.setString(1, search);
			query.setInt(2, page.getMaxPrint());
			query.setInt(3, page.getPage()*page.getMaxPrint());
	        ResultSet result = query.executeQuery();
	        while(result.next()) {
	        	resultList.add(this.toComputer(result).get());
		   }
		} catch (SQLException errorSQL) {
			new ErrorDAOComputer().connectionLost(errorSQL);
			throw new ErrorSaisieUser(this.getClass());
		}
		return resultList;
	}
	
	
	public long insertComputer(Computer computer) {
		long newKey = 0;
        if(computer != null) {
            try (Connection connection = this.dbConnection.getConnection();
                	PreparedStatement query = connection.prepareStatement(INSERT_COMPUTER, Statement.RETURN_GENERATED_KEYS);){
            	query.setString(1, computer.getName());
            	query.setDate(2, MappeurDate.optionalLocalDateToDate(computer.getIntroduced()));
            	query.setDate(3, MappeurDate.optionalLocalDateToDate(computer.getDiscontinued()));

    			if(computer.getCompany().isPresent()) {
    				query.setLong(4, computer.getCompany().get().getId());
    			}else {
    				query.setString(4, null);
    			}
            	
                query.executeUpdate();
                ResultSet key = query.getGeneratedKeys();
                key.next();
                newKey = key.getLong(1);
            } catch (SQLException errorSQL) {
            	new ErrorDAOComputer().insertError(errorSQL);
            }
        }
        return newKey;
    }
	
	public void updateComputer(Computer computer){
		if(computer != null) {
			try (Connection connection = this.dbConnection.getConnection();
					PreparedStatement query = connection.prepareStatement(UPDATE_COMPUTER);){
				query.setString(1, computer.getName());
            	query.setDate(2, MappeurDate.optionalLocalDateToDate(computer.getIntroduced()));
            	query.setDate(3, MappeurDate.optionalLocalDateToDate(computer.getDiscontinued()));    			
            	if(computer.getCompany().isPresent()) {
    				query.setLong(4, computer.getCompany().get().getId());
    			}else {
    				query.setString(4, null);
    			}
            	query.setLong(5, computer.getId());
                query.executeUpdate();
			}catch(SQLException errorSQL){
				new ErrorDAOComputer().updateError(errorSQL);
			}
		}
	}
	
	public void deleteComputerById(int id) {
		try (Connection connection = this.dbConnection.getConnection();
				PreparedStatement query = connection.prepareStatement(DELETE_COMPUTER_BY_ID);){
			
           	query.setLong(1, id);
            query.executeUpdate();
		}catch(SQLException errorSQL){
			new ErrorDAOComputer().deleteError(errorSQL);
		}
	}
	
	private Optional<Computer> toComputer(ResultSet result) throws ErrorDAOComputer {
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
		} catch (SQLException errorSQL) {
			throw new ErrorDAOComputer();
		}
		return optionalComputer;
	}
}
