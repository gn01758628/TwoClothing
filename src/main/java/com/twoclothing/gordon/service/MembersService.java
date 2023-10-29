package com.twoclothing.gordon.service;

import java.util.List;

import com.twoclothing.model.members.Members;

public interface MembersService {
	
	Members addMembers( String email, String pswdHash);
	
	Members addMembers (Members members);
	
	List<Members> getAll();
	
	Members getByPrimaryKey(Integer mbrId);
	
	Members getByEmail(String email);
	
	Members updateMembers (Members members);
	
	Members updateMembers(Integer mbrId, Integer sellScore, Integer buyScore);
	
	int deleteMembers(Integer mbrId);
	
	
}
