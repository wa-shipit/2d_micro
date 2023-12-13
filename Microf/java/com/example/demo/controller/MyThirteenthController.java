package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Books;
import com.example.demo.entity.Drinks;
import com.example.demo.repository.BooksRepository;
import com.example.demo.repository.DrinksRepository;

@Controller
public class MyThirteenthController {

	//リポジトリ使うよ宣言
	@Autowired
	BooksRepository booksRepository;

	@Autowired
	DrinksRepository drinksRepository;

	@RequestMapping(path = "/mythirteendrinks", method = RequestMethod.GET)
	public String twelfth(Model model) {

		//全件検索(findAll)した結果を変数「userlist」にしまっている。
		List<Drinks> drinkslist = drinksRepository.findAll();

		model.addAttribute("drinkslist", drinkslist);

		return "mythirteendrinks";
	}

	//練習問題用
	@RequestMapping(path = "/mythirteen", method = RequestMethod.POST)
	public String twelfthPra(Model model, String book_id, String book_name, String book_page, String author) {

		Books myBook = new Books();
		myBook.setBook_id(Integer.parseInt(book_id));
		myBook.setBook_name(book_name);
		myBook.setBook_page(Integer.parseInt(book_page));
		myBook.setAuthor(author);

		//Repositoryを介してDBに登録
		//登録するときはsaveメソッドを使う。
		booksRepository.save(myBook);

		return "redirect:/mythirteen";
	}

	@RequestMapping(path = "/mythirteendrinks", method = RequestMethod.POST)
	public String twelfthPra2(Model model, int drink_id, String drink_name, int drink_price) {

		Drinks myDrink = new Drinks();
		myDrink.setDrink_id(drink_id);
		myDrink.setDrink_name(drink_name);
		myDrink.setDrink_price(drink_price);

		//Repositoryを介してDBに登録
		//登録するときはsaveメソッドを使う。
		drinksRepository.save(myDrink);

		return "redirect:/mythirteendrinks";
	}

}