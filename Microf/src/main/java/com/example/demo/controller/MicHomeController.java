package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicHomeController {
	@RequestMapping(path = "/michome", method = RequestMethod.GET)
	public String copGet1() {
		return "michome";

	}

	@RequestMapping(path = "/michome", method = RequestMethod.POST)
	public String copPost() {
		return "michome";
	}

	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String copGet2() {
		return "michome";

	}

	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String copGet3() {
		return "michome";

	}

	@RequestMapping(path = "/micdel", method = RequestMethod.GET)
	public String copGet4() {
		return "michome";

	}
}
