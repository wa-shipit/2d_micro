package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyFifthFinalController {

	@RequestMapping(path = "/fifthfinal", method = RequestMethod.GET)
	public String fifth() {

		return "myfifthfinal";
	}

	@RequestMapping(path = "/fifthfinal", method = RequestMethod.POST)
	public String fifth(Model model , String[] sushi) {

		/**
		 * 画面から入力された内容をSysout
		 */
		for (int i = 0; i < sushi.length; i++) {
			System.out.println("好きな食べ物:" + sushi[i]);
		}

		/**
		 * HTMLで表示するために、modelに格納する。
		 */

		model.addAttribute("sushilist", sushi);
		return "myfifthfinal";
	}

}
