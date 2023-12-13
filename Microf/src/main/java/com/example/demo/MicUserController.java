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

	//ユーザ登録ページ(ユーザ登録メソッド)
	@RequestMapping(path = "/micuser", method = RequestMethod.GET)
	public String RegistrationGet() {
		return "micuser";
	}

	//ユーザ登録ページ（画面から何か入力をした時用）
	@RequestMapping(path = "/micuser", method = RequestMethod.POST)
	public String RegistrationPost(String loginid, String password, Model model) {

		// 文字数制限のチェック
		if (loginid.length() > 10 || password.length() > 10) {
			// エラーメッセージを設定して再度登録ページに戻る
			model.addAttribute("error", "ユーザIDとパスワードはそれぞれ10文字以内で入力してください。");
			return "micuser";
		}

		//DBに接続して登録(JdbcTemplate)
		jdbcTemplate.update("INSERT INTO miclogin (loginid,password) VALUES(?,?);", loginid, password);
		return "redirect:/miclogin";
	}
}
