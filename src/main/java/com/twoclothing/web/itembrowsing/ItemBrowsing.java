package com.twoclothing.web.itembrowsing;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.twoclothing.web.itembrowsing.ItemBrowsing.CompositeDetail;



@Entity
@Table(name="itembrowsing")
@IdClass(CompositeDetail.class)
public class ItemBrowsing implements Serializable {
	@Id
	@Column(name="itemid")
	private Integer itemId;
	@Id
	@Column(name="mbrid")
	private Integer mbrId;
	
	@Column(name="browsingtime")
	private Timestamp browsingTime;
	
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
	
	public Timestamp getBrowsingTime() {
		return browsingTime;
	}
	
	public void setBrowsingTime(Timestamp browsingTime) {
		this.browsingTime = browsingTime;
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
