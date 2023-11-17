package com.twoclothing.chijung.service;

import java.util.List;

import com.twoclothing.model.dto.MembersCouponDTO;

public interface MembersCouponService {

	List<MembersCouponDTO> getAllMembersCouponDTOByMemberId(Integer memberId);
	
}
