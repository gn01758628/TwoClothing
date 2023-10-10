package com.twoclothing.web.newsimage;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class NewsImage implements Serializable {

	private Integer imageId;
	private Integer newsId;
	private byte[] image;
	private String descriptions;

	public NewsImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewsImage(Integer newsId, byte[] image, String descriptions) {
		super();
		this.newsId = newsId;
		this.image = image;
		this.descriptions = descriptions;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(imageId, newsId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewsImage other = (NewsImage) obj;
		return Objects.equals(imageId, other.imageId) && Objects.equals(newsId, other.newsId);
	}

	@Override
	public String toString() {
		String imageStr;
		if (image == null) {
			imageStr = "null";
		} else {
			imageStr = String.valueOf(image.length);
		}

		return "NewsImage [imageId=" + imageId + ", newsId=" + newsId + ", image=" + imageStr + ", descriptions="
				+ descriptions + "]";
	}

}
