package com.example.demo;


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

	//login
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String copGet() {
		return "miclogin";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String copPost(String micloginid, String micpw, Model model) {

//		DBに繋ぐならこんな感じ(JdbcTemplate)
		jdbcTemplate.queryForList("SELECT * FROM miclogin WHERE loginid = ? AND password  = ?;",micloginid,  micpw);

		model.addAttribute("id", micloginid);
		model.addAttribute("pass", micpw);
		
	return "redirect:/michome";
		
		//ログイン成功時と失敗時のやり方わからん
		
//		if () {
//            // ログイン成功時の処理
//            return "redirect:/michome"; // michomeへのリダイレクト
//        } else {
//            // ログイン失敗時の処理
//            model.addAttribute("errorMessage", "ログインに失敗しました");
//            return "miclogin"; // ログインページを再表示
//        }
	}
}
