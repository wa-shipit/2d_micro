package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//↓「name="xxx"」の「xxx」の部分に模倣したいテーブル名を書く
@Table(name = "records")
public class Records {

	//主キーには「@Id」を設定する！
	@Id
	//カラム名(列名)を書く。
	@Column(name = "user_id")
	private int user_id;

	@Column(name = "user_name")
	private String uesr_name;

	@Column(name = "game_count")
	private int game_count;

	@Column(name = "win")
	private int win;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUesr_name() {
		return uesr_name;
	}

	public void setUesr_name(String uesr_name) {
		this.uesr_name = uesr_name;
	}

	public int getGame_count() {
		return game_count;
	}

	public void setGame_count(int game_count) {
		this.game_count = game_count;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}


}