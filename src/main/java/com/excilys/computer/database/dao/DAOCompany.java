package com.excilys.computer.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computer.database.data.Company;

@Component
public class DAOCompany {

	private static final int MAX_ENTRY_PRINT = 25;

	private static final String SELECT_COMPANY_NAME = "Select * FROM company WHERE name = ?";
	private static final String SELECT_COMPANY = "Select * FROM company LIMIT ? OFFSET ?";
	private static final String SELECT_COMPANY_NO_LIMIT = "Select * FROM company";

	private static final String DELETE_COMPUTER_BY_COMPANY = "DELETE FROM computer WHERE company_id = :id";
	private static final String DELETE_COMPANY_BY_ID = "DELETE FROM company WHERE id = :id";

	private DataSource dataSource;

	public DAOCompany(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Optional<Company> getCompany(String name) {
		Optional<Company> company = Optional.empty();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

		RowMapper<Company> vRowMapper = this.getRowMapper();
		List<Company> result = jdbcTemplate.query(SELECT_COMPANY_NAME, vRowMapper, name);

		if (!result.isEmpty()) {
			company = Optional.ofNullable(result.get(0));
		}
		return company;
	}

	public List<Company> getListCompany(int page) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		RowMapper<Company> vRowMapper = this.getRowMapper();
		List<Company> result = jdbcTemplate.query(SELECT_COMPANY, vRowMapper, MAX_ENTRY_PRINT, page * MAX_ENTRY_PRINT);
		return result;
	}

	public List<Company> getListCompany() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		RowMapper<Company> vRowMapper = this.getRowMapper();
		List<Company> result = jdbcTemplate.query(SELECT_COMPANY_NO_LIMIT, vRowMapper);
		return result;
	}
	
	@Transactional
	public void deleteCompanyById(int id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
		jdbcTemplate.update(DELETE_COMPUTER_BY_COMPANY, param);
		jdbcTemplate.update(DELETE_COMPANY_BY_ID, param);

	}

	private RowMapper<Company> getRowMapper() {
		return new RowMapper<Company>() {
			public Company mapRow(ResultSet pRS, int pRowNum) throws SQLException {
				Company company = new Company(pRS.getInt("id"), pRS.getString("name"));
				return company;
			}
		};
	}
}
