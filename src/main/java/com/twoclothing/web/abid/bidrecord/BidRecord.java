package com.twoclothing.web.abid.bidrecord;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class BidRecord implements Serializable{
	private  Integer recordId;
	private  Integer bidItemId;
	private  Integer mbrId;
	private  Integer bidPrice;
	private  Timestamp bidDate;
	
	public BidRecord() {
		
	}

	public BidRecord(Integer recordId, Integer bidItemId, Integer mbrId, Integer bidPrice, Timestamp bidDate) {
		super();
		this.recordId = recordId;
		this.bidItemId = bidItemId;
		this.mbrId = mbrId;
		this.bidPrice = bidPrice;
		this.bidDate = bidDate;
	}

	@Override
	public String toString() {
		return "BidRecord [recordId=" + recordId + ", bidItemId=" + bidItemId + ", mbrId=" + mbrId + ", bidPrice="
				+ bidPrice + ", bidDate=" + bidDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bidDate, bidItemId, bidPrice, mbrId, recordId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BidRecord other = (BidRecord) obj;
		return Objects.equals(bidDate, other.bidDate) && Objects.equals(bidItemId, other.bidItemId)
				&& Objects.equals(bidPrice, other.bidPrice) && Objects.equals(mbrId, other.mbrId)
				&& Objects.equals(recordId, other.recordId);
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getBidItemId() {
		return bidItemId;
	}

	public void setBidItemId(Integer bidItemId) {
		this.bidItemId = bidItemId;
	}

	public Integer getMbrId() {
		return mbrId;
	}

	public void setMbrId(Integer mbrId) {
		this.mbrId = mbrId;
	}

	public Integer getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Integer bidPrice) {
		this.bidPrice = bidPrice;
	}

	public Timestamp getBidDate() {
		return bidDate;
	}

	public void setBidDate(Timestamp bidDate) {
		this.bidDate = bidDate;
	}
	
	
	
}
