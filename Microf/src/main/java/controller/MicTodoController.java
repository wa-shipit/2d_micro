package controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicTodoController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String add() {
		return "micadd";
	}

	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String edit() {
		return "micedit";
	}

	@RequestMapping(path = "/micdel", method = RequestMethod.GET)
	public String del() {
		return "micdel";
	}

	@RequestMapping(path = "/micadd", method = RequestMethod.POST)
	public String addPost(String month, String day, String todo, Model model) {

		String id = "a";
		month = month + "月";
		day = day + "日の予定";
		todo = "・" + todo;

		List<Map<String, Object>> resultList0;
		resultList0 = jdbcTemplate.queryForList("SELECT * FROM todo where month = ? and day = ?;", month, day);
		System.out.println(resultList0);
		if (resultList0.size() == 0) {
			jdbcTemplate.update("INSERT INTO todo (user_id, month, day, todo) VALUES (?,?,?,?)", id, month, day, todo);
			return "redirect:/michome";
		} else {
			model.addAttribute("example1", "すでにその曜日の予定が存在します。");
			return "micadd";
		}
	}

	@RequestMapping(path = "/micedit", method = RequestMethod.POST)
	public String editPost(String month, String day, String todo, Model model) {

		month = month + "月";
		day = day + "日の予定";
		todo = "・" + todo;
		List<Map<String, Object>> resultList0;
		resultList0 = jdbcTemplate.queryForList("SELECT * FROM todo where month = ? and day = ?;", month, day);
		System.out.println(resultList0);
		if (resultList0.size() != 0) {
			jdbcTemplate.update("UPDATE todo SET month = ? where month = ? and day = ?", month, month, day);
			jdbcTemplate.update("UPDATE todo SET day= ? where month = ? and day = ?", day, month, day);
			jdbcTemplate.update("UPDATE todo SET todo = ? where month = ? and day = ?", todo, month, day);
			return "redirect:/michome";
		} else {
			model.addAttribute("example1", "その曜日にはまだ予定がありません。");
			return "micedit";
		}
	}

	@RequestMapping(path = "/micdel", method = RequestMethod.POST)
	public String delPost(String month, String day, String todo, Model model) {

		month = month + "月";
		day = day + "日の予定";
		todo = "・" + todo;
		List<Map<String, Object>> resultList0;
		resultList0 = jdbcTemplate.queryForList("SELECT * FROM todo where month = ? and day = ?;", month, day);
		System.out.println(resultList0);
		if (resultList0.size() != 0) {
			jdbcTemplate.update("delete from todo where month = ? and day = ?", month, day);
			return "redirect:/michome";
		} else {
			model.addAttribute("example1", "その曜日にはまだ予定がありません。");
			return "micedit";
		}
	}
}
