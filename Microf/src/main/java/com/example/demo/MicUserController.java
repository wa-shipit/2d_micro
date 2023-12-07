package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicUserController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/micuser", method = RequestMethod.GET)
	public String copGet() {
		return "micuser";
	}

	//サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/micuser", method = RequestMethod.POST)
	public String copPost(String loginid, String password, Model model) {

		String result = "登録完了";
		try {
			//DBに繋ぐならこんな感じ(JdbcTemplate)
			jdbcTemplate.update("INSERT INTO miclogin VALUES(?,?);",loginid,password);
		}catch(DuplicateKeyException e) {
			System.out.println("id重複エラーです。");
			result = "idが重複しています";
		}


		model.addAttribute("result", result);
		//入力値確認
//		model.addAttribute("loginid", loginid);
//		model.addAttribute("password", password);

		return "micuser";
	}

}
