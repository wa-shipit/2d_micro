package com.example.demo;

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

	// 新規登録画面表示

	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String Insert() {
		return "micadd";
	}

	// 新規登録処理
	@RequestMapping(path = "/micadd", method = RequestMethod.POST)
	public String Insert1( String month, String day, String todo, Model model) {
		jdbcTemplate.update("INSERT INTO todo (user_id,month, day, todo) VALUES (?, ?, ?,?)","999999", month, day,
				todo);
		return "redirect:/micadd";
	}

	// 編集画面表示
	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String Update() {
		return "micedit";
	}

	// 編集処理
	@RequestMapping(path = "/micedit", method = RequestMethod.POST)
	public String Update1( String month, String day, String todo, Model model) throws IOException {



		 jdbcTemplate.update("UPDATE todo SET todo=? WHERE day=?AND month=?", todo,day,month);

		return "redirect:/micedit";
	}

	// 削除画面表示

	@RequestMapping(path = "/micdel", method = RequestMethod.GET)
	public String Delete() {
		return "micdel";
	}

	// 削除処理

	@RequestMapping(path = "/micdel", method = RequestMethod.POST)
	public String Delete1(String day,String month) throws IOException {

		//データ削除SQL実行
		jdbcTemplate.update("DELETE FROM todo WHERE day=?AND month=?", day,month);

		return "redirect:/micdel";
	}
}
