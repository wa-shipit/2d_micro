package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyFourthController {
	@RequestMapping(path = "/myfourth", method = RequestMethod.GET)
	public String third() {
		return "myfourth";
	}

	@RequestMapping(path = "/myfourth", method = RequestMethod.POST)
	public String third(String yourname, String myname, String practice) {
		System.out.println(yourname);
		System.out.println(myname);
		System.out.println(practice);
		return "myfourth";
	}
}