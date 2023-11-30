package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/**
 *
 * @author 澁川匠太郎
 *
 */
@Controller
public class MicLoginController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	//ページ表示用メソッド
	@GetMapping("/miclogin")
	public String copGet() {
		/**
		 * このメソッドでログインページを表示させる。
		 */
		return "miclogin";
	}

	//idとパスワードが入力されたときの処理用メソッド
	@PostMapping("/miclogin")
	public String copPost(String micloginid, String micpw, Model model) {
		/**
		 * このメソッドで検索を行う。
		 * micloginid → micloginテーブル内のloginidの検索条件(ユーザが入力した物)
		 * micpw → micloginテーブル内のpasswordの検索条件(ユーザが入力した物)
		 *
		 * ヒットした場合はホーム画面にリダイレクトをかける。
		 * リスト内のidが空だった場合は再度ログインIDとパスワードを入力させる。
		 */

		//検索結果格納用のリスト
		List<Map<String, Object>> resultList;

		//検索を実行する。
		resultList = jdbcTemplate.queryForList("SELECT * FROM miclogin WHERE loginid = ? AND password = ? ",
				micloginid,
				micpw);

		//判定を行う。
		if (resultList.size() != 0) {
			//成功した場合リダイレクト
			return "redirect:/michome";
		} else {
			//失敗した場合、ログイン画面に再帰
			return "miclogin";
		}

	}
}
