package com.twoclothing.redismodel.allotedCoupon;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;

import com.twoclothing.model.coupon.Coupon;

public class AllotedCoupon {
    private Integer cpnId;
    private String cpnName;
    private Long createDate;
    private Long expireDate;
    private Integer disType;
    private Integer disValue;
    private Integer minAmount;//0折抵金額 1折%數
    
    
    private Integer index;
    private Long allotDate;
    private Integer totalQuantity;
    private Integer remainingQuantity;
    private Integer status; //-1使用期限已到 0尚未發放 1發放中 2停止發放 3發放完了
    
	

    
    
    
	public AllotedCoupon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public AllotedCoupon(Coupon coupon,Long allotDate, Integer totalQuantity,
			Integer remainingQuantity) {
		super();
		this.cpnId = coupon.getCpnId();
		this.cpnName = coupon.getCpnName();
		this.createDate = (coupon.getCreateDate() != null) ? coupon.getCreateDate().getTime() : null;
		this.expireDate = (coupon.getExpireDate() != null) ? coupon.getExpireDate().getTime() : null;
		this.disType = coupon.getDisType();
		this.disValue = coupon.getDisValue();
		this.minAmount = coupon.getMinAmount();
		
		this.allotDate = allotDate;
		this.totalQuantity = totalQuantity;
		this.remainingQuantity = remainingQuantity;
		this.status = 0;
	}



	public Integer getCpnId() {
		return cpnId;
	}



	public void setCpnId(Integer cpnId) {
		this.cpnId = cpnId;
	}



	public String getCpnName() {
		return cpnName;
	}



	public void setCpnName(String cpnName) {
		this.cpnName = cpnName;
	}



	public Long getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate.getTime();
	}



	public Long getExpireDate() {
		return expireDate;
	}



	public void setExpireDate(Timestamp expireDate) {
		this.expireDate = expireDate.getTime();
	}



	public Integer getDisType() {
		return disType;
	}



	public void setDisType(Integer disType) {
		this.disType = disType;
	}



	public Integer getDisValue() {
		return disValue;
	}



	public void setDisValue(Integer disValue) {
		this.disValue = disValue;
	}



	public Integer getMinAmount() {
		return minAmount;
	}



	public void setMinAmount(Integer minAmount) {
		this.minAmount = minAmount;
	}



	public Integer getIndex() {
		return index;
	}



	public void setIndex(Integer index) {
		this.index = index;
	}



	public Long getAllotDate() {
		return allotDate;
	}



	public void setAllotDate(Timestamp allotDate) {
		this.allotDate = allotDate.getTime();
	}



	public Integer getTotalQuantity() {
		return totalQuantity;
	}



	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}



	public Integer getRemainingQuantity() {
		return remainingQuantity;
	}



	public void setRemainingQuantity(Integer remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "AllotedCoupon [cpnId=" + cpnId + ", cpnName=" + cpnName + ", createDate=" + createDate + ", expireDate="
				+ expireDate + ", disType=" + disType + ", disValue=" + disValue + ", minAmount=" + minAmount
				+ ", index=" + index + ", allotDate=" + allotDate + ", totalQuantity=" + totalQuantity
				+ ", remainingQuantity=" + remainingQuantity + ", status=" + status + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(cpnId, index);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AllotedCoupon other = (AllotedCoupon) obj;
		return Objects.equals(cpnId, other.cpnId) && Objects.equals(index, other.index);
	}
	
	
}