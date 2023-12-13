package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyFirstController {
	@RequestMapping(path = "/firstpage2", method = RequestMethod.GET)
	public String first() {
		return "myfirst";
	}

	@RequestMapping(path = "/second", method = RequestMethod.GET)
	public String second() {
		return "mysecond";
	}
}