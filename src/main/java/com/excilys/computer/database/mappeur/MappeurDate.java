package com.excilys.computer.database.mappeur;

import java.time.LocalDate;
import java.util.Optional;

public class MappeurDate {

	private MappeurDate() {
	}

	public static LocalDate dateToLocalDate(java.sql.Date date) {
		if (date != null) {
			return date.toLocalDate();
		} else {
			return null;
		}

	}

	public static java.sql.Date optionalLocalDateToDate(Optional<LocalDate> date) {
		java.sql.Date dateSql = null;
		if (date.isPresent()) {
			dateSql = java.sql.Date.valueOf(date.get());
		}
		return dateSql;
	}
}
