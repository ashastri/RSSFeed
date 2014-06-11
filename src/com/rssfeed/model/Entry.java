package com.rssfeed.model;

import java.io.Serializable;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class Entry implements Serializable {
	@SerializedName("im:name")
	private Label name;
	@SerializedName("im:image")
	private ArrayList<Image> image;
	private Label summary;
	@SerializedName("im:price")
	private Price price;
	private Label rights;
	private Label title;
	@SerializedName("link")
	private Link link;
	@SerializedName("id")
	private ID id;
	@SerializedName("im:artist")
	private Artist artist;
	@SerializedName("im:releaseDate")
	private ReleaseDate releaseDate;
	public Label getName() {
		return name;
	}
	public void setName(Label name) {
		this.name = name;
	}
	public ArrayList<Image> getImage() {
		return image;
	}
	public void setImage(ArrayList<Image> image) {
		this.image = image;
	}
	public Label getSummary() {
		return summary;
	}
	public void setSummary(Label summary) {
		this.summary = summary;
	}
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	public Label getRights() {
		return rights;
	}
	public void setRights(Label rights) {
		this.rights = rights;
	}
	public Label getTitle() {
		return title;
	}
	public void setTitle(Label title) {
		this.title = title;
	}
	public Link getLink() {
		return link;
	}
	public void setLink(Link link) {
		this.link = link;
	}
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public ReleaseDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(ReleaseDate releaseDate) {
		this.releaseDate = releaseDate;
	}
}
