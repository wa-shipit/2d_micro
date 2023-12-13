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
	public String copPost(String micloginid, String micpw, Model model) {
		
		 micloginid = micloginid.substring(0, Math.min(micloginid.length(), 16));
	        micpw = micpw.substring(0, Math.min(micpw.length(), 16));

	        if (micloginid.length() > 16 || micpw.length() > 16) {
	            model.addAttribute("error", "文字数が多すぎます");
	            return "miclogin";
	        }


		//SELECT文の結果をしまうためのリスト
		 List<Map<String, Object>> resultList;

		//SELECT文の実行
		resultList = jdbcTemplate.queryForList("SELECT * FROM miclogin WHERE loginid = ? AND password = ?",
				micloginid, micpw);

		 if (!resultList.isEmpty()) {
		        return "redirect:/michome";
		    } else {
		    	 System.out.println("ログインに失敗しました");
		        return "redirect:/miclogin";
		    }
		}
	@RequestMapping(path = "/michome", method = RequestMethod.GET)
	public String michome() {
		return "michome";
	}
}
