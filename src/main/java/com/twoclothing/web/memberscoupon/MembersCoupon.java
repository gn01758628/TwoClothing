package com.twoclothing.web.memberscoupon;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class MembersCoupon implements Serializable {

    private Integer memberId;
    private Integer couponId;
    private Timestamp useDate;
    private int couponStatus;
	public MembersCoupon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MembersCoupon(Integer memberId, Integer couponId, Timestamp useDate, int couponStatus) {
		super();
		this.memberId = memberId;
		this.couponId = couponId;
		this.useDate = useDate;
		this.couponStatus = couponStatus;
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
		return Objects.hash(couponId);
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
		return Objects.equals(couponId, other.couponId);
	}
	@Override
	public String toString() {
		return "MembersCoupon [memberId=" + memberId + ", couponId=" + couponId + ", useDate=" + useDate
				+ ", couponStatus=" + couponStatus + "]";
	}

    
}
