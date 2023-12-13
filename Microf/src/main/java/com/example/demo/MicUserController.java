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
		System.out.println("get");
		return "micuser";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/micuser", method = RequestMethod.POST)
	public String copPost(String id, String ps, Model model) {
		System.out.println("ふるべゆらゆら");
		//DBに繋ぐならこんな感じ(jdbcTemplate)
				List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM miclogin WHERE loginid= ? AND password=?",id ,ps);

				if(id.length()>11 || ps.length()>11) {
					model.addAttribute("ooki", "文字数が多すぎます");
					System.out.println("もうあるんだよ禿");
					return "micuser";
				}else {
					jdbcTemplate.update("insert into miclogin(loginid , password) values (?,?)",id,ps);
					return "micuser";
				}
	}
}
