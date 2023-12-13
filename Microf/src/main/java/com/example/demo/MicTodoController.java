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

	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/mictodo_add", method = RequestMethod.GET)
	public String addGet() {
		return "micadd";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/mictodo_add", method = RequestMethod.POST)
	public String addPost(	int add1, int add2,String add3, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
			List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM todo WHERE  ");
            
			jdbcTemplate.update("insert into todo(month,day,todo) VALUES (?,?,?)",1,1,"しょうがつ");
		model.addAttribute("add1", add1);
		model.addAttribute("add2",add2);
		model.addAttribute("add3",add3);

		return "micadd";
	}
	
	@RequestMapping(path = "/mictodo_dit", method = RequestMethod.GET)
	public String ditGet() {
		return "micedit";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/mictodo_dit", method = RequestMethod.POST)
	public String ditPost(String dit1, String dit2,String dit3, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
				List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM honyarara WHERE honyarara");

		model.addAttribute("dit1", dit1);
		model.addAttribute("dit2", dit2);
		model.addAttribute("dit3", dit3);

		return "micedit";
	}
	@RequestMapping(path = "/mictodo_del", method = RequestMethod.GET)
	public String delGet() {
		return "mictodo_del";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/mictodo_del", method = RequestMethod.POST)
	public String delPost(String del1, String del2, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
				List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM honyarara WHERE honyarara");

		model.addAttribute("del1", del1);
		model.addAttribute("del2", del2);

		return "mictodo_del";
	}
	
}
