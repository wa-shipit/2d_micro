package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//↓「name="xxx"」の「xxx」の部分に模倣したいテーブル名を書く
@Table(name = "esptran")
public class Esptran {

	//主キーには「@Id」を設定する！
	@Id
	//カラム名(列名)を書く。
	@Column(name = "jpn")
	private String jpn;

	@Column(name = "esp")
	private String esp;

	public String getJpn() {
		return jpn;
	}

	public void setJpn(String jpn) {
		this.jpn = jpn;
	}

	public String getEsp() {
		return esp;
	}

	public void setEsp(String esp) {
		this.esp = esp;
	}
}