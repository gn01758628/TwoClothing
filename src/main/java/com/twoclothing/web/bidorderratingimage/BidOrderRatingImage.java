package com.twoclothing.web.bidorderratingimage;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class BidOrderRatingImage implements Serializable{
	private Integer imageId;
	private Integer bidOrderId;
	private byte[] image;

	public BidOrderRatingImage() {
		super();
	}
	

	public BidOrderRatingImage(Integer imageId, Integer bidOrderId, byte[] image) {
		super();
		this.imageId = imageId;
		this.bidOrderId = bidOrderId;
		this.image = image;
	}

	@Override
	public String toString() {
		return "BidOrderRatingImage [imageId=" + imageId + ", bidOrderId=" + bidOrderId + ", image="
				+ Arrays.toString(image) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + Objects.hash(bidOrderId, imageId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		BidOrderRatingImage other = (BidOrderRatingImage) obj;
		return Objects.equals(bidOrderId, other.bidOrderId) && Arrays.equals(image, other.image)
				&& Objects.equals(imageId, other.imageId);
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public Integer getBidOrderId() {
		return bidOrderId;
	}

	public void setBidOrderId(Integer bidOrderId) {
		this.bidOrderId = bidOrderId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
