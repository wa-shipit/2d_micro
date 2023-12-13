package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class MicUserController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/micuser", method = RequestMethod.GET)
	public String copGet() {
		return "micuser";
	}

	@RequestMapping(path = "/micuser", method = RequestMethod.POST)
	public String copPost(String micloginid, String micpw) {

	jdbcTemplate.queryForList("INSERT INTO miclogin VALUES (loginid=?, password=?)" , micloginid, micpw);

		
		return "redirect:/miclogin";
	}

}
