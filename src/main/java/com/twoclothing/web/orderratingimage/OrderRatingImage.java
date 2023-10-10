package com.twoclothing.web.orderratingimage;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class OrderRatingImage implements Serializable {
	private Integer imageId;
	private Integer orderId;
	private Byte[] image;

	public OrderRatingImage() {
	}

	public OrderRatingImage(Integer imageId, Integer orderId, Byte[] image) {
		this.imageId = imageId;
		this.orderId = orderId;
		this.image = image;
	}

	@Override
	public String toString() {
		return "OrderRatingImage [imageId=" + imageId + ", orderId=" + orderId + ", image=" + Arrays.toString(image)
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + Objects.hash(imageId, orderId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderRatingImage other = (OrderRatingImage) obj;
		return Arrays.equals(image, other.image) && Objects.equals(imageId, other.imageId)
				&& Objects.equals(orderId, other.orderId);
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}
}
