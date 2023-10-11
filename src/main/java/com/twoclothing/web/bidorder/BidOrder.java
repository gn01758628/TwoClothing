package com.twoclothing.web.bidorder;

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
@Table(name = "bidorder")
public class BidOrder  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bidorderid", updatable = false)
	private Integer bidOrderId;
	
	@Column(name = "bidItemId", updatable = false)
	private Integer bidItemId;
	
	@Column(name = "buymbrid", updatable = false)
	private Integer buyMbrId;
	
	@Column(name = "biditemid", updatable = false)
	private Integer sellMbrId;
	
	@Column(name = "buystar")
	private Integer buyStar;
	
	@Column(name = "buyerratingdesc")
	private String buyerRatingDesc;
	
	@Column(name = "sellstar")
	private Integer sellStar;
	
	@Column(name = "sellerratingdesc")
	private String sellerRatingDesc;
	
	@Column(name = "orderdate", updatable = false)
	private Timestamp orderDate;
	
	@Column(name = "paytype")
	private Integer payType;
	
	@Column(name = "payinfo")
	private String payInfo;
	
	@Column(name = "amount", updatable = false)
	private Integer amount;
	
	@Column(name = "orderstatus")
	private Integer orderStatus;
	
	@Column(name = "receiveaddress")
	private String receiveAddress;
	
	@Column(name = "receivename")
	private String receiveName;
	
	@Column(name = "receivephone")
	private String receivePhone;
	
	@Column(name = "remarks")
	private String remarks;

	public BidOrder() {

	}

	public BidOrder(Integer bidOrderId, Integer bidItemId, Integer buyMbrId, Integer sellMbrId, Integer buyStar, String buyerRatingDesc, Integer sellStar, String sellerRatingDesc, Timestamp orderDate, Integer payType, String payInfo, Integer amount, Integer orderStatus, String receiveAddress, String receiveName, String receivePhone, String remarks) {
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
		return "BidOrder{" +
				"bidOrderId=" + bidOrderId +
				", bidItemId=" + bidItemId +
				", buyMbrId=" + buyMbrId +
				", sellMbrId=" + sellMbrId +
				", buyStar=" + buyStar +
				", buyerRatingDesc='" + buyerRatingDesc + '\'' +
				", sellStar=" + sellStar +
				", sellerRatingDesc='" + sellerRatingDesc + '\'' +
				", orderDate=" + orderDate +
				", payType=" + payType +
				", payInfo='" + payInfo + '\'' +
				", amount=" + amount +
				", orderStatus=" + orderStatus +
				", receiveAddress='" + receiveAddress + '\'' +
				", receiveName='" + receiveName + '\'' +
				", receivePhone='" + receivePhone + '\'' +
				", remarks='" + remarks + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BidOrder bidOrder = (BidOrder) o;
		return Objects.equals(bidOrderId, bidOrder.bidOrderId) && Objects.equals(bidItemId, bidOrder.bidItemId) && Objects.equals(buyMbrId, bidOrder.buyMbrId) && Objects.equals(sellMbrId, bidOrder.sellMbrId) && Objects.equals(buyStar, bidOrder.buyStar) && Objects.equals(buyerRatingDesc, bidOrder.buyerRatingDesc) && Objects.equals(sellStar, bidOrder.sellStar) && Objects.equals(sellerRatingDesc, bidOrder.sellerRatingDesc) && Objects.equals(orderDate, bidOrder.orderDate) && Objects.equals(payType, bidOrder.payType) && Objects.equals(payInfo, bidOrder.payInfo) && Objects.equals(amount, bidOrder.amount) && Objects.equals(orderStatus, bidOrder.orderStatus) && Objects.equals(receiveAddress, bidOrder.receiveAddress) && Objects.equals(receiveName, bidOrder.receiveName) && Objects.equals(receivePhone, bidOrder.receivePhone) && Objects.equals(remarks, bidOrder.remarks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bidOrderId, bidItemId, buyMbrId, sellMbrId, buyStar, buyerRatingDesc, sellStar, sellerRatingDesc, orderDate, payType, payInfo, amount, orderStatus, receiveAddress, receiveName, receivePhone, remarks);
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
