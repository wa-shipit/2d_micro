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
		//SELECT文の結果をしまうためのリスト
		 List<Map<String, Object>> todoList;
		 //SELECT文の実行
		 todoList = jdbcTemplate.queryForList("SELECT * FROM todo");
		 //実行結果をmodelにしまってHTMLで出せるようにする。
	        // todoListをモデルに追加
	        model.addAttribute("todoList", todoList);

	        return "michome";
	    }
	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String copGet1() {
		return "micadd";
	}
	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String copGet2() {
		return "micedit";
	}
	@RequestMapping(path = "/micdel", method = RequestMethod.GET)
	public String copGet3() {
		return "micdel";
	}
}
