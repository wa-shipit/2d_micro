package com.example.demo.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SNS_Controller {

	//DBへつなぐために必要
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/mylogin", method = RequestMethod.GET)
	public String sixth() {
		return "SNS";//ここに指定されたhtml名を入力
	}

	@RequestMapping(path = "/regist", method = RequestMethod.GET)
	public String six(Model model) {
		//SELECT文の結果をしまうためのリスト
		List<Map<String, Object>> resultList;

		//SELECT文の実行
		resultList = jdbcTemplate.queryForList("select * from users");

		//実行結果をmodelにしまってHTMLで出せるようにする。
		model.addAttribute("selectResult", resultList);

		return "SNS_regist";//ここに指定されたhtml名を入力
	}

	//[/SNS]ここにURLを入力
	@RequestMapping(path = "/mylogin", method = RequestMethod.POST)
	public String sns(String id, RedirectAttributes redirectAttributes, String password) {
		if (id.equals("kokuri") && password.equals("1920")) {
			redirectAttributes.addFlashAttribute("id", id);
			redirectAttributes.addFlashAttribute("password", password);
			return "redirect:/home";
		} else {
			return "redirect:/ng";
		}
	}

	//次のページ
	//[/SNS_second]ここにURLを入力
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public String snshome() {
		return "SNS_home";
	}

	@RequestMapping(path = "/ng", method = RequestMethod.GET)
	public String snsng() {
		return "SNS_ng";
	}

	@RequestMapping(path = "/home", method = RequestMethod.POST)
	public String home(MultipartFile myfile, Model model, String comment) throws IOException {

		//アップロードされたファイルをバイトデータに変換する。
		byte[] byteData = myfile.getBytes();

		//Base64に変換する。
		String encodedImage = Base64.getEncoder().encodeToString(byteData);

		//Base64に変換した画像データをmodelに格納する。
		model.addAttribute("encodedImage", encodedImage);
		model.addAttribute("comment", comment);

		return "SNS_home";
	}

	@RequestMapping(path = "/regist", method = RequestMethod.POST)
	public String user(String id_1, String password_1, Model model){
		jdbcTemplate.update("INSERT INTO users (user_id,user_pass) VALUES(?,?);", id_1,
				password_1);

		return "redirect:/SNS_regist";
	}
}