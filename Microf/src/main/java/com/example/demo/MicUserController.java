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
public class MicUserController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/micuser", method = RequestMethod.GET)
	public String copGet() {
		return "micuser";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/micuser", method = RequestMethod.POST)
	public String copPost(int micuser1, int micuser2, Model model) {
		boolean validated = false;
		while (!validated) {
		if (micuser1 > 11) {
		validated = true;
		}
		if (micuser2 > 11) {
			validated = true;
			}
		}


		//DBに繋ぐならこんな感じ(JdbcTemplate)
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM miclogin WHERE loginid = ?. and password = ?");

		model.addAttribute("micuser1", micuser1);
		model.addAttribute("micuser2", micuser2);

		return "micuser";
	}
}
