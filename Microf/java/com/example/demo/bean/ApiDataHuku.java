package com.example.demo.bean;

public class ApiDataHuku {
	String hukusyu;

	public ApiDataHuku() {
	}

	//コンストラクタ。
	//この部分がJsonデータの中身になる。
	//例えば↓の例で言うと、
	//{message:あいうえお}みたいなデータになる。
	public ApiDataHuku(String hukusyu) {
		this.hukusyu = hukusyu;
	}

	public String getHukusyu() {
		return hukusyu;
	}

	public void setHukusyu(String hukusyu) {
		this.hukusyu = hukusyu;
	}


	//getterとsetter作る
}