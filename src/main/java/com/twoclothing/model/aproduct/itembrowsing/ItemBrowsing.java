package com.twoclothing.model.aproduct.itembrowsing;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "itembrowsing")
public class ItemBrowsing implements Serializable {
	@EmbeddedId
	private CompositeDetail compositeKey;

	@Column(name = "browsingtime", nullable = false)
	private Timestamp browsingTime;

	public ItemBrowsing() {
	}

	public ItemBrowsing(CompositeDetail compositeKey, Timestamp browsingTime) {
		this.compositeKey = compositeKey;
		this.browsingTime = browsingTime;
	}

	@Override
	public String toString() {
		return "ItemBrowsing [compositeKey=" + compositeKey + ", browsingTime=" + browsingTime + "]";
	}

	public CompositeDetail getCompositeKey() {
		return compositeKey;
	}

	public void setCompositeKey(CompositeDetail compositeKey) {
		this.compositeKey = compositeKey;
	}

	public Timestamp getBrowsingTime() {
		return browsingTime;
	}

	public void setBrowsingTime(Timestamp browsingTime) {
		this.browsingTime = browsingTime;
	}

	@Embeddable
	static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;

		@Column(name = "itemid")
		private Integer itemId;

		@Column(name = "mbrid")
		private Integer mbrId;

		public CompositeDetail() {
		}

		public CompositeDetail(Integer itemId, Integer mbrId) {
			this.itemId = itemId;
			this.mbrId = mbrId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(itemId, mbrId);
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
			return Objects.equals(itemId, other.itemId) && Objects.equals(mbrId, other.mbrId);
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