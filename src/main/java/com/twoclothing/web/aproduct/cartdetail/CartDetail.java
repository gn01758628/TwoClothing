package com.twoclothing.web.aproduct.cartdetail;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cartdetail")
public class CartDetail implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cartid")
	private Integer cartId;
	
	@Column(name = "mbrid")
	private Integer mbrId;
	
	@Column(name = "itemid")
	private Integer itemId;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	public CartDetail() {
	}

	public CartDetail(Integer cartId, Integer mbrId, Integer itemId, Integer quantity) {
		this.cartId = cartId;
		this.mbrId = mbrId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartDetail [cartId=" + cartId + ", mbrId=" + mbrId + ", itemId=" + itemId + ", quantity=" + quantity
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartId, itemId, mbrId, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartDetail other = (CartDetail) obj;
		return Objects.equals(cartId, other.cartId) && Objects.equals(itemId, other.itemId)
				&& Objects.equals(mbrId, other.mbrId) && Objects.equals(quantity, other.quantity);
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getMbrId() {
		return mbrId;
	}

	public void setMbrId(Integer mbrId) {
		this.mbrId = mbrId;
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
}
