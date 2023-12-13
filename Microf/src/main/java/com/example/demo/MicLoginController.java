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
	public String copPost(String micloginid,String micpw ,Model model) {


				List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM miclogin WHERE micloginid = ? AND micpw = ?",micloginid,micpw);

				if(resultList.size() == 0) {
					model.addAttribute("fail","ログインに失敗しました");
				}

		model.addAttribute("micloginid", resultList);
		model.addAttribute("micpw", resultList);


		return "miclogin";




	}
}
