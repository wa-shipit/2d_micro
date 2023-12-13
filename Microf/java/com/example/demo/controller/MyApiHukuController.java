package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ApiDataHuku;

@RestController
public class MyApiHukuController {

		@PostMapping("/apiHuku")
		@CrossOrigin(origins = "*")
		public ApiDataHuku postApidata(@RequestBody ApiDataHuku apiData) {
			//変数「apiData」に受信したデータが格納されている。

			//apiDataからhukusyuを取り出し。
			String jusinMessage = apiData.getHukusyu();

			//HTMLに返すデータ。
			return new ApiDataHuku(jusinMessage + "復習問題Controllerで受信した");
		}

}