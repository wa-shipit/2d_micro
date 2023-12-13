package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicUserController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/micuser", method = RequestMethod.GET)
	public String copGet() {
		return "/micuser";
	}

	@RequestMapping(path = "/micuser", method = RequestMethod.POST)
	public String copPost(String example1, String example2, Model model) {

		java.util.List<Map<String, Object>> resultList = jdbcTemplate
				.queryForList("SELECT * FROM honyarara WHERE honyarara");

		model.addAttribute("example1", example1);
		model.addAttribute("example2", example2);

		return "example";
	}
}
