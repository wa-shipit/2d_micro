package com.example.demo;

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

	@RequestMapping(path = "/micuser", method = RequestMethod.GET)
	public String copGet() {
		return "micuser";
	}

	@RequestMapping(path = "/micuser", method = RequestMethod.POST)
	public String copPost(String loginid, String password, Model model) {

		 if (loginid.length() > 10 || password.length() > 10) {
		        model.addAttribute("message", "IDおよびパスワードは10文字以内で入力してください。");
		    } else {
		        jdbcTemplate.update("INSERT INTO miclogin (loginid, password) VALUES (?, ?)", loginid, password);
		        model.addAttribute("message", "登録が完了しました。");
		    }

		    return "micuser";
	}
}
