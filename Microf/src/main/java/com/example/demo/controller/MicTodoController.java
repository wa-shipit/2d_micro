package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicTodoController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	// 新規登録画面表示
	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String getMicAdd() {
		// ここで新規登録画面のHTMLを表示する処理を追加する（未提供のため適宜実装してください）
		// 例: return "micadd"; (micadd.htmlが存在する前提で)
		return "mictodo_add";
	}

	// 新規登録処理
	@RequestMapping(path = "/micadd", method = RequestMethod.POST)
	public String postIns(String month, String day) {
		jdbcTemplate.update("INSERT INTO todo VALUES (?,?);", month, day);
		return "redirect:/micadd";
	}

	// 編集画面表示
	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String getMicEdit() {
		// ここで編集画面のHTMLを表示する処理を追加する（未提供のため適宜実装してください）
		// 例: return "micedit"; (micedit.htmlが存在する前提で)
		return "mictodo_edit";
	}

	// 編集処理
	@RequestMapping(path = "/micedit", method = RequestMethod.POST)
	public String postUpd(String month, String day) {
		jdbcTemplate.update("UPDATE todo SET month = ? WHERE day = ?", month, day);
		return "redirect:/micedit";
	}

	// 削除画面表示
	@RequestMapping(path = "/micdel", method = RequestMethod.GET)
	public String getMicDel() {
		// ここで削除画面のHTMLを表示する処理を追加する（未提供のため適宜実装してください）
		// 例: return "micdel"; (micdel.htmlが存在する前提で)
		return "mictodo_del";
	}

	// 削除処理
	@RequestMapping(path = "/micdel", method = RequestMethod.POST)
	public String postDel(String month, String day) {
		jdbcTemplate.update("DELETE FROM todo WHERE month = ? AND day = ?", month, day);
		return "redirect:/micdel";
	}
}
