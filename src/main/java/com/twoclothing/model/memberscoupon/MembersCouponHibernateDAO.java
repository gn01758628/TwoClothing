package com.twoclothing.model.memberscoupon;

import java.util.List;

import org.hibernate.Session;

import com.twoclothing.model.dto.MembersCouponDTO;
import com.twoclothing.utils.HibernateUtil;

public class MembersCouponHibernateDAO implements MembersCouponDAO{
	
	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public List<MembersCouponDTO> getAllMembersCouponDTOByMemberId(Integer memberId) {
		String hql = "SELECT NEW com.twoclothing.model.dto.MembersCouponDTO(mc, c) " +
	             "FROM MembersCoupon mc " +
	             "JOIN Coupon c ON mc.compositeKey.couponId = c.cpnId " +
	             "WHERE mc.compositeKey.memberId = :memberId";

	List<MembersCouponDTO> result = getSession().createQuery(hql, MembersCouponDTO.class)
	        .setParameter("memberId", memberId)
	        .getResultList();

		return result;
	}

}
