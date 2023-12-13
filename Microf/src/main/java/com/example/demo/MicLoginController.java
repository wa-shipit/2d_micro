//package com.example.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@Controller
//public class MicLoginController {
//
//		@Autowired
//		JdbcTemplate jdbcTemplate;
//
//		//コピペ用サンプル(ページ表示用メソッド)
//		@RequestMapping(path = "/miclogin", method = RequestMethod.GET)
//		public String copGet() {
//			return "miclogin";
//		}
//
//		//コピペ用サンプル（画面から何か入力をした時用）
//		@RequestMapping(path = "/mcilogin", method = RequestMethod.POST)
//		public String copPost(String miclogin, String micpw, Model model) {
//
//			//DBに繋ぐならこんな感じ(JdbcTemplate)
//	List<Map<String, Object>> resultlist; resultlist = jdbcTemplate.queryForList("SELECT * FROM miclogin WHERE user_id = ?",16,"user_pass = ?",16);
//
//			model.addAttribute("miclog", miclogin);
//			model.addAttribute("micpas", micpw);
//
//			return "miclogin";
//		}
//	}
//
