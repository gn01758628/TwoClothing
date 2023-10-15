package com.twoclothing.model.memberscoupon;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "memberscoupon")
public class MembersCoupon implements Serializable {

	@EmbeddedId
	private MembersCouponCompositeDetail compositeKey;

	@Column(name = "usedate")
	private Timestamp useDate;

	@Column(name = "cpnstatus", columnDefinition = "TINYINT")
	private int couponStatus;

	public MembersCoupon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MembersCoupon(MembersCouponCompositeDetail compositeKey, Timestamp useDate, int couponStatus) {
		super();
		this.compositeKey = compositeKey;
		this.useDate = useDate;
		this.couponStatus = couponStatus;
	}

	public MembersCouponCompositeDetail getCompositeKey() {
		return compositeKey;
	}

	public void setCompositeKey(MembersCouponCompositeDetail compositeKey) {
		this.compositeKey = compositeKey;
	}

	public Timestamp getUseDate() {
		return useDate;
	}

	public void setUseDate(Timestamp useDate) {
		this.useDate = useDate;
	}

	public int getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(int couponStatus) {
		this.couponStatus = couponStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(compositeKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MembersCoupon other = (MembersCoupon) obj;
		return Objects.equals(compositeKey, other.compositeKey);
	}

	@Override
	public String toString() {
		return "MembersCoupon [compositeKey=" + compositeKey + ", useDate=" + useDate + ", couponStatus=" + couponStatus
				+ "]";
	}

	// 複合主鍵
	@Embeddable
	public static class MembersCouponCompositeDetail implements Serializable {

		@Column(name = "mbrid")
		private Integer memberId;

		@Column(name = "cpnid")
		private Integer couponId;

		public MembersCouponCompositeDetail() {
			super();
			// TODO Auto-generated constructor stub
		}

		public MembersCouponCompositeDetail(Integer memberId, Integer couponId) {
			super();
			this.memberId = memberId;
			this.couponId = couponId;
		}

		public Integer getMemberId() {
			return memberId;
		}

		public void setMemberId(Integer memberId) {
			this.memberId = memberId;
		}

		public Integer getCouponId() {
			return couponId;
		}

		public void setCouponId(Integer couponId) {
			this.couponId = couponId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(couponId, memberId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MembersCouponCompositeDetail other = (MembersCouponCompositeDetail) obj;
			return Objects.equals(couponId, other.couponId) && Objects.equals(memberId, other.memberId);
		}

		@Override
		public String toString() {
			return "MembersCouponCompositeDetail [memberId=" + memberId + ", couponId=" + couponId + "]";
		}

	}

}
