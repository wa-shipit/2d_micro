package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MicLoginController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/miclogin", method = RequestMethod.GET)
    public String copGet() {
        return "miclogin";
    }

    @RequestMapping(path = "/miclogin", method = RequestMethod.POST)
    public String copPost(String miclogin1, String miclogin2, Model model, RedirectAttributes redirectAttributes) {

        if (miclogin1 == null || miclogin2 == null) {
            model.addAttribute("message", "IDまたはパスワードが入力されていません");
            return "miclogin";
        }

        if (miclogin1.length() > 16 || miclogin2.length() > 16) {
            model.addAttribute("message", "文字数が多すぎます");
            return "miclogin";
        }

        List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM miclogin WHERE loginid = ? AND password = ?", miclogin1, miclogin2);

        if (resultList.isEmpty()) {
            model.addAttribute("message", "ログインに失敗しました");
            return "miclogin";
        } else {
            redirectAttributes.addFlashAttribute("loginUser", miclogin1);
            return "redirect:/michome";
        }
    }
}
    