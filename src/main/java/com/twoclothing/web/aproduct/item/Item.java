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
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "detail")
	private String detail;
	
	@Column(name = "tadid")
	private Integer tadId;
	
	@Column(name = "mbrid")
	private Integer mbrId;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "itemstatus")
	private Integer itemStatus;
	
	@Column(name = "quantity")
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
