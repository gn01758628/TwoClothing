package com.twoclothing.web.item;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable{
	private Integer itemId;
	private String name;
	private String detail;
	private Integer tadId;
	private Integer mbrId;
	private Integer price;
	private Integer itemStatus;
	private Integer quantity;
	
	public Item() {
	}
	
	public Item(Integer itemId, String name, String detail, Integer tadId, Integer mbrId, Integer price,
			Integer itemStatus, Integer quantity) {
		this.itemId = itemId;
		this.name = name;
		this.detail = detail;
		this.tadId = tadId;
		this.mbrId = mbrId;
		this.price = price;
		this.itemStatus = itemStatus;
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", name=" + name + ", detail=" + detail + ", tadId=" + tadId + ", mbrId="
				+ mbrId + ", price=" + price + ", itemStatus=" + itemStatus + ", quantity=" + quantity + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(detail, itemId, itemStatus, mbrId, name, price, quantity, tadId);
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
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price)
				&& Objects.equals(quantity, other.quantity) && Objects.equals(tadId, other.tadId);
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getTadId() {
		return tadId;
	}

	public void setTadId(Integer tadId) {
		this.tadId = tadId;
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
