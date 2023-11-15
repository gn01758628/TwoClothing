package com.twoclothing.model.dto;
import java.io.Serializable;
import java.sql.Timestamp;

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

    
    // 省略 Getter 和 Setter...

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
