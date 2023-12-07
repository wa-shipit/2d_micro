package com.example.demo;

import java.util.List;
import java.util.Map;

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
	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String copGet() {
		return "mictodo_add";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/micadd", method = RequestMethod.POST)
	public String copPost(String month, String day, String todo, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
		jdbcTemplate.update("INSERT INTO todo(user_id,month,day,todo) values(?, ? , ?, ?)", 999, month, day, todo);

		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("todo", todo);

		return "mictodo_add";
	}

	//edit
	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String copGet1() {
		return "mictodo_edit";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/micedit", method = RequestMethod.POST)
	public String copPost1(String month, String day, String todo, String user_id, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
		jdbcTemplate.update("UPDATE todo SET month=?, day=?, todo=? WHERE user_id=?", month, day, todo, user_id);

		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("todo", todo);

		return "mictodo_edit";
	}

	//del
	@RequestMapping(path = "/micdel", method = RequestMethod.GET)
	public String copGet2() {
		return "mictodo_del";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/micdel", method = RequestMethod.POST)
	public String copPost2(String month, String day, String todo, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
		jdbcTemplate.update("");

		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("todo", todo);

		return "mictodo_del";
	}
}
