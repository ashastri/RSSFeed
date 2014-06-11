package com.rssfeed.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class Author implements Serializable {
	@SerializedName("name")
	private Label name;
	@SerializedName("uri")
	private Label uri;
	
	public Label getName() {
		return name;
	}
	public void setName(Label name) {
		this.name = name;
	}
}
