package com.twoclothing.web.biditem;

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
@Table(name= "biditem")
public class BidItem implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "biditemid", updatable = false)
	private Integer bidItemId;
	
	@Column(name = "bidname")
	private String bidName;
	
	@Column(name = "detail")
	private String detail;
	
	@Column(name = "tagid")
	private Integer tagId;
	
	@Column(name = "mbrid")
	private Integer mbrId;
	
	@Column(name = "starprice")
	private Integer starPrice;
	
	@Column(name = "reserveprice")
	private Integer reservePrice;
	
	@Column(name = "directprice")
	private Integer directPrice;
	
	@Column(name = "bidstatus")
	private Integer bidStatus;
	
	@Column(name = "starttime")
	private Timestamp startTime;
	
	@Column(name = "endtime")
	private Timestamp endTime;
	
	@Column(name = "empid")
	private Integer empId;
	
	public BidItem() {
		
	}
	
	public BidItem(Integer bidItemId, String bidName, String detail, Integer tagId, Integer mbrId, Integer starPrice,
			Integer reservePrice, Integer directPrice, Integer bidStatus, Timestamp startTime, Timestamp endTime,
			Integer empId) {
		super();
		this.bidItemId = bidItemId;
		this.bidName = bidName;
		this.detail = detail;
		this.tagId = tagId;
		this.mbrId = mbrId;
		this.starPrice = starPrice;
		this.reservePrice = reservePrice;
		this.directPrice = directPrice;
		this.bidStatus = bidStatus;
		this.startTime = startTime;
		this.endTime = endTime;
		this.empId = empId;
	}



	@Override
	public String toString() {
		return "BidItem [bidItemId=" + bidItemId + ", bidName=" + bidName + ", detail=" + detail + ", tagId=" + tagId
				+ ", mbrId=" + mbrId + ", starPrice=" + starPrice + ", reservePrice=" + reservePrice + ", directPrice="
				+ directPrice + ", bidStatus=" + bidStatus + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", empId=" + empId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bidItemId, bidName, bidStatus, detail, directPrice, empId, endTime, mbrId, reservePrice,
				starPrice, startTime, tagId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BidItem other = (BidItem) obj;
		return Objects.equals(bidItemId, other.bidItemId) && Objects.equals(bidName, other.bidName)
				&& Objects.equals(bidStatus, other.bidStatus) && Objects.equals(detail, other.detail)
				&& Objects.equals(directPrice, other.directPrice) && Objects.equals(empId, other.empId)
				&& Objects.equals(endTime, other.endTime) && Objects.equals(mbrId, other.mbrId)
				&& Objects.equals(reservePrice, other.reservePrice) && Objects.equals(starPrice, other.starPrice)
				&& Objects.equals(startTime, other.startTime) && Objects.equals(tagId, other.tagId);
	}

	public Integer getBidItemId() {
		return bidItemId;
	}

	public void setBidItemId(Integer bidItemId) {
		this.bidItemId = bidItemId;
	}

	public String getBidName() {
		return bidName;
	}

	public void setBidName(String bidName) {
		this.bidName = bidName;
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

	public Integer getStarPrice() {
		return starPrice;
	}

	public void setStarPrice(Integer starPrice) {
		this.starPrice = starPrice;
	}

	public Integer getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(Integer reservePrice) {
		this.reservePrice = reservePrice;
	}

	public Integer getDirectPrice() {
		return directPrice;
	}

	public void setDirectPrice(Integer directPrice) {
		this.directPrice = directPrice;
	}

	public Integer getBidStatus() {
		return bidStatus;
	}

	public void setBidStatus(Integer bidStatus) {
		this.bidStatus = bidStatus;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	
	
}
