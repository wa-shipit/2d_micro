package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class MicTodoController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	//登録
	@GetMapping("/micadd")
	public String copGet1() {
		return "mictodoadd";
	}

	@PostMapping("/micadd")
	public String micaddPost(String user_id, String month, String day, String todo, Model model) {

		jdbcTemplate.update("INSERT INTO todo (`user_id`, `month`, `day`, `todo`) VALUES (?,?,?,?)", user_id, month,
				day, todo);

		return "redirect:/micadd";
	}

	//編集
	@GetMapping("/micedit")
	public String copGet2() {
		return "mictodoedit";
	}

	@PostMapping("/micedit")
	public String copPost2(String user_id, String month, String day, String todo, Model model) {

		jdbcTemplate.update("UPDATE todo SET todo = ? WHERE month = ? AND DAY = ?", todo, month, day);

		return "redirect:/micedit";
	}

	//削除
	@GetMapping("/micedel")
	public String copGet3() {
		return "mictododel";
	}

	//削除
	@PostMapping("/micdel")
	public String micdelPost(String user_id, String month, String day, String todo, Model model) {

		jdbcTemplate.update("DELETE FROM todo WHERE month = ? AND day =?", month, day);
		return "redirect:/micdel";
	}
}