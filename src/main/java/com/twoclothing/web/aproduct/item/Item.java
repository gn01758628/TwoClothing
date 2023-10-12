package com.twoclothing.web.aproduct.item;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itemid", updatable = false)
	private Integer itemId;
	
	@Column(name = "itemname")
	private String itemName;
	
	@Column(name = "detail")
	private String detail;
	
	@Column(name = "tagid")
	private Integer tagId;
	
	@Column(name = "mbrid")
	private Integer mbrId;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "itemstatus", columnDefinition = "TINYINT")
	private Integer itemStatus;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	public Item() {
	}
	
	public Item(Integer itemId, String name, String detail, Integer tadId, Integer mbrId, Integer price,
			Integer itemStatus, Integer quantity) {
		this.itemId = itemId;
		this.itemName = name;
		this.detail = detail;
		this.tagId = tadId;
		this.mbrId = mbrId;
		this.price = price;
		this.itemStatus = itemStatus;
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", name=" + itemName + ", detail=" + detail + ", tadId=" + tagId + ", mbrId="
				+ mbrId + ", price=" + price + ", itemStatus=" + itemStatus + ", quantity=" + quantity + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(detail, itemId, itemStatus, mbrId, itemName, price, quantity, tagId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(detail, other.detail) && Objects.equals(itemId, other.itemId)
				&& Objects.equals(itemStatus, other.itemStatus) && Objects.equals(mbrId, other.mbrId)
				&& Objects.equals(itemName, other.itemName) && Objects.equals(price, other.price)
				&& Objects.equals(quantity, other.quantity) && Objects.equals(tagId, other.tagId);
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return itemName;
	}

	public void setName(String name) {
		this.itemName = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getTadId() {
		return tagId;
	}

	public void setTadId(Integer tadId) {
		this.tagId = tadId;
	}

	public Integer getMbrId() {
		return mbrId;
	}

	public void setMbrId(Integer mbrId) {
		this.mbrId = mbrId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(Integer itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
