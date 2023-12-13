package com.example.demo.controller;

import java.io.IOException;

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

	//予定
	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String micaddPost() {

		return "Mictodo_add";
	}

	@RequestMapping(path = "/micadd", method = RequestMethod.POST)
	public String micadd(String user_id, String month, String day, String todo,
			Model model) throws IOException {

		//データ登録SQL実行
		jdbcTemplate.update(
				"INSERT INTO MicTodo (user_id,month,day,todo) VALUES(?,?,?,?) WHERE user_id = ;",
				user_id, month, day, todo);

		return "redirect:/Mictodo_add";
	}

	//編集
	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String miceditPost() {

		return "Mictodo_edit";
	}

	@RequestMapping(path = "/micedit", method = RequestMethod.POST)
	public String micedit(String month, String day,
			Model model)
			throws IOException {

		//データ更新SQL実行
		jdbcTemplate.update(
				"UPDATE MicTodo SET user_id=?,month=?,day=? ,todo=? WHERE month = ? && day = ?",
				month, day);

		return "redirect:/Mictodo_edit";
	}

	//削除
	@RequestMapping(path = "/micdel", method = RequestMethod.GET)
	public String micdelPost() {

		return "Mictodo_del";
	}

	@RequestMapping(path = "/micdel", method = RequestMethod.POST)
	public String micdel(String month, String day, Model model) throws IOException {

		//データ削除SQL実行
		jdbcTemplate.update("DELETE FROM MicTodo WHERE month = ? && day = ?", month, day);

		return "redirect:/Mictodo_del";
	}
}