package com.twoclothing.web.itemimage;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class ItemImage implements Serializable {
	private Integer imgId;
	private Integer itemId;
	private Byte[] image;
	
	
	
	public ItemImage() {
	}

	public ItemImage(Integer imgId, Integer itemId, Byte[] image) {
		this.imgId = imgId;
		this.itemId = itemId;
		this.image = image;
	}

	@Override
	public String toString() {
		return "ItemImage [imgId=" + imgId + ", itemId=" + itemId + ", image=" + Arrays.toString(image) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + Objects.hash(imgId, itemId);
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
		ItemImage other = (ItemImage) obj;
		return Arrays.equals(image, other.image) && Objects.equals(imgId, other.imgId)
				&& Objects.equals(itemId, other.itemId);
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}	
}
