package com.excilys.computer.database.mappeur;

import java.time.LocalDate;
import java.util.Optional;

public class MappeurDate {

	private MappeurDate() {
	}

	public static Optional<LocalDate> dateToOptionalLocalDate(java.sql.Date date) {
		Optional<LocalDate> optionalDate;
		if (date == null) {
			optionalDate = Optional.empty();
		} else {
			optionalDate = Optional.of(date.toLocalDate());
		}
		return optionalDate;
	}

	public static java.sql.Date optionalLocalDateToDate(Optional<LocalDate> date) {
		java.sql.Date dateSql = null;
		if (date.isPresent()) {
			dateSql = java.sql.Date.valueOf(date.get());
		}
		return dateSql;
	}
}
