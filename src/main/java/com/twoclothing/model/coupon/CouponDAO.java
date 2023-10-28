package com.twoclothing.model.coupon;

import java.sql.Timestamp;
import java.util.List;

public interface CouponDAO {
	void insert(Coupon coupon);

	Coupon getByPrimaryKey(Integer cpnId);

	List<Coupon> getAll();

	List<Coupon> getByEmpId(Integer empId);

	List<Coupon> getByTagId(Integer tagId);

	List<Coupon> getByMinAmount(int minAmount);

	void update(Coupon coupon);

	void delete(Integer cpnId);
}
