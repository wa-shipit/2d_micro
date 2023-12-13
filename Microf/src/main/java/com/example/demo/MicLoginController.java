package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;



@Controller
public class MicLoginController {
	@Autowired
	JdbcTemplate jdbcTemplate;


	@GetMapping("/miclogin")
	public String micLoginGet() {

		return "miclogin";
	}


	@PostMapping("/miclogin")
	public String micLoginPost(String micloginid, String micpw, HttpSession session, Model model) {

		if (micloginid.length() > 16 || micpw.length() > 16) {
			model.addAttribute("errormessageLF", "文字数多");
			return "miclogin";
		}


		session.setAttribute("loginparam1", micloginid);
		session.setAttribute("loginparam2", micpw);


		List<Map<String, Object>> resultList;

		resultList = jdbcTemplate.queryForList("SELECT * FROM miclogin WHERE loginid = ? AND password = ? ",
				micloginid,
				micpw);


		if (resultList.size() != 0) {

			return "redirect:/michome";

		} else {
         model.addAttribute("errormessageLF", "失敗");
			return "miclogin";
		}

	}
}