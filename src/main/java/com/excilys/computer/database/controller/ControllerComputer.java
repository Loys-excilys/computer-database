package com.excilys.computer.database.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerComputer {

	private static final String MAX_NUMBER_ENTRY = "maxNumberPrint";
	private static final String SEARCH = "search";
	private static final String ORDER_FIELD = "orderField";
	private static final String SORT = "sort";

	@GetMapping("/getComputer")
	public String redirectWithUsingRedirectView(@RequestParam(required = false) String id,
			@RequestParam(required = false) String numberEntry,
			@RequestParam(required = false) String search,
			@RequestParam(required = false) String orderField,
			@RequestParam(required = false) String sort) {
		
		
		return "Computer";
	}
}
