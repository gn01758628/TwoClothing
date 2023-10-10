package com.twoclothing.web.bidorder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class BidOrder implements Serializable {
	private Integer bidOrderId;
	private Integer bidItemId;
	private Integer buyMbrId;
	private Integer sellMbrId;
	private Integer buyStar;
	private String buyerRatingDesc;
	private Integer sellStar;
	private String sellerRatingDesc;
	private Timestamp orderDate;
	private Integer payType;
	private String payInfo;
	private Integer amount;
	private Integer orderStatus;
	private String receiveAddress;
	private String receiveName;
	private String receivePhone;
	private String remarks;
	

	public BidOrder() {

	}


	public BidOrder(Integer bidOrderId, Integer bidItemId, Integer buyMbrId, Integer sellMbrId, Integer buyStar,
			String buyerRatingDesc, Integer sellStar, String sellerRatingDesc, Timestamp orderDate, Integer payType,
			String payInfo, Integer amount, Integer orderStatus, String receiveAddress, String receiveName,
			String receivePhone, String remarks) {
		super();
		this.bidOrderId = bidOrderId;
		this.bidItemId = bidItemId;
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
		this.orderStatus = orderStatus;
		this.receiveAddress = receiveAddress;
		this.receiveName = receiveName;
		this.receivePhone = receivePhone;
		this.remarks = remarks;
	}


	@Override
	public String toString() {
		return "BidOrder [bidOrderId=" + bidOrderId + ", bidItemId=" + bidItemId + ", buyMbrId=" + buyMbrId
				+ ", sellMbrId=" + sellMbrId + ", buyStar=" + buyStar + ", buyerRatingDesc=" + buyerRatingDesc
				+ ", sellStar=" + sellStar + ", sellerRatingDesc=" + sellerRatingDesc + ", orderDate=" + orderDate
				+ ", payType=" + payType + ", payInfo=" + payInfo + ", amount=" + amount + ", orderStatus="
				+ orderStatus + ", receiveAddress=" + receiveAddress + ", receiveName=" + receiveName
				+ ", receivePhone=" + receivePhone + ", remarks=" + remarks + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(amount, bidItemId, bidOrderId, buyMbrId, buyStar, buyerRatingDesc, orderDate, orderStatus,
				payInfo, payType, receiveAddress, receiveName, receivePhone, remarks, sellMbrId, sellStar,
				sellerRatingDesc);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		BidOrder other = (BidOrder) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(bidItemId, other.bidItemId)
				&& Objects.equals(bidOrderId, other.bidOrderId) && Objects.equals(buyMbrId, other.buyMbrId)
				&& Objects.equals(buyStar, other.buyStar) && Objects.equals(buyerRatingDesc, other.buyerRatingDesc)
				&& Objects.equals(orderDate, other.orderDate) && Objects.equals(orderStatus, other.orderStatus)
				&& Objects.equals(payInfo, other.payInfo) && Objects.equals(payType, other.payType)
				&& Objects.equals(receiveAddress, other.receiveAddress)
				&& Objects.equals(receiveName, other.receiveName) && Objects.equals(receivePhone, other.receivePhone)
				&& Objects.equals(remarks, other.remarks) && Objects.equals(sellMbrId, other.sellMbrId)
				&& Objects.equals(sellStar, other.sellStar) && Objects.equals(sellerRatingDesc, other.sellerRatingDesc);
	}


	public Integer getBidOrderId() {
		return bidOrderId;
	}


	public void setBidOrderId(Integer bidOrderId) {
		this.bidOrderId = bidOrderId;
	}


	public Integer getBidItemId() {
		return bidItemId;
	}


	public void setBidItemId(Integer bidItemId) {
		this.bidItemId = bidItemId;
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
