package com.twoclothing.model.aproduct.item;

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
	@Column(name = "itemid", insertable = false , updatable = false)
	private Integer itemId;
	
	@Column(name = "itemname" , nullable = false)
	private String itemName;
	
	@Column(name = "grade", columnDefinition = "TINYINT" , nullable = false)
	private Integer grade;
	
	@Column(name = "size", columnDefinition = "TINYINT" , nullable = false)
	private Integer size;
	
	@Column(name = "detail")
	private String detail;
	
	@Column(name = "tagid" , nullable = false)
	private Integer tagId;
	
	@Column(name = "mbrid" , nullable = false)
	private Integer mbrId;
	
	@Column(name = "price" , nullable = false)
	private Integer price;
	
	@Column(name = "itemstatus", columnDefinition = "TINYINT", nullable = false)
	private Integer itemStatus;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	public Item() {
	}
	
	public Item(Integer itemId, String itemName, Integer grade, Integer size, String detail, Integer tagId, Integer mbrId,
			Integer price, Integer itemStatus, Integer quantity) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.grade = grade;
		this.size = size;
		this.detail = detail;
		this.tagId = tagId;
		this.mbrId = mbrId;
		this.price = price;
		this.itemStatus = itemStatus;
		this.quantity = quantity;
	}

	
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", grade=" + grade + ", size=" + size + ", detail="
				+ detail + ", tagId=" + tagId + ", mbrId=" + mbrId + ", price=" + price + ", itemStatus=" + itemStatus
				+ ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(detail, grade, itemId, itemName, itemStatus, mbrId, price, quantity, size, tagId);
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
		return Objects.equals(detail, other.detail) && Objects.equals(grade, other.grade)
				&& Objects.equals(itemId, other.itemId) && Objects.equals(itemName, other.itemName)
				&& Objects.equals(itemStatus, other.itemStatus) && Objects.equals(mbrId, other.mbrId)
				&& Objects.equals(price, other.price) && Objects.equals(quantity, other.quantity)
				&& Objects.equals(size, other.size) && Objects.equals(tagId, other.tagId);
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

	public void setItemName(String name) {
		this.itemName = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
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

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
