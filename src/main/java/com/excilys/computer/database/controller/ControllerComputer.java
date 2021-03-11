package com.excilys.computer.database.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerComputer {

	@GetMapping("/getComputer")
	public String redirectWithUsingRedirectView() {
		return "index";
	}
}
