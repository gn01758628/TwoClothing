package com.twoclothing.model.aproduct.itemimage;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "itemimage")
public class ItemImage implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "imgid", insertable = false , updatable = false)
	private Integer imgId;
	
	@Column(name = "itemid", nullable = false)
	private Integer itemId;
	
	@Column(name = "image", columnDefinition = "mediumblob")
	private byte[] image;
	
	
	
	public ItemImage() {
	}

	public ItemImage(Integer itemId, byte[] image) {
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}	
}
