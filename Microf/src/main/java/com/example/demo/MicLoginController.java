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

	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/miclogin", method = RequestMethod.GET)
	public String copGet() {
		return "miclogin";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/miclogin", method = RequestMethod.POST)
	public String copPost(int miclogin1, int miclogin2, Model model) {
		boolean validated = false;
		while (!validated) {
		if (miclogin1 > 17) {
		System.out.println("文字数が多すぎます");
		validated = true;
		}
		if (miclogin2 > 17) {
			System.out.println("文字数が多すぎます");
			validated = true;
			}
		}

		//DBに繋ぐならこんな感じ(JdbcTemplate)
			List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM miclogin WHERE loginid = ?. and password = ?");

		model.addAttribute("miclogin1", miclogin1);
		model.addAttribute("miclogin2", miclogin2);

		return "miclogin";
	}
	public static void main(String[] args) {

}
}
