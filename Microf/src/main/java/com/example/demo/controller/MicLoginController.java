package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicLoginController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/miclogin", method = RequestMethod.GET)
	public String micloginGet() {
		return "miclogin";
	}


	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/miclogin", method = RequestMethod.POST)
	public String micloginPost(String micloginid, String micpw, Model model,HttpSession session) {

		if (micloginid.length() > 16 || micpw.length() > 16) {
			model.addAttribute("moji", "文字数が多すぎます");
			return "miclogin";
		} else {

			//DBに繋ぐならこんな感じ(JdbcTemplate)
			List<Map<String, Object>> resultList = jdbcTemplate
					.queryForList("SELECT * FROM miclogin WHERE loginid = ? and password = ? ", micloginid, micpw);
			System.out.println(resultList.size());
			if (!CollectionUtils.isEmpty(resultList)) {
				return "redirect:/michome";
			} else {
				model.addAttribute("error", "ログインに失敗しました");
				return "miclogin";
			}

		}

	}
}
