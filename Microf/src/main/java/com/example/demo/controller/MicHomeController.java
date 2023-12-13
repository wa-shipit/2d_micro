package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicHomeController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/michome", method = RequestMethod.GET)
	public String copGet1(Model model) {
		List<Map<String, Object>> resultList;
		resultList = jdbcTemplate.queryForList("SELECT * FROM todo;");

		model.addAttribute("todoList", resultList);
		return "michome";

	}

	@RequestMapping(path = "/michome", method = RequestMethod.POST)
	public String copPost() {

		return "michome";
	}

	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String copGet2() {
		return "mictodo_add";

	}

	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String copGet3() {
		return "mictodo_edit";

	}

	@RequestMapping(path = "/micdel", method = RequestMethod.GET)
	public String copGet4() {
		return "mictodo_del";

	}
}
