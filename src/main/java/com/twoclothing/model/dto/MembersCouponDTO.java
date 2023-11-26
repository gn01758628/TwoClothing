package com.twoclothing.model.dto;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import com.twoclothing.model.coupon.Coupon;
import com.twoclothing.model.memberscoupon.MembersCoupon;

public class MembersCouponDTO implements Serializable {

    private Integer memberId;
    private Integer couponId;
    private Timestamp useDate;
    private int couponStatus;
    private String cpnName;
    private Timestamp createDate;
    private Timestamp expireDate;
    private int disType;
    private int disValue;
    private int minAmount;
    

    public MembersCouponDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public MembersCouponDTO(MembersCoupon membersCoupon, Coupon coupon) {
        this.memberId = membersCoupon.getCompositeKey().getMemberId();
        this.couponId = membersCoupon.getCompositeKey().getCouponId();
        this.useDate = membersCoupon.getUseDate();
        this.couponStatus = membersCoupon.getCouponStatus();
        this.cpnName = coupon.getCpnName();
        this.createDate = coupon.getCreateDate();
        this.expireDate = coupon.getExpireDate();
        this.disType = coupon.getDisType();
        this.disValue = coupon.getDisValue();
        this.minAmount = coupon.getMinAmount();
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




	public String getCpnName() {
		return cpnName;
	}




	public void setCpnName(String cpnName) {
		this.cpnName = cpnName;
	}




	public Timestamp getCreateDate() {
		return createDate;
	}




	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}




	public Timestamp getExpireDate() {
		return expireDate;
	}




	public void setExpireDate(Timestamp expireDate) {
		this.expireDate = expireDate;
	}




	public int getDisType() {
		return disType;
	}




	public void setDisType(int disType) {
		this.disType = disType;
	}




	public int getDisValue() {
		return disValue;
	}




	public void setDisValue(int disValue) {
		this.disValue = disValue;
	}




	public int getMinAmount() {
		return minAmount;
	}




	public void setMinAmount(int minAmount) {
		this.minAmount = minAmount;
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
		MembersCouponDTO other = (MembersCouponDTO) obj;
		return Objects.equals(couponId, other.couponId) && Objects.equals(memberId, other.memberId);
	}




	@Override
    public String toString() {
        return "MembersCouponDTO{" +
                "memberId=" + memberId +
                ", couponId=" + couponId +
                ", useDate=" + useDate +
                ", couponStatus=" + couponStatus +
                ", cpnName='" + cpnName + '\'' +
                ", createDate=" + createDate +
                ", expireDate=" + expireDate +
                ", disType=" + disType +
                ", disValue=" + disValue +
                ", minAmount=" + minAmount +
                '}';
    }

	
}
