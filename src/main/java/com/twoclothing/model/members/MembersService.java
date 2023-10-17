package com.twoclothing.model.members;

import java.util.List;

public interface MembersService {
	Members addMembers (Members members);
	
	List<Members> getAllMembers(int currentPage);
	
	Members getByPrimaryKey(Integer mbrId);
	
	Members updateMembers (Members members);
	
	int getPageTotal();
}
