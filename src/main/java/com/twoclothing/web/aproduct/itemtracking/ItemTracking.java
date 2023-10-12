package com.twoclothing.web.aproduct.itemtracking;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Objects;

public class ItemTracking implements Serializable {
	private Integer itemId;
	private Integer mbrId;
	private Timestamp trackingTime;
	
	public ItemTracking() {
	}

	public ItemTracking(Integer itemId, Integer mbrId, Timestamp trackingTime) {
		this.itemId = itemId;
		this.mbrId = mbrId;
		this.trackingTime = trackingTime;
	}

	@Override
	public String toString() {
		return "ItemTracking [itemId=" + itemId + ", mbrId=" + mbrId + ", trackingTime=" + trackingTime + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemId, mbrId, trackingTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemTracking other = (ItemTracking) obj;
		return Objects.equals(itemId, other.itemId) && Objects.equals(mbrId, other.mbrId)
				&& Objects.equals(trackingTime, other.trackingTime);
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getMbrId() {
		return mbrId;
	}

	public void setMbrId(Integer mbrId) {
		this.mbrId = mbrId;
	}

	public Timestamp getTrackingTime() {
		return trackingTime;
	}

	public void setTrackingTime(Timestamp trackingTime) {
		this.trackingTime = trackingTime;
	}
}
