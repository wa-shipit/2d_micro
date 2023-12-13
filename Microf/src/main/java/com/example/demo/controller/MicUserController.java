package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicUserController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/micuser", method = RequestMethod.GET)
	public String copGet2() {
		return "micuser";

	}

	@RequestMapping(path = "/micuser", method = RequestMethod.POST)
	public String copPost(String micloginid, String micpw) {
		int resultList;
		resultList = jdbcTemplate.update("INSERT INTO `miclogin` (`loginid`, `password`) VALUES (?, ?);", micloginid, micpw);

		return "redirect:/micuser";
	}
}
