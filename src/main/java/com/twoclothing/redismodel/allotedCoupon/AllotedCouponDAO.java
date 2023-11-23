package com.twoclothing.redismodel.allotedCoupon;

import java.util.List;
import java.util.Map;

import com.twoclothing.model.coupon.Coupon;

public interface AllotedCouponDAO {
	
	void allot(AllotedCoupon allotedCoupon);

	public List<AllotedCoupon> getAll();

	void receiveCoupon(AllotedCoupon allotedCoupon);

	void delete(AllotedCoupon allotedCoupon);
	
}
