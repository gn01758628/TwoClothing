package com.twoclothing.gordon.service;

import java.util.List;

import com.twoclothing.model.members.Members;

public interface MembersService {
	Members addMembers (Members members);
	
	List<Members> getAllMembers(int currentPage);
	
	Members getByPrimaryKey(Integer mbrId);
	
	Members updateMembers (Members members);
	
	int getPageTotal();
}
