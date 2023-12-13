package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MySixthController {
	//最初のページ
	@RequestMapping(path = "/mysixth", method = RequestMethod.GET)
	public String sixth() {
		return "mysixth";
	}

	@RequestMapping(path = "/mysixth", method = RequestMethod.POST)
	public String sixth(String yourname, RedirectAttributes redirectAttributes , String myname) {
		redirectAttributes.addFlashAttribute("yourname", yourname);
		redirectAttributes.addFlashAttribute("myname" , myname);
		//ページを跨ぎたい時は、
		//「redirect:/xxx」を使って移動先のGETメソッドのURLを使う。
		return "redirect:/mysixth_four";
	}

	//次のページ
	@RequestMapping(path = "/mysixth_second", method = RequestMethod.GET)
	public String sixthsecond() {
		return "mysixth_second";
	}

	@RequestMapping(path = "/mysixth_third", method = RequestMethod.GET)
	public String sixththird() {
		return "mysixth_third";
	}
	//次のページ
		@RequestMapping(path = "/mysixth_four", method = RequestMethod.GET)
		public String sixthfour() {
			return "mysixth_four";
		}
}