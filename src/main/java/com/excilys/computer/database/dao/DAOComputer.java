package com.excilys.computer.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.excilys.computer.database.builder.ComputerBuilder;
import com.excilys.computer.database.data.Company;
import com.excilys.computer.database.data.Computer;
import com.excilys.computer.database.data.Page;
import com.excilys.computer.database.error.ErrorSaisieUser;
import com.excilys.computer.database.mappeur.MappeurDate;

@Component
public class DAOComputer {

	private static final String SELECT_COMPUTER = "SELECT computer.id as id," + " computer.name as name,"
			+ " computer.introduced as introduced," + " computer.discontinued as discontinued,"
			+ " company.id as companyId," + " company.name as companyName" + " FROM computer "
			+ " LEFT JOIN company ON computer.company_id = company.id";

	private static final String SELECT_COMPUTER_ID = "SELECT computer.id as id," + " computer.name as name,"
			+ " computer.introduced as introduced," + " computer.discontinued as discontinued,"
			+ " company.id as companyId," + " company.name as companyName" + " FROM computer "
			+ " LEFT JOIN company ON computer.company_id = company.id" + " WHERE computer.id = ?";

	private static final String INSERT_COMPUTER = "INSERT INTO computer(name, introduced, discontinued, company_id)"
			+ " values(:name, :introduced, :discontinued, :company_id) ";
	private static final String UPDATE_COMPUTER = "UPDATE computer" + " SET name = :name,"
			+ " introduced = :introduced," + " discontinued = :discontinued," + " company_id = :company_id"
			+ " WHERE id = :id";
	private static final String SEARCH_COMPUTER_JOIN = " WHERE computer.name LIKE ?";

	private static final String SEARCH_COMPUTER = " WHERE name LIKE ?";

	private static final String ORDER_BY = " ORDER BY ";

	private static final String LIMIT_OFFSET = " LIMIT ? OFFSET ?";

	private static final String DELETE_COMPUTER_BY_ID = "DELETE FROM computer WHERE id = :id";

	private static final String COUNT_COMPUTER = "SELECT COUNT(*) FROM computer";

	private DataSource dataSource;

	public DAOComputer(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int getNumberComputer() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		int numberComputer = jdbcTemplate.queryForObject(COUNT_COMPUTER, Integer.class);
		return numberComputer;
	}

	public int getSearchNumberComputer(String search) {
		search = "%" + search + "%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		int numberComputer = jdbcTemplate.queryForObject(COUNT_COMPUTER + SEARCH_COMPUTER, Integer.class, search);
		return numberComputer;
	}

	public Optional<Computer> getComputer(int id) throws ErrorSaisieUser {
		Optional<Computer> computer = Optional.empty();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

		RowMapper<Computer> vRowMapper = this.getRowMapper();
		List<Computer> result = jdbcTemplate.query(SELECT_COMPUTER_ID, vRowMapper, id);

		if (!result.isEmpty()) {
			computer = Optional.ofNullable(result.get(0));
		}
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
//		computer = Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_COMPUTER_ID, Computer.class));

		return computer;
	}

	public List<Computer> getListComputer(Page page) throws ErrorSaisieUser {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		RowMapper<Computer> vRowMapper = this.getRowMapper();
		List<Computer> result = jdbcTemplate.query(SELECT_COMPUTER + LIMIT_OFFSET, vRowMapper, page.getMaxPrint(),
				page.getPage() * page.getMaxPrint());
		return result;
	}

	public List<Computer> getSearchComputer(String search, Page page) throws ErrorSaisieUser {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		search = "%" + search + "%";
		RowMapper<Computer> vRowMapper = this.getRowMapper();
		List<Computer> result = jdbcTemplate.query(SELECT_COMPUTER + SEARCH_COMPUTER_JOIN + LIMIT_OFFSET, vRowMapper,
				search, page.getMaxPrint(), page.getPage() * page.getMaxPrint());
		return result;
	}

	public List<Computer> getListComputerOrder(String orderField, String sort, Page page) throws ErrorSaisieUser {
		orderField = "computer." + orderField;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		RowMapper<Computer> vRowMapper = this.getRowMapper();
		List<Computer> result = jdbcTemplate.query(SELECT_COMPUTER + ORDER_BY + orderField + " " + sort + LIMIT_OFFSET,
				vRowMapper, page.getMaxPrint(), page.getPage() * page.getMaxPrint());
		return result;
	}

	public List<Computer> getSearchComputerOrder(String search, String orderField, String sort, Page page)
			throws ErrorSaisieUser {
		orderField = "computer." + orderField;
		search = "%" + search + "%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		RowMapper<Computer> vRowMapper = this.getRowMapper();
		List<Computer> result = jdbcTemplate.query(
				SELECT_COMPUTER + SEARCH_COMPUTER_JOIN + ORDER_BY + orderField + " " + sort + LIMIT_OFFSET, vRowMapper,
				search, page.getMaxPrint(), page.getPage() * page.getMaxPrint());
		return result;
	}

	public long insertComputer(Computer computer) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", computer.getName());
		param.addValue("introduced", MappeurDate.optionalLocalDateToDate(computer.getIntroduced()));
		param.addValue("discontinued", MappeurDate.optionalLocalDateToDate(computer.getDiscontinued()));
		param.addValue("company_id", computer.getCompany().isPresent() ? computer.getCompany().get().getId() : null);
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
		long newKey = jdbcTemplate.update(INSERT_COMPUTER, param);

		return newKey;
	}

	public void updateComputer(Computer computer) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", computer.getName());
		param.addValue("introduced", MappeurDate.optionalLocalDateToDate(computer.getIntroduced()));
		param.addValue("discontinued", MappeurDate.optionalLocalDateToDate(computer.getDiscontinued()));
		param.addValue("company_id", computer.getCompany().isPresent() ? computer.getCompany().get().getId() : null);
		param.addValue("id", computer.getId());
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
		jdbcTemplate.update(UPDATE_COMPUTER, param);
	}

	public void deleteComputerById(int id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
		jdbcTemplate.update(DELETE_COMPUTER_BY_ID, param);
	}

	private RowMapper<Computer> getRowMapper() {
		return new RowMapper<Computer>() {
			public Computer mapRow(ResultSet pRS, int pRowNum) throws SQLException {
				Computer computer = new ComputerBuilder().addId(pRS.getInt("id")).addName(pRS.getString("name"))
						.addIntroduced((MappeurDate.dateToOptionalLocalDate(pRS.getDate("introduced"))))
						.addDiscontinued((MappeurDate.dateToOptionalLocalDate(pRS.getDate("discontinued"))))
						.addCompany(Optional
								.ofNullable(new Company(pRS.getLong("companyID"), pRS.getString("companyName"))))
						.getComputer();
				return computer;
			}
		};
	}
}
