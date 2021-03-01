package com.excilys.computerDatabase.test;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;


public class TestDB extends DataSourceBasedDBTestCase {
	
    @Override
    protected DataSource getDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/computer-database-db-test");
        dataSource.setUser("admincdb");
        dataSource.setPassword("qwerty1234");
        return dataSource;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
          .getResourceAsStream("data.xml"));
    }
    
    @Test
    public void givenDataSetEmptySchema_whenDataSetCreated_thenTablesAreEqual() throws Exception {
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("computer");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("computer");
        assertEquals(expectedTable, actualTable);
    }
}
