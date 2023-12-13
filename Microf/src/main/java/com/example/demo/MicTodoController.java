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

	//登録
	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String copGet1() {
		return "mictodotouroku";
	}

	@RequestMapping(path = "/micadd", method = RequestMethod.POST)
	public String micaddPost(String user_id, String month, String day, String todo, Model model) {

		jdbcTemplate.update("INSERT INTO todo (`user_id`, `month`, `day`, `todo`) VALUES (?,?,?,?)", user_id, month,
				day, todo);

		return "redirect:/micadd";
	}

	//編集
	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String copGet2() {
		return "mictodohennsyu";
	}

	@RequestMapping(path = "/micedit", method = RequestMethod.POST)
	public String copPost2(String user_id, String month, String day, String todo, Model model) {

		jdbcTemplate.update("UPDATE todo SET todo = ? WHERE month = ? AND DAY = ?", todo, month, day);

		return "redirect:/micedit";
	}

	//削除
	@RequestMapping(path = "/micedel", method = RequestMethod.GET)
	public String copGet3() {
		return "mictodosakujyo";
	}

	//削除
	@RequestMapping(path = "/micdel", method = RequestMethod.POST)
	public String micdelPost(String user_id, String month, String day, String todo, Model model) {

		jdbcTemplate.update("DELETE FROM todo WHERE month = ? AND day =?", month, day);
		return "mictodosakujyo";
	}
}