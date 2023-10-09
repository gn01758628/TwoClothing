package com.twoclothing.web.biditembrowsing;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class BidItemBrowsing implements Serializable {
	private  Integer mbrId;
	private  Integer bidItemId;
	private  Timestamp browsingTime;
	
	public BidItemBrowsing() {
		
	}

	public BidItemBrowsing(Integer mbrId, Integer bidItemId, Timestamp browsingTime) {
		super();
		this.mbrId = mbrId;
		this.bidItemId = bidItemId;
		this.browsingTime = browsingTime;
	}

	@Override
	public String toString() {
		return "BidItemBrowsing [mbrId=" + mbrId + ", bidItemId=" + bidItemId + ", browsingTime=" + browsingTime + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bidItemId, browsingTime, mbrId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BidItemBrowsing other = (BidItemBrowsing) obj;
		return Objects.equals(bidItemId, other.bidItemId) && Objects.equals(browsingTime, other.browsingTime)
				&& Objects.equals(mbrId, other.mbrId);
	}

	public Integer getMbrId() {
		return mbrId;
	}

	public void setMbrId(Integer mbrId) {
		this.mbrId = mbrId;
	}

	public Integer getBidItemId() {
		return bidItemId;
	}

	public void setBidItemId(Integer bidItemId) {
		this.bidItemId = bidItemId;
	}

	public Timestamp getBrowsingTime() {
		return browsingTime;
	}

	public void setBrowsingTime(Timestamp browsingTime) {
		this.browsingTime = browsingTime;
	}
	
	
	
}
