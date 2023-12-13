package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Hannou;
import com.example.demo.repository.HannouRepository;

@Controller
public class MyAppHanController {

	@Autowired
	private SimpMessagingTemplate template;

	//DB接続用//
	@Autowired
	HannouRepository hannouRepository;

	@RequestMapping(path = "/myapphan", method = RequestMethod.GET)
	public String input1() {
		return "myapphan";
	}

	//ログページ表示
		@RequestMapping(path = "/myapphanlog", method = RequestMethod.GET)
		public String input2(Model model) {

			//①全件検索
			List<Hannou> hanList = hannouRepository.findAll();

			//②modelに格納。モデルに格納するときは「model.addAttribute("名前",値);」と書く
			model.addAttribute("list", hanList);

			//HTMLの表示
			return "myapphanlog";
		}

	/*******反応するボタンを押したときの処理*******/
	@MessageMapping("/hannou")
	public void send1(@Payload String pname) {
		//反応した時刻をミリ秒単位で取得して変数「hanTime」に格納
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");
		String hanTime = sdf.format(now);

		/**データベースに対してINSERT処理を行う。**/
		//①hannouRepositoryを利用して全件検索を行う。
				List<Hannou> hanList = hannouRepository.findAll();;

		//②検索結果「hanList」のデータ件数に「1」を足して列「data_id」の値とする。
		//  データ件数は、「件数を測りたいリスト.size()」で取れる。
				int data_id = (hanList.size() + 1);

		//③Hannouエンティティのインスタンスを生成して、パラメータをセットする。
				Hannou han = new Hannou();
				han.setData_id(data_id);
				han.setUser_name(pname);
				han.setCategory("反応");
				han.setTime(hanTime);

		//④hannouRepositoryを利用してsaveする。
				hannouRepository.save(han);

		//一通り処理が終わったので、チャネルにメッセージを流しておわり。
		template.convertAndSend("/topic/hannou", pname + "が" + hanTime + "に反応した！");
	}

	/*********************************************************/

	/*******仕掛けるボタンを押したときの処理*******/
	@MessageMapping("/sikake")
	public void send2(@Payload String pname) {
		//反応した時刻をミリ秒単位で取得して変数「hanTime」に格納
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");
		String hanTime = sdf.format(now);

		/**データベースに対してINSERT処理を行う。**/
		//①hannouRepositoryを利用して全件検索を行う。
				List<Hannou> hanList = hannouRepository.findAll();

		//②検索結果リスト「hanList」のデータ件数に「1」を足して列「data_id」の値とする。
		  //データ件数は、「件数を測りたいリスト.size()」で取れる。
				int data_id = (hanList.size() + 1);

		//③Hannouエンティティのインスタンスを生成して、パラメータをセットする。
				Hannou han = new Hannou();
				han.setData_id(data_id);
				han.setUser_name(pname);
				han.setCategory("仕掛け");
				han.setTime(hanTime);

		//④hannouRepositoryを利用してDBに保存する。
				hannouRepository.save(han);

		//処理が一通り終わったので、チャネルにメッセージを流しておわり。
		template.convertAndSend("/topic/sikake", pname + "が" + hanTime + "に仕掛けた！");
	}
	/*********************************************************/
}