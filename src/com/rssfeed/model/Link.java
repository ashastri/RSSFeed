package com.rssfeed.model;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("serial")
public class Link implements Serializable {
	@SerializedName("attributes")
	private Attribute attributes;

	public Attribute getAttributes() {
		return attributes;
	}

	public void setAttributes(Attribute attributes) {
		this.attributes = attributes;
	};
	
}
