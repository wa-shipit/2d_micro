package com.example.demo.bean;

public class ApiDataPra {
	String apikey;

	public ApiDataPra() {
	}

	//コンストラクタ。
	//この部分がJsonデータの中身になる。
	//例えば↓の例で言うと、
	//{message:あいうえお}みたいなデータになる。
	public ApiDataPra(String apikey) {
		this.apikey = apikey;
	}

	public String getApiKey() {
		return apikey;
	}

	public void setApiKey(String apikey) {
		this.apikey = apikey;
	}

}