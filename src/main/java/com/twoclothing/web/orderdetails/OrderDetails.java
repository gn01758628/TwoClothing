package com.twoclothing.web.orderdetails;

import java.io.Serializable;
import java.util.Objects;

public class OrderDetails implements Serializable {
	private Integer orderId;
	private Integer itemId;
	private Integer quantity;
	private Integer price;
	private Integer discountPrice;
	private Integer buyingPrice;

	public OrderDetails() {
	}

	public OrderDetails(Integer orderId, Integer itemId, Integer quantity, Integer price, Integer discountPrice,
			Integer buyingPrice) {
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.price = price;
		this.discountPrice = discountPrice;
		this.buyingPrice = buyingPrice;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", itemId=" + itemId + ", quantity=" + quantity + ", price=" + price
				+ ", discountPrice=" + discountPrice + ", buyingPrice=" + buyingPrice + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(buyingPrice, discountPrice, itemId, orderId, price, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetails other = (OrderDetails) obj;
		return Objects.equals(buyingPrice, other.buyingPrice) && Objects.equals(discountPrice, other.discountPrice)
				&& Objects.equals(itemId, other.itemId) && Objects.equals(orderId, other.orderId)
				&& Objects.equals(price, other.price) && Objects.equals(quantity, other.quantity);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Integer getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(Integer buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
}
