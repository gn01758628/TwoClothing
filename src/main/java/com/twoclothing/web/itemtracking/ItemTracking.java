package com.twoclothing.web.itemtracking;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.twoclothing.web.itemtracking.ItemTracking.CompositeDetail;


@Entity
@Table(name="itemtracking")
@IdClass(CompositeDetail.class)
public class ItemTracking implements Serializable {
	@Id
	@Column(name="itemid")
	private Integer itemId;
	@Id
	@Column(name="mbrid")
	private Integer mbrId;
	
	@Column(name="trackingtime")
	private Timestamp trackingTime;
	
	public CompositeDetail getCompositeKey() {
		return new CompositeDetail(itemId, mbrId);
	}

	public void setCompositeKey(CompositeDetail key) {
		this.itemId = key.getItemId();
		this.mbrId = key.getMbrId();
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

	public Timestamp getTrackingTime() {
		return trackingTime;
	}

	public void setTrackingTime(Timestamp trackingTime) {
		this.trackingTime = trackingTime;
	}
	
	static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer itemId;
		private Integer mbrId;
		
		// 一定要有無參數建構子
		public CompositeDetail() {
			super();
		}

		public CompositeDetail(Integer itemId, Integer mbrId) {
			super();
			this.itemId = itemId;
			this.mbrId = mbrId;
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
		
	}
}
