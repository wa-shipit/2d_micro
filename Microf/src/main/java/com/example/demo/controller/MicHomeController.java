package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicHomeController {
	@RequestMapping(path = "/michome", method = RequestMethod.GET)
	public String copGet() {
		return "michome";

	}

	@RequestMapping(path = "/michome", method = RequestMethod.POST)
	public String copPost() {
		return "michome";
	}
}
