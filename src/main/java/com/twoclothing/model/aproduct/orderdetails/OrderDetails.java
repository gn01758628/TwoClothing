package com.twoclothing.model.aproduct.orderdetails;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "orderdetails")
public class OrderDetails implements Serializable {
	@EmbeddedId
	private OrderDetailsCompositeDetail compositeKey;
	@Column(name = "quantity")
	private Integer quantity;
	@Column(name = "price")
	private Integer price;
	@Column(name = "discountprice")
	private Integer discountPrice;
	@Column(name = "buyingprice")
	private Integer buyingPrice;

	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetails(OrderDetailsCompositeDetail compositeKey, Integer quantity, Integer price,
			Integer discountPrice, Integer buyingPrice) {
		super();
		this.compositeKey = compositeKey;
		this.quantity = quantity;
		this.price = price;
		this.discountPrice = discountPrice;
		this.buyingPrice = buyingPrice;
	}

	public OrderDetailsCompositeDetail getCompositeKey() {
		return compositeKey;
	}

	public void setCompositeKey(OrderDetailsCompositeDetail compositeKey) {
		this.compositeKey = compositeKey;
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

	@Override
	public int hashCode() {
		return Objects.hash(compositeKey);
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
		return Objects.equals(compositeKey, other.compositeKey);
	}

	@Override
	public String toString() {
		return "OrderDetails [compositeKey=" + compositeKey + ", quantity=" + quantity + ", price=" + price
				+ ", discountPrice=" + discountPrice + ", buyingPrice=" + buyingPrice + "]";
	}

	// 複合主鍵
	@Embeddable
	public static class OrderDetailsCompositeDetail implements Serializable {

		@Column(name = "orderid", nullable = false)
		private Integer orderId;

		@Column(name = "itemid", nullable = false)
		private Integer itemId;

		public OrderDetailsCompositeDetail() {
			super();
			// TODO Auto-generated constructor stub
		}

		public OrderDetailsCompositeDetail(Integer orderId, Integer itemId) {
			super();
			this.orderId = orderId;
			this.itemId = itemId;
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

		@Override
		public int hashCode() {
			return Objects.hash(itemId, orderId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			OrderDetailsCompositeDetail other = (OrderDetailsCompositeDetail) obj;
			return Objects.equals(itemId, other.itemId) && Objects.equals(orderId, other.orderId);
		}

		@Override
		public String toString() {
			return "OrderDetailsCompositeDetail [orderId=" + orderId + ", itemId=" + itemId + "]";
		}

	}
}
