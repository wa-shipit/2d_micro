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

	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/michome", method = RequestMethod.GET)
	public String michome(Model model) {

		int userId= 999999;

		//SELECT文の結果をしまうためのリスト
		List<Map<String, Object>> resultList;
		//SELECT文の実行
		resultList = jdbcTemplate.queryForList("SELECT * FROM ToDo where user_id=?;",userId);
		//実行結果をmodelにしまってHTMLで出せるようにする。
		model.addAttribute("selectResult", resultList);
		 model.addAttribute("userId", userId);

		return"michome";
	}

}


