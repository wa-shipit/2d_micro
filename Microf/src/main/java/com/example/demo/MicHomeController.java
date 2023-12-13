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
		public String copGet(Model model) {
			
			
			//SELECT文の結果をしまうためのリスト
			List<Map<String, Object>> resultList;
			List<Map<String, Object>> resultList2;

			//SELECT文の実行
			resultList = jdbcTemplate.queryForList("select * from todo");
			resultList2 = jdbcTemplate.queryForList("select * from miclogin");

			//実行結果をmodelにしまってHTMLで出せるようにする。
			model.addAttribute("selectResult", resultList);
			model.addAttribute("selectResult2", resultList2);


			return "michome";
		}
	
}
