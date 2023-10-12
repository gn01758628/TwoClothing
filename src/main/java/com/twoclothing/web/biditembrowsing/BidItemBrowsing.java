package com.twoclothing.web.biditembrowsing;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.twoclothing.web.biditembrowsing.BidItemBrowsing.CompositeDetail;


@Entity
@Table(name="biditembrowsing")
@IdClass(CompositeDetail.class)
public class BidItemBrowsing implements Serializable {
	
	@Id
	@Column(name = "mbrid")
	private  Integer mbrId;
	
	@Id
	@Column(name = "biditemid")
	private  Integer bidItemId;
	
	@Column(name = "browsingtime")
	private  Timestamp browsingTime;
	
	
	public BidItemBrowsing() {
		
	}

	// 特別加上對複合主鍵物件的 getter / setter
		public CompositeDetail getCompositeKey() {
			return new CompositeDetail(mbrId, bidItemId);
		}
		
		public void setCompositeKey(CompositeDetail key) {
			this.mbrId = key.getMbrId();
			this.bidItemId = key.getBidItemId();
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
	
	// 需要宣告一個有包含複合主鍵屬性的類別，並一定要實作 java.io.Serializable 介面
	static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;

		private  Integer mbrId;
		private  Integer bidItemId;
		
		// 一定要有無參數建構子
		public CompositeDetail() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public CompositeDetail(Integer mbrId, Integer bidItemId) {
			super();
			this.mbrId = mbrId;
			this.bidItemId = bidItemId;
		}

		@Override
		public String toString() {
			return "CompositeDetail [mbrId=" + mbrId + ", bidItemId=" + bidItemId + "]";
		}
		// 一定要 override 此類別的 hashCode() 與 equals() 方法！
		@Override
		public int hashCode() {
			return Objects.hash(bidItemId, mbrId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CompositeDetail other = (CompositeDetail) obj;
			return Objects.equals(bidItemId, other.bidItemId) && Objects.equals(mbrId, other.mbrId);
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
		
		
		
	}

	
	
	
}

	