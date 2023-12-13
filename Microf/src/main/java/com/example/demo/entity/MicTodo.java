package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//↓「name="xxx"」の「xxx」の部分に模倣したいテーブル名を書く
@Table(name = "MicTodos")
public class MicTodo {

	//主キーには「@Id」を設定する！
	@Id
	//カラム名(列名)を書く。
	@Column(name = "user_id")
	private String user_id;

	@Column(name = "month")
	private String month;

	@Column(name = "day")
	private String day;

	@Column(name = "todo")
	private String todo;

	public String getuser_id() {
		return user_id;
	}

	public void setuser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getmonth() {
		return month;
	}

	public void setmonth(String month) {
		this.month = month;
	}

	public String getday() {
		return day;
	}

	public void setday(String day) {
		this.day = day;
	}

	public String gettodo() {
		return todo;
	}

	public void settodo(String todo) {
		this.todo = todo;
	}

}
