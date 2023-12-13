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
public class MicTodoController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//(ページ表示用メソッド)
	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String micaddGet() {
		return "mictodo_add";
	}

	// 新規登録メソッド
	@RequestMapping(path = "/micadd", method = RequestMethod.POST)
	public String mictodo_add(String month, String day,String todo, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
		List<Map<String, Object>> Dup = jdbcTemplate.queryForList("SELECT count(*) FROM todo WHERE month = ? AND day = ?",month,day);
		String dup = Dup.get(0).toString();
		if(!("0".equals(Dup))) {
			return "mictodo_add";
		}else {
		jdbcTemplate.update("INSERT INTO todo VALUE(0,?,?,?)",month,day,todo);

		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("todo", todo);

		return "mictodo_add";
		}
	}
	
	//(ページ表示用メソッド)
	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String miceditGet() {
		return "mictodo_edit";
	}
	
	// 編集メソッド
	@RequestMapping(path = "/micedit", method = RequestMethod.POST)
	public String mictodo_edit(String month, String day,String todo, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
		jdbcTemplate.update("UPDATE todo SET todo = ? WHERE month = ? AND day = ?",todo,month,day);

		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("todo", todo);

		return "mictodo_edit";
	}
	
	//(ページ表示用メソッド)
	@RequestMapping(path = "/micdel", method = RequestMethod.GET)
	public String micdelGet() {
		return "mictodo_del";
	}
	
	// 削除メソッド
	@RequestMapping(path = "/micdel", method = RequestMethod.POST)
	public String mictodo_del(String month, String day,String todo, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
		jdbcTemplate.update("DELETE FROM todo WHERE month=? AND day=?",month,day);

		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("todo", todo);

		return "mictodo_del";
	}
}
