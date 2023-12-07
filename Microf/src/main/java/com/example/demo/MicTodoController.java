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

	//add
	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String micaddGet() {
		return "mictodo_add";
	}

	@RequestMapping(path = "/micadd", method = RequestMethod.POST)
	public String micaddPost(String user_id, String month, String day, String todo, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
		jdbcTemplate.update("INSERT INTO todo (`user_id`, `month`, `day`, `todo`) VALUES (?,?,?,?)", user_id, month,
				day, todo);

		return "redirect:/micadd";
	}

	//edit
	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String miceditGet() {
		return "mictodo_edit";
	}

	@RequestMapping(path = "/micedit", method = RequestMethod.POST)
	public String miceditPost(String user_id, String month, String day, String todo, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
		jdbcTemplate.update("UPDATE todo SET todo = ? WHERE month = ? AND DAY = ?", todo, month, day);

		return "redirect:/micedit";
	}

	//del
	@RequestMapping(path = "/micdel", method = RequestMethod.GET)
	public String micdelGet() {
		return "mictodo_del";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/micdel", method = RequestMethod.POST)
	public String micdelPost(String user_id, String month, String day, String todo, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
		jdbcTemplate.update("DELETE FROM todo WHERE month = ? AND day =?", month, day);

		return "redirect:/micdel";
	}
}