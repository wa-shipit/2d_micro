package com.example.demo;

import org.springframework.stereotype.Controller;

@Controller
public class ExampleController {

	/*	@Autowired
	JdbcTemplate jdbcTemplate;

	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/example", method = RequestMethod.GET)
	public String copGet() {
		return "example";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/example", method = RequestMethod.POST)
	public String copPost(String example1, String example2, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
		//		List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM honyarara WHERE honyarara");

		model.addAttribute("example1", example1);
		model.addAttribute("example2", example2);

		return "example";
	}*/
}
