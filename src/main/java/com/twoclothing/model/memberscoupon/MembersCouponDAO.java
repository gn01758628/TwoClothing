package com.twoclothing.model.memberscoupon;
import java.util.List;

public interface MembersCouponDAO {
    void addMembersCoupon(MembersCoupon membersCoupon);
    void updateMembersCoupon(MembersCoupon membersCoupon);
    void deleteMembersCoupon(Integer memberId, Integer couponId);
    MembersCoupon getMembersCouponByIds(Integer memberId, Integer couponId);
    List<MembersCoupon> getAllMembersCoupons();
    List<MembersCoupon> getMembersCouponsByMemberId(Integer memberId);
    List<MembersCoupon> getMembersCouponsByCouponId(Integer couponId);
    List<MembersCoupon> getMembersCouponsByStatus(int couponStatus);
}
