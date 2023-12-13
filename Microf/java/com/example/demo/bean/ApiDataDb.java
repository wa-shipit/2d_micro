package com.example.demo.bean;

public class ApiDataDb {
	int searchId;
	String searchName;
	String searchPass;

	public ApiDataDb() {
	}

	public ApiDataDb(int searchId, String searchName, String searchPass) {
		this.searchId = searchId;
		this.searchName = searchName;
		this.searchPass = searchPass;
	}

	public int getSearchId() {
		return searchId;
	}

	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchPass() {
		return searchPass;
	}

	public void setSearchPass(String searchPass) {
		this.searchPass = searchPass;
	}
}