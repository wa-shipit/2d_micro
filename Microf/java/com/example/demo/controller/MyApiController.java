package com.example.demo.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ApiData;
import com.example.demo.bean.ApiDataPra;
import com.example.demo.bean.Gacha;

@RestController
public class MyApiController {

	@PostMapping("/api")
	@CrossOrigin(origins = "*")
	public ApiData postApidata(@RequestBody ApiData apiData) {
		//変数「apiData」に受信したデータが格納されている。

		//apiDataからmessageを取り出し。
		String jusinMessage = apiData.getMessage();

		//HTMLに返すデータ。
		return new ApiData("HTML→Spring(ここで作った文章です): " + apiData.getMessage(), 0);
	}

	//慣れよう問題用
		@PostMapping("/apinare")
		@CrossOrigin(origins = "*")
		public ApiData postApidatanare(@RequestBody ApiData apiData) {
			//変数「apiData」に受信したデータ（Json）が格納されている。
			//apiDataからmessageを取り出し。
			String jusinMessage = apiData.getMessage();

			//apiDataからquantityを取り出してみよう。
			int jusinQuantity = apiData.getQuantity();

			//取り出したmessageとquantityをHTMLに帰してみよう。
			return new ApiData("復習問題です。: " + apiData.getMessage(), apiData.getQuantity());
		}

	//練習問題用
		@PostMapping("/apiPra")
		@CrossOrigin(origins = "*")
		public ApiDataPra postApidataPra(@RequestBody ApiDataPra apiData) {
			//変数「apiData」に受信したデータ（Json）が格納されている。

			//受信データからAPIキーを取得
			String jusinApiKey = apiData.getApiKey();

			//取得したAPIキーが正しい「kokuri1920」かどうかによって返すメセージを変える。
			String returnMoji = "";
			if ("kokuri1920".equals(jusinApiKey)) {
				returnMoji = "正しいAPIキーが入力されました。開発者情報にアクセスが出来ます。";
			} else {
				returnMoji = "間違ったAPIキーです。サービス提供者にAPIキーを問い合わせてください。";
			}

			//取り出したmessageとquantityをHTMLに帰してみよう。
			return new ApiDataPra("問い合わせの結果は…:" + returnMoji);
		}

	///////早めに終わった人向け//////
	@PostMapping("/apiGacha")
	@CrossOrigin(origins = "*")
	public Gacha postApidataGacha() {

		/**
		 * プログラムを以下のような仕様に変更してみよう。
		 * 提供割合を
		 * 30%・・・・沼田先生（SR）
		 * 40%・・・・渡部（R）
		 * 20%・・・・小野先生(SR)
		 * 9%・・・・末原先生(SR)
		 * 1%・・・・竹野谷先生(SSR)
		 * になるようにする。
		 * 名前、画像、レアリティを画面に表示させる。
		 *
		 * それも出来たら・・・。
		 * 10連ガチャを実装してみよう。
		 * ヒント：配列を上手く使う事！！
		 */
		//1-100のランダムな数値を生成し、変数「ransu」に格納する。
		Random rnd = new Random();

		//return用の変数
		String[] name = new String[10];
		String[] rarerity = new String[10];
		String[] imgUrl = new String[10];
		//100%沼田先生が出る。神様のような仕様
		for(int i = 0; i<10; i++) {
			int ransu = rnd.nextInt(100) + 1;
			if (ransu < 31 && ransu > 0) {
				name[i] = "沼田";
				rarerity[i] = "SR";
				imgUrl[i] = "https://www.itc.ac.jp/_cms/wp-content/themes/itc1.1.0/assets/img/teacher/img-teacher-numata-s-on.jpg";
			}
			if (ransu < 71 && ransu > 31) {
				name[i] = "渡部";
				rarerity[i] = "R";
				imgUrl[i] = "https://www.itc.ac.jp/_cms/wp-content/themes/itc1.1.0/assets/img/teacher/img-teacher-watabe-m-on.jpg";
			}
			if (ransu < 91 && ransu > 71) {
				name[i] = "小野";
				rarerity[i] = "SR";
				imgUrl[i] = "https://www.itc.ac.jp/_cms/wp-content/themes/itc1.1.0/assets/img/teacher/img-teacher-ono-k-on.jpg";
			}
			if (ransu < 100 && ransu > 91) {
				name[i] = "末原";
				rarerity[i] = "SR";
				imgUrl[i] = "https://www.itc.ac.jp/_cms/wp-content/themes/itc1.1.0/assets/img/teacher/img-teacher-suehara-a-on.jpg";
			}
			if (ransu == 100) {
				name[i] = "竹野谷";
				rarerity[i] = "SSR";
				imgUrl[i] = "https://www.itc.ac.jp/_cms/wp-content/themes/itc1.1.0/assets/img/teacher/img-teacher-takenoya-y-on.jpg";
			}
		}

		return new Gacha(name, rarerity, imgUrl);
	}
}