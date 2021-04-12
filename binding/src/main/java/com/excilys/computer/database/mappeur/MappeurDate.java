package com.excilys.computer.database.mappeur;

import java.sql.Date;
import java.time.LocalDate;

public class MappeurDate {

	private MappeurDate() {
	}

	public static LocalDate dateToLocalDate(Date date) {
		if (date != null) {
			return date.toLocalDate();
		} else {
			return null;
		}

	}

	public static Date localDateToDate(LocalDate date) {
		Date dateSql = null;
		if (date != null) {
			dateSql = Date.valueOf(date);
		}
		return dateSql;
	}
}
