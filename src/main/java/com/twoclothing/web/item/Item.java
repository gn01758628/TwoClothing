package com.twoclothing.web.item;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable{
	private Integer itemId;
	private String itemName;
	private String detail;
	private Integer tadId;
	private Integer mbrId;
	private Integer price;
	private Integer itemStatus;
	private Integer quantity;
	
	public Item() {
	}

	public Item(Integer itemId, String itemName, String detail, Integer tadId, Integer mbrId, Integer price, Integer itemStatus, Integer quantity) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.detail = detail;
		this.tadId = tadId;
		this.mbrId = mbrId;
		this.price = price;
		this.itemStatus = itemStatus;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item{" +
				"itemId=" + itemId +
				", itemName='" + itemName + '\'' +
				", detail='" + detail + '\'' +
				", tadId=" + tadId +
				", mbrId=" + mbrId +
				", price=" + price +
				", itemStatus=" + itemStatus +
				", quantity=" + quantity +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Item item = (Item) o;
		return Objects.equals(itemId, item.itemId) && Objects.equals(itemName, item.itemName) && Objects.equals(detail, item.detail) && Objects.equals(tadId, item.tadId) && Objects.equals(mbrId, item.mbrId) && Objects.equals(price, item.price) && Objects.equals(itemStatus, item.itemStatus) && Objects.equals(quantity, item.quantity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemId, itemName, detail, tadId, mbrId, price, itemStatus, quantity);
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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
