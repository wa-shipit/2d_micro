package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.bean.Event;
import com.example.demo.bean.LineData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
public class MyLineApiController {

	Random rand = new Random();
	//ここにチャンネルアクセストークンを貼る！
	String channelAccessToken = "v9aXgI4iXEo4ChovinTnJurJGWKLKgTweDyvvLwR8f/oWhqF70Sv1cSIb9OmRoCrtFHMY5IWHe7aoGKXjnPxK1W77hNoo6CMGr17bRup77jEAc/Y7q4QRJZOeXYRNkAni318/eefPoK4zdOOxlXpyAdB04t89/1O/w1cDnyilFU=";

	@PostMapping("/lineApi")
	@CrossOrigin(origins = "*")
	public void postApidata(@RequestBody LineData webhookData) {
		for (Event event : webhookData.getEvents()) {

			//メッセージを送ってきたアカウント情報を変数「replyToken」に格納する。
			String replyToken = event.getReplyToken();

			///////////////☆☆重要☆☆///////////////////
			/////////////変数「replyText」に送られてきたメッセージが格納されている
			String replyText = event.getMessage().getText();
			//////////////////////////////////////////////

			//取り扱い注意！！！！
			String numataImg = "https://www.itc.ac.jp/_cms/wp-content/themes/itc1.1.0/assets/img/teacher/img-teacher-numata-s-on.jpg";
			String watabeImg = "https://www.itc.ac.jp/_cms/wp-content/themes/itc1.1.0/assets/img/teacher/img-teacher-watabe-m-on.jpg";
			String sueImg = "https://www.itc.ac.jp/_cms/wp-content/themes/itc1.1.0/assets/img/teacher/img-teacher-suehara-a-on.jpg";

			//画像を返す。
			replyImageMessage(replyToken, numataImg, numataImg);

			//LINEから送られてきたﾒｯｾｰｼﾞをチャットGPTに送り、
			//チャットGPTからの返信を変数「gptResult」に格納する。
			String gptResult = openAi(replyText);
			replyMessage(replyToken, gptResult);

			if (replyText.startsWith("質問")) {
				String gpt = openAi(replyText);
				replyMessage(replyToken, gpt);
			}
			if ("何か聞きたい".equals(replyText)) {
				replyMessage(replyToken, "質問をどうぞ！先頭に「質問」をつけて何でも聞いてください！");
			} else if ("国際理工GUYSを見たい".equals(replyText)) {
				replyImageMessage(replyToken, numataImg, numataImg);
			} else {
				replyButtonsTemplate(replyToken);
			}
			//スタンプを送る場合
			replyStampMessage(replyToken);

			//////////////ここまでを見てね。///////////////////////
		}
	}

	/*******************************************************************:
	 * ここから↓は今は気にしないでOK!
	 *******************************************************************/
	private void replyImageMessage(String replyToken, String originalContentUrl, String previewImageUrl) {

		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/reply";

		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);

		// 送信する画像を設定
		Map<String, Object> message = new HashMap<>();
		message.put("type", "image");
		message.put("originalContentUrl", originalContentUrl);
		message.put("previewImageUrl", previewImageUrl);

		// リクエストボディを設定（画像用）
		Map<String, Object> body = new HashMap<>();
		body.put("replyToken", replyToken);
		body.put("messages", Collections.singletonList(message));

