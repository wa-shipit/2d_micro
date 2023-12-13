package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Records;
import com.example.demo.repository.RecordsRepository;

@Controller
public class MyRecordsController {

	//リポジトリ使うよ宣言

	@Autowired
	RecordsRepository kakoikun;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/myrecords", method = RequestMethod.GET)
	public String twelfth(Model model) {

		//全件検索(findAll)した結果を変数「userlist」にしまっている。
		List<Records> recordslist = kakoikun.findAll();

		model.addAttribute("recordsslist", recordslist);

		return "myrecords";
	}

	@RequestMapping(path = "/myrecordsInsUpd", method = RequestMethod.GET)
	public String eidht(Model model) {

		//!!! 38行目の/myrecordsInsUpdが
		//　　　なぜか/myrecordsInsUpd}になっていて
		//それがエラーの原因だったよ！



		//SELECT文の結果をしまうためのリスト
		List<Map<String, Object>> recordslist;

		//SELECT文の実行
		recordslist = jdbcTemplate.queryForList("select * from users");

		//実行結果をmodelにしまってHTMLで出せるようにする。
		model.addAttribute("selectResult", recordslist);

		return "myninth";
	}

	//練習問題用
	@RequestMapping(path = "/myrecords", method = RequestMethod.POST)
	public String twelfthPra(Model model, String user_id, String user_name, String game_count, String win) {

		Records myBook = new Records();
		myBook.setUser_id(Integer.parseInt(user_id));
		myBook.setUesr_name(user_name);
		myBook.setGame_count(Integer.parseInt(game_count));
		myBook.setWin(Integer.parseInt(win));

		//Repositoryを介してDBに登録
		//登録するときはsaveメソッドを使う。
		kakoikun.save(myBook);

		return "redirect:/myrecords";
	}

}