package com.rssfeed.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Label implements Serializable {
	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
