package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ApiDataDb;

@RestController
public class MyApiDbController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@PostMapping("/apiDb")
	@CrossOrigin(origins = "*")
	public List<Map<String, Object>> postApidata(@RequestBody ApiDataDb apiData) {
		System.out.println(apiData.getSearchPass());

		//SELECT文の結果をしまうためのリスト
		List<Map<String, Object>> resultList;

		//LIKE文用の文字列
		String likePass = "%" + apiData.getSearchPass() + "%";

		//SELECT文の実行
		resultList = jdbcTemplate.queryForList("select * from users WHERE user_pass like ?;",
				likePass);

		//HTMLに返すデータ。
		return resultList;
	}

}