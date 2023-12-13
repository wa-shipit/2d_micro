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
public class MicLoginController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // Loginページ表示
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String showLoginForm() {
        return "miclogin";
    }

    // ログイン処理
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String micloginid, String micpw, Model model) {
        // DBからユーザ情報を取得するクエリ
        String query = "SELECT * FROM miclogin WHERE loginid = ? AND password = ?";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(query, micloginid, micpw);

        // ログイン成功の判定
        if (!resultList.isEmpty()) {
            model.addAttribute("id", micloginid);
            model.addAttribute("pass", micpw);
            return "redirect:/michome"; // ログイン成功時はmichomeにリダイレクト
        } else {
            model.addAttribute("errorMessage", "ログインに失敗しました");
            return "miclogin"; // ログイン失敗時はmicloginを再表示
        }
    }
}
