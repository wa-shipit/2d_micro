package com.example.demo.Controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@SpringBootApplication
public class MicrofApplication {

	//一覧表示用
	@RequestMapping(path = "/mylogin", method = RequestMethod.GET)
	public String viewPage() {

		return "myclogin";
	}

	//ログイン検証用
	@RequestMapping(path = "/mylogin", method = RequestMethod.POST)
	public String loginPost(String micloginid, String micpw, HttpSession session) {

		session.setAttribute("micloginid", micloginid);

		return "redirect:/miclogin";
}
}