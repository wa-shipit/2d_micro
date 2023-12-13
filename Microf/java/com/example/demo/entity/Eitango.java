package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//↓「name="xxx"」の「xxx」の部分に模倣したいテーブル名を書く
@Table(name = "eitango")
public class Eitango {

	//主キーには「@Id」を設定する！
	@Id
	//カラム名(列名)を書く。

	@Column(name = "eng")
	private String eng;
	@Column(name = "jpn")
	private String jpn;
	public String getEng() {
		return eng;
	}
	public void setEng(String eng) {
		this.eng = eng;
	}
	public String getJpn() {
		return jpn;
	}
	public void setJpn(String jpn) {
		this.jpn = jpn;
	}

}