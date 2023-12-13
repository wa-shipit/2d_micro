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
public class MicHomeController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//コピペ用サンプル(ページ表示用メソッド)	//getでTODOを表示
	@RequestMapping(path = "/michome", method = RequestMethod.GET)
	public String copGet(String month, String day, String todo, Model model) {
		List<Map<String, Object>> resultList;

		//SELECT文の実行
		resultList = jdbcTemplate.queryForList("select * from todo");

		//実行結果をmodelにしまってHTMLで出せるようにする。
		model.addAttribute("selectResult", resultList);

		return "michome";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/michome", method = RequestMethod.POST)
	public String copPost(String month, String day, String todo, String example1, String example2, Model model) {

		System.out.println(month + "月" + day + "日の予定");

		model.addAttribute("example1", example1);
		model.addAttribute("example2", example2);

		return "michome";
	}
}
