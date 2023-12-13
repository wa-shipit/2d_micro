package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicHomeController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/michome", method = RequestMethod.GET)
	public String copGet() {
		return "michome";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/michome", method = RequestMethod.POST)
	public String copPost(String micloginid, String micpw, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
		//		List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM honyarara WHERE honyarara");

		model.addAttribute("micloginid", micloginid);
		model.addAttribute("micpw", micpw);

		return "redirect:/michome";
	}
}
