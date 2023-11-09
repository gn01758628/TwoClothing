package com.twoclothing.redismodel.itembrowsing;

import java.io.Serializable;
import java.util.Objects;

public class ItemBrowsing implements Serializable {
	private Integer mbrId;

	private Integer itemId;

	private long browsingTime;

	public ItemBrowsing() {
	}

	public ItemBrowsing(Integer mbrId, Integer itemId, long browsingTime) {
		this.mbrId = mbrId;
		this.itemId = itemId;
		this.browsingTime = browsingTime;
	}

	@Override
	public String toString() {
		return "ItemBrowsing [mbrId=" + mbrId + ", itemId=" + itemId + ", browsingTime=" + browsingTime + "]";
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
		return browsingTime == other.browsingTime && Objects.equals(itemId, other.itemId)
				&& Objects.equals(mbrId, other.mbrId);
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

	public long getBrowsingTime() {
		return browsingTime;
	}

	public void setBrowsingTime(long browsingTime) {
		this.browsingTime = browsingTime;
	}
}
