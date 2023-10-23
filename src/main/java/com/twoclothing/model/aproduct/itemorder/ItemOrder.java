package com.twoclothing.model.aproduct.itemorder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="itemorder")
public class ItemOrder implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="orderid", updatable = false)
	private Integer orderId;
	
	@Column(name="buymbrid",updatable = false)
	private Integer buyMbrId;
	
	@Column(name="sellmbrid",updatable = false)
	private Integer sellMbrId;
	
	@Column(name="buystar")
	private Integer buyStar;
	
	@Column(name="buyerratingdesc")
	private String buyerRatingDesc;
	
	@Column(name="sellstar")
	private Integer sellStar;
	
	@Column(name="sellerratingdesc")
	private String sellerRatingDesc;
	
	@Column(name="orderdate",updatable = false )
	private Timestamp orderDate;
	
	@Column(name="paytype", columnDefinition = "TINYINT")
	private Integer payType;
	
	@Column(name="payinfo")
	private String payInfo;
	
	@Column(name="amount")
	private Integer amount;
	
	@Column(name="pointdiscount")
	private Integer pointDiscount;
	
	@Column(name="finalamount")
	private Integer finalAmount;
	
	@Column(name="orderstatus", columnDefinition = "TINYINT")
	private Integer orderStatus;
	
	@Column(name="receiveaddress")
	private String receiveAddress;
	
	@Column(name="receivename")
	private String receiveName;
	
	@Column(name="receivephone")
	private String receivePhone;
	
	@Column(name="remarks")
	private String remarks;

	public ItemOrder() {
	}

	public ItemOrder(Integer orderId, Integer buyMbrId, Integer sellMbrId, Integer buyStar, String buyerRatingDesc, Integer sellStar, String sellerRatingDesc, Timestamp orderDate, Integer payType, String payInfo, Integer amount, Integer pointDiscount, Integer finalAmount, Integer orderStatus, String receiveAddress, String receiveName, String receivePhone, String remarks) {
		this.orderId = orderId;
		this.buyMbrId = buyMbrId;
		this.sellMbrId = sellMbrId;
		this.buyStar = buyStar;
		this.buyerRatingDesc = buyerRatingDesc;
		this.sellStar = sellStar;
		this.sellerRatingDesc = sellerRatingDesc;
		this.orderDate = orderDate;
		this.payType = payType;
		this.payInfo = payInfo;
		this.amount = amount;
		this.pointDiscount = pointDiscount;
		this.finalAmount = finalAmount;
		this.orderStatus = orderStatus;
		this.receiveAddress = receiveAddress;
		this.receiveName = receiveName;
		this.receivePhone = receivePhone;
		this.remarks = remarks;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ItemOrder itemOrder = (ItemOrder) o;
		return Objects.equals(orderId, itemOrder.orderId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getBuyMbrId() {
		return buyMbrId;
	}

	public void setBuyMbrId(Integer buyMbrId) {
		this.buyMbrId = buyMbrId;
	}

	public Integer getSellMbrId() {
		return sellMbrId;
	}

	public void setSellMbrId(Integer sellMbrId) {
		this.sellMbrId = sellMbrId;
	}

	public Integer getBuyStar() {
		return buyStar;
	}

	public void setBuyStar(Integer buyStar) {
		this.buyStar = buyStar;
	}

	public String getBuyerRatingDesc() {
		return buyerRatingDesc;
	}

	public void setBuyerRatingDesc(String buyerRatingDesc) {
		this.buyerRatingDesc = buyerRatingDesc;
	}

	public Integer getSellStar() {
		return sellStar;
	}

	public void setSellStar(Integer sellStar) {
		this.sellStar = sellStar;
	}

	public String getSellerRatingDesc() {
		return sellerRatingDesc;
	}

	public void setSellerRatingDesc(String sellerRatingDesc) {
		this.sellerRatingDesc = sellerRatingDesc;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getPointDiscount() {
		return pointDiscount;
	}

	public void setPointDiscount(Integer pointDiscount) {
		this.pointDiscount = pointDiscount;
	}

	public Integer getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(Integer finalAmount) {
		this.finalAmount = finalAmount;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getReceivePhone() {
		return receivePhone;
	}

	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
