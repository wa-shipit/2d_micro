package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MicTodoController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String addGet() {



		return "mictodo_add";
	}

	@RequestMapping(path = "/micadd", method = RequestMethod.POST)
	public String addPost(String month, String day, String todo, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)

		jdbcTemplate.update("INSERT INTO todo  VALUES ('1',?,?,?);",month,day,todo);

		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("todo", todo);


		return "mictodo_add";
	}



	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String editGet() {



		return "mictodo_edit";
	}

	@RequestMapping(path = "/micedit", method = RequestMethod.POST)
	public String editPost(String month, String day, String todo, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)

		jdbcTemplate.update("UPDATE todo SET todo=? WHERE month=? and day=?",todo,month,day);


		return "mictodo_edit";
	}
	
	@RequestMapping(path = "/micdel", method = RequestMethod.GET)
	public String delGet() {



		return "mictodo_del";
	}

	@RequestMapping(path = "/micdel", method = RequestMethod.POST)
	public String delPost(String month, String day,  Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)

		jdbcTemplate.update("DELETE from todo  WHERE  month=? and day=?",month,day);


		return "mictodo_del";
	}


}
