package com.twoclothing.model.memberscoupon;

import java.util.List;

import com.twoclothing.model.dto.MembersCouponDTO;
import com.twoclothing.model.memberscoupon.MembersCoupon.MembersCouponCompositeDetail;

public interface MembersCouponDAO {
//	void insert(MembersCoupon membersCoupon);
//
//	MembersCoupon getByCompositeKey(MembersCouponCompositeDetail compositeKey);
//
//	List<MembersCoupon> getAll();
//
//	List<MembersCoupon> getAllByMemberId(Integer memberId);
//
//	List<MembersCoupon> getAllByCouponId(Integer couponId);
//
//	List<MembersCoupon> getAllByStatus(int couponStatus);
//
//	void update(MembersCoupon membersCoupon);
//
//	void delete(Integer memberId, Integer couponId);
	
	List<MembersCouponDTO> getAllMembersCouponDTOByMemberId(Integer memberId);
}
