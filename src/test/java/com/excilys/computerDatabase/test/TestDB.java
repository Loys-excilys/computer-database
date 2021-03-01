package com.excilys.computerDatabase.test;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import org.h2.jdbcx.JdbcDataSource;


public class TestDB extends DataSourceBasedDBTestCase {
	
    @Override
    protected DataSource getDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:default;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:schema.sql'");
        dataSource.setUser("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
          .getResourceAsStream("data.xml"));
    }
}
