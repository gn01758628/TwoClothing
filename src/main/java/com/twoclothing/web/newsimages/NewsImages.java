package com.twoclothing.web.newsimages;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "newsimages")
public class NewsImages implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "imageid", updatable = false)
	private Integer imageId;
	@Column(name = "newsid", updatable = false)
	private Integer newsId;
	@Column(name = "image", columnDefinition = "MEDIUMBLOB")
	private byte[] image;
	@Column(name = "descriptions")
	private String descriptions;

	public NewsImages() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewsImages(Integer newsId, byte[] image, String descriptions) {
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
		NewsImages other = (NewsImages) obj;
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
