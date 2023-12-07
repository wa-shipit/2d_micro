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
public class MicLoginController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/miclogin", method = RequestMethod.GET)
	public String copGet() {
		return "miclogin";
	}

	@RequestMapping(path = "/miclogin", method = RequestMethod.POST)
	public String copPost(String miclogin, String micpw, Model model) {
		int length = miclogin.length();
		int length2 = micpw.length();
		if (length < 17 && length2 < 17) {

			List<Map<String, Object>> resultList = jdbcTemplate
					.queryForList("SELECT * FROM miclogin WHERE loginid=? passward=?");

			model.addAttribute("miclogin", miclogin);
			model.addAttribute("micpw", micpw);

			int count = resultList.size();

			if (count > 0) {
				return "redirect:/michome";
			} else {
				return "miclogin_ng";
			}

		} else {
			return "miclogin_ng2";
		}

	}
}
