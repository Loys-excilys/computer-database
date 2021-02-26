package com.excilys.computerDatabase.data;

import java.time.LocalDate;
import java.util.Optional;

public class MappeurDate {
	
	public static Optional<LocalDate> dateToOptionalLocalDate(java.sql.Date date){
		Optional<LocalDate> optionalDate;
		if(date == null) {
			optionalDate = Optional.empty();
		}else {
			optionalDate = Optional.of(date.toLocalDate());
		}
		return optionalDate;
	}
	
	
	public static java.sql.Date OptionalLocalDateToDate(Optional<LocalDate> date){
		java.sql.Date Date = null;
		if(date.isPresent()){
			Date = java.sql.Date.valueOf(date.get());
		}
		return Date;
	}
}
