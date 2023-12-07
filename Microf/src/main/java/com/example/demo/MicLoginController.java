package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class MicLoginController {

	@Autowired
	JdbcTemplate jdbcTemplate;
    // HttpSession型のフィールドを定義する
    private HttpSession session;

    // コンストラクタを作成し、@Autowiredアノテーションを付与する
    @Autowired
    public MicLoginController(HttpSession session) {
        // フィールドに代入する
        this.session = session;
    }

	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/miclogin", method = RequestMethod.GET)
	public String micloginGet() {
		return "miclogin";
	}

	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/michome", method = RequestMethod.GET)
	public String michomeGet(Model model) {
		// 名前に紐づいたデータが存在しない場合はnullを返します
		String id = (String)this.session.getAttribute("micloginid");
		// 名前に紐づいたデータが存在しない場合はnullを返します
		String pw = (String)this.session.getAttribute("micpw");

		model.addAttribute("micloginid", id);
		model.addAttribute("micpw", pw);

		return "michome";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/miclogin", method = RequestMethod.POST)
	public String copPost(String micloginid, String micpw, Model model) {

		if (micloginid.length() < 16 || micpw.length() < 16) {
			//SELECT文の結果をしまうためのリスト
			List<Map<String, Object>> resultList;

			//DBに繋ぐならこんな感じ(JdbcTemplate)
			resultList = jdbcTemplate.queryForList("SELECT * FROM miclogin WHERE loginid=? AND password=?", micloginid,
					micpw);

			if (resultList.isEmpty()) {
				model.addAttribute("loginerror", "ログイン失敗");
				return "miclogin";
			} else {
				String strid = micloginid.toString();
				this.session.setAttribute("micloginid", strid);
				String strpw = micpw.toString();
				this.session.setAttribute("micpw", strpw);
				return "redirect:/michome";
			}
		}
		model.addAttribute("loginerror", "文字数が多すぎます");
		return "miclogin";
	}
}
