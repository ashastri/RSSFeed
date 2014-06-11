package com.rssfeed.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class Feed implements Serializable {
	@SerializedName("author")
	private Author author;
	@SerializedName("entry")
	private ArrayList<Entry> entry;
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public ArrayList<Entry> getEntry() {
		return entry;
	}
	public void setEntry(ArrayList<Entry> entry) {
		this.entry = entry;
	}

		
}
