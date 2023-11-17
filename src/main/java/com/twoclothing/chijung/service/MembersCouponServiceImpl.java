package com.twoclothing.chijung.service;

import java.util.List;

import com.twoclothing.model.dto.MembersCouponDTO;
import com.twoclothing.model.memberscoupon.MembersCouponDAO;
import com.twoclothing.model.memberscoupon.MembersCouponHibernateDAO;

public class MembersCouponServiceImpl implements MembersCouponService {
	
	private MembersCouponDAO dao = new MembersCouponHibernateDAO();
	
	public List<MembersCouponDTO> getAllMembersCouponDTOByMemberId(Integer memberId){
		return dao.getAllMembersCouponDTOByMemberId(memberId);
	}
}
