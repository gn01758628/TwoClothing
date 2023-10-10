package com.twoclothing.web.coupon;

import java.sql.Timestamp;
import java.util.List;

public interface CouponDAO {
    void addCoupon(Coupon coupon);
    void updateCoupon(Coupon coupon);
    void deleteCoupon(Integer cpnId);
    Coupon getCouponById(Integer cpnId);
    List<Coupon> getAllCoupons();
    List<Coupon> getCouponsByEmployeeId(Integer empId);
    List<Coupon> getCouponsByTagId(Integer tagId);
    List<Coupon> getCouponsByDateRange(Timestamp  startDate, Timestamp  endDate);
    List<Coupon> getCouponsByMinAmount(int minAmount);
}
