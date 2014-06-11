package com.rssfeed.model;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Price implements Serializable {
	private String label;
	private Attribute attributes;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Attribute getAttribute() {
		return attributes;
	}
	public void setAttribute(Attribute attributes) {
		this.attributes = attributes;
	}


	
}