		// 画像を送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);

	}

	//文字を送りたい場合はこのメソッドを呼び出す。
	//呼び出す際、第二引数に送りたい文字列を渡す。
	private void replyMessage(String replyToken, String replyText) {
		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/reply";

		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);

		// 送信するメッセージを設定
		Map<String, Object> message = new HashMap<>();
		message.put("type", "text");
		message.put("text", replyText);

		// リクエストボディを設定
		Map<String, Object> body = new HashMap<>();
		body.put("replyToken", replyToken);
		body.put("messages", Collections.singletonList(message));

		System.out.println("test");

		// HTTPリクエストを送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);
	}

	//メッセージ送ってきた人に返信するためのメソッド
	private void replyStampMessage(String replyToken) {
		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/reply";

		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);

		// 送信するメッセージを設定
		Map<String, Object> message = new HashMap<>();
		message.put("type", "sticker");
		message.put("packageId", 446);
		message.put("stickerId", 1988);
		//↑packageIdとstickrIdでどのスタンプを送るか定義している。
		//詳しくは公式Docを見てみよう！
		//https://developers.line.biz/ja/docs/messaging-api/sticker-list/#sticker-definitions

		// リクエストボディを設定
		Map<String, Object> body = new HashMap<>();
		body.put("replyToken", replyToken);
		body.put("messages", Collections.singletonList(message));

		System.out.println("test");

		// HTTPリクエストを送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);
	}

	private String openAi(String replyText) {

		//chatGPTに投げる文章の作成
		String preMessage = "次の質問に答えて。出来るだけ専門用語は使わずに。" + replyText;

		//chatGPTのAPIキー
		String chatGptApiKey = "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";

		//chatGPTのWEBHOOKを叩く。（エンドポイントとも言う）
		String url = "https://api.openai.com/v1/chat/completions";

		//利用するモデル。gpt4にすると高くてやばいので3.5。
		String model = "gpt-3.5-turbo";

		//chatGPTが求めるデータを生成。
		String message = "{\"role\": \"system\", \"content\": \"返答は日本語で\"},{\"role\": \"user\", \"content\": \""
				+ preMessage + "\"}";
		String content = "";
		try {

			//HTTP通信の準備
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Authorization", "Bearer " + chatGptApiKey);

			con.setDoOutput(true);

			//実際に通信する。
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
			System.out.println(message);
			out.write("{\"model\": \"" + model + "\", \"messages\": [" + message + "]}");
			out.close();

			// レスポンスをJsonに変換する
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			Gson gson = new Gson();
			JsonObject jsonResponse = gson.fromJson(in, JsonObject.class);
			JsonArray choicesArray = jsonResponse.getAsJsonArray("choices");
			JsonObject messageObject = choicesArray.get(0).getAsJsonObject().get("message").getAsJsonObject();
			content = messageObject.get("content").getAsString();

			return content;
		} catch (Exception e) {
			System.out.println("catch the error. forget the bad things.");
			return "error";
		}
	}

	/////ここに↓貼る////
	private void replyButtonsTemplate(String replyToken) {
		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/reply";

		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);

		// 1つ目のボタン
		Map<String, Object> button1 = new HashMap<>();
		button1.put("type", "message");
		button1.put("label", "何か聞きたい");
		button1.put("text", "何か聞きたい");

		// 2つ目のボタン
		Map<String, Object> button2 = new HashMap<>();
		button2.put("type", "message");
		button2.put("label", "国際理工GUYSを見たい");
		button2.put("text", "国際理工GUYSを見たい");

		// 3詰のボタン。有料プランに申し込む
		Map<String, Object> button3 = new HashMap<>();
		button3.put("type", "uri");
		button3.put("label", "有料プランに加入したい");
		button3.put("uri", "https://buy.stripe.com/test_9AQaG70Qrgi45Fe288");

		//画面に出したいボタンをリストに詰める。
		List<Map<String, Object>> buttons = Arrays.asList(button1, button2, button3);

		// テンプレートにボタンを追加
		Map<String, Object> template = new HashMap<>();
		template.put("type", "buttons");
		template.put("text", "要件をどうぞー");
		template.put("actions", buttons);

		// メッセージにテンプレートを追加
		Map<String, Object> message = new HashMap<>();
		message.put("type", "template");
		message.put("altText", "ボタンテンプレート");
		message.put("template", template);

		// リクエストボディを設定
		Map<String, Object> body = new HashMap<>();
		body.put("replyToken", replyToken);
		body.put("messages", Collections.singletonList(message));

		// HTTPリクエストを送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);
	}

	////↑ここに貼る////
}