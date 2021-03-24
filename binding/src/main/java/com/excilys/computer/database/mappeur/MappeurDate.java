package com.excilys.computer.database.mappeur;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

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

	public static Date optionalLocalDateToDate(Optional<LocalDate> date) {
		Date dateSql = null;
		if (date.isPresent()) {
			dateSql = Date.valueOf(date.get());
		}
		return dateSql;
	}
}
