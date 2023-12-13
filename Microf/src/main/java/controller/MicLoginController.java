package controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class MicLoginController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/miclogin", method = RequestMethod.GET)
	public String copGet(HttpSession session) {
		String ex = "miclogin";
		session.setAttribute("loginparm1,loginparm2", ex);
		String exexe = (String) session.getAttribute("loginparm1,loginparm2");
		return "/miclogin";
	}

	@RequestMapping(path = "/miclogin", method = RequestMethod.POST)
	public String copPost(String miclogin1, String miclogin2, Model model) {

		java.util.List<Map<String, Object>> resultList = jdbcTemplate
				.queryForList("SELECT * FROM honyarara WHERE honyarara");

		model.addAttribute("miclogin1", miclogin1);
		model.addAttribute("miclogin2", miclogin2);

		return "michome";
	}
}
