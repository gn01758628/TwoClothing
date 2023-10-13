package com.twoclothing.web.blacklist;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "blacklist")
public class BlackList implements Serializable {
	@EmbeddedId
	private CompositeDetail compositeKey;

	public BlackList() {
	}

	public BlackList(CompositeDetail compositeKey) {
		this.compositeKey = compositeKey;
	}

	@Override
	public String toString() {
		return "BlackList{" + "compositeKey=" + compositeKey + '}';
	}

	public CompositeDetail getCompositeKey() {
		return compositeKey;
	}

	public void setCompositeKey(CompositeDetail compositeKey) {
		this.compositeKey = compositeKey;
	}

	@Embeddable
	public static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;
		
		@Column(name = "mbrid")
		private Integer mbrId;

		@Column(name = "blackid")
		private Integer blackId;

		public CompositeDetail() {
		}

		public CompositeDetail(Integer mbrId, Integer blackId) {
			this.mbrId = mbrId;
			this.blackId = blackId;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			CompositeDetail that = (CompositeDetail) o;
			return Objects.equals(mbrId, that.mbrId) && Objects.equals(blackId, that.blackId);
		}

		@Override
		public int hashCode() {
			return Objects.hash(mbrId, blackId);
		}

		public Integer getMbrId() {
			return mbrId;
		}

		public void setMbrId(Integer mbrId) {
			this.mbrId = mbrId;
		}

		public Integer getBlackId() {
			return blackId;
		}

		public void setBlackId(Integer blackId) {
			this.blackId = blackId;
		}
	}
}