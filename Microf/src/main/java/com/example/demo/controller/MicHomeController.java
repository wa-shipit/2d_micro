package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

	@RequestMapping(path = "/michome", method = RequestMethod.GET)
	public String michome(Model model, HttpSession session) throws IOException {
		List<Map<String, Object>> resultList;

		//SELECT文の実行
		resultList = jdbcTemplate.queryForList("select * from todo");

		//実行結果をmodelにしまってHTMLで出せるようにする。
		//String loginid = (String) session.getAttribute("loginid");
		String loginid = "9999";
		model.addAttribute("loginid", loginid);
		model.addAttribute("selectResult", resultList);

		return "michome";
	}

	@RequestMapping(path = "/michome", method = RequestMethod.POST)
	public String mainmenu()
			throws IOException {

		return "michome";

	}
}
