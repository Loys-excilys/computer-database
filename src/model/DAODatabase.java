package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class DAODatabase extends DAOEntity{
	
	public DAODatabase(final Connection connection) throws SQLException {
		super(connection);
	}
	
	public List<DataComputer> getComputer() {
		List<DataComputer> resultList = new ArrayList<>();
		ResultSet result = null;
		String requete = "SELECT * FROM computer;";
		
		try {
		   Statement stmt = getConnection().createStatement();
		   result = stmt.executeQuery(requete);
		   while(result.next()) {
			   resultList.add(new DataComputer(result.getInt("id"), result.getString("name"), result.getDate("introduced"), result.getDate("discontinued"), result.getInt("company_id")));
		   }
		} catch (SQLException e) {
		   //traitement de l'exception
		}
		
		return resultList;
	}
}
