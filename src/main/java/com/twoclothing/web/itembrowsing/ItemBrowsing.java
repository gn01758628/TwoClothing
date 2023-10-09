package com.twoclothing.web.itembrowsing;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class ItemBrowsing implements Serializable {
	private Integer itemId;
	private Integer mbrId;
	private Timestamp browsingTime;
	
	public ItemBrowsing() {
	}
	
	public ItemBrowsing(Integer itemId, Integer mbrId, Timestamp browsingTime) {
		this.itemId = itemId;
		this.mbrId = mbrId;
		this.browsingTime = browsingTime;
	}
	
	@Override
	public String toString() {
		return "ItemBrowsing [itemId=" + itemId + ", mbrId=" + mbrId + ", browsingTime=" + browsingTime + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(browsingTime, itemId, mbrId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemBrowsing other = (ItemBrowsing) obj;
		return Objects.equals(browsingTime, other.browsingTime) && Objects.equals(itemId, other.itemId)
				&& Objects.equals(mbrId, other.mbrId);
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
	
	public Timestamp getBrowsingTime() {
		return browsingTime;
	}
	
	public void setBrowsingTime(Timestamp browsingTime) {
		this.browsingTime = browsingTime;
	}
}
