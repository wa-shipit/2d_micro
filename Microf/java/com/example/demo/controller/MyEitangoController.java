/*package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Eitango;
import com.example.demo.repository.EitangoRepository;

@Controller
public class MyEitangoController {

	//リポジトリ使うよ宣言

	@Autowired
	EitangoRepository eitango;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/myeitango", method = RequestMethod.GET)
	public String tango(Model model) {

		//全件検索(findAll)した結果を変数「userlist」にしまっている。
		List<Eitango> eitangolist = eitango.findAll();

		model.addAttribute("eitangolist", eitangolist);

		return "myeitango";
	}

	@RequestMapping(path = "/myeitango2", method = RequestMethod.GET)
	public String twelfth(Model model, String jpn) {

		List<Map<String, Object>> eitangolist;

		//SELECT文の実行
		eitangolist = jdbcTemplate.queryForList("select * from eitango where jpn = ?;" , jpn);

		//実行結果をmodelにしまってHTMLで出せるようにする。
		model.addAttribute("selectResult", eitangolist);

		return "redirect:/myeitango";
	}

	@RequestMapping(path = "/myeitango", method = RequestMethod.POST)
	public String twelfthPra(Model model, String eng, String jpn) {

		Eitango myEitango = new Eitango();
		myEitango.setEng(eng);
		myEitango.setJpn(jpn);


		//Repositoryを介してDBに登録
		//登録するときはsaveメソッドを使う。
		eitango.save(myEitango);

		return "redirect:/myeitango";
	}

}


*/

package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repository.EitangoRepository;

@Controller
public class MyEitangoController {

	@Autowired
	EitangoRepository eitango;

	@Autowired
	JdbcTemplate jdbcTemplate;

	//一覧表示用
	@RequestMapping(path = "/myeitango", method = RequestMethod.GET)
	public String viewPage(Model model) {

		List<Map<String, Object>> resultList;
		resultList = jdbcTemplate.queryForList("SELECT * FROM eitango;");

		model.addAttribute("tangoList", resultList);

		return "myeitango";
	}

	//INSERT（登録）用メソッド
	@RequestMapping(path = "/myeitangoIns", method = RequestMethod.POST)
	public String postIns(String eng, String jpn) {

		jdbcTemplate.update("INSERT INTO eitango VALUES (?,?);", eng, jpn);

		return "redirect:/myeitango";
	}

	//更新（更新）用メソッド
	@RequestMapping(path = "/myeitangoUpd", method = RequestMethod.POST)
	public String postUpd(String eng, String jpn) {

		jdbcTemplate.update("UPDATE eitango SET jpn = ? WHERE eng = ?", jpn, eng);

		return "redirect:/myeitango";
	}

	//追加問題用
	@RequestMapping(path = "/myeitangoSr", method = RequestMethod.POST)
	public String postUpd(String jpn, Model model) {

		/**
		 * 冊子のModel,form,DB操作を参考にしてみよう。
		 */
		List<Map<String, Object>> resultList;
		resultList = jdbcTemplate.queryForList("SELECT * FROM eitango WHERE jpn = ?;", jpn);

		model.addAttribute("tangoList", resultList);

		return "myeitango";
	}
}