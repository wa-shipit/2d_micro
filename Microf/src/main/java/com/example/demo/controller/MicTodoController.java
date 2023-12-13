package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class MicTodoController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	//登録
	@PostMapping("/micadd")
	public String micAddPost(String user_id, String month, String day, String todo, Model model) {

		jdbcTemplate.update("INSERT INTO todo (`user_id`, `month`, `day`, `todo`) VALUES (?,?,?,?)", user_id, month,
				day, todo);

		return "redirect:/michome";
	}
	//編集
	@PostMapping("/micedit")
	public String micEditPost(String user_id, String month, String day, String todo, Model model) {

		jdbcTemplate.update("UPDATE todo SET todo = ? WHERE month = ? AND DAY = ?", todo, month, day);

		return "redirect:/michome";
	}

	//削除
	@PostMapping("/micdel")
	public String micDelPost(String user_id, String month, String day, String todo, Model model) {

		jdbcTemplate.update("DELETE FROM todo WHERE month = ? AND day =?", month, day);
		return "redirect:/michome";
	}
}