package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicUserController {

	@RequestMapping(path = "/micuser", method = RequestMethod.GET)
	public String copGet() {
		return "micuser";
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/micuser", method = RequestMethod.POST)
	public String copGet( String loginid, String password) {


		jdbcTemplate.update("insert into miclogin(loginid,password)");

		return "micuser";
	}
}
