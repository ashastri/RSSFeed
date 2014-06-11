package com.rssfeed.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("serial")
public class Image implements Serializable {
	private String label;
	@SerializedName("attributes")
	private Attribute height;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Attribute getHeight() {
		return height;
	}
	public void setHeight(Attribute height) {
		this.height = height;
	}

}
