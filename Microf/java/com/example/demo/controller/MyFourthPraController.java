package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyFourthPraController {

	@RequestMapping(path = "/myfourthpra", method = RequestMethod.GET)
	public String third() {
		return "myfourthpra";
	}

	@RequestMapping(path = "/myfourthpra", method = RequestMethod.POST)
	public String third(String yourname2 , String myname2) {

		System.out.println(yourname2);
		System.out.println(myname2);

		return "myfourthpra";
	}

}