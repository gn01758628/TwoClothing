package com.twoclothing.gordon.service;

import java.util.List;

import com.twoclothing.model.members.Members;
import com.twoclothing.model.members.MembersDAO;
import com.twoclothing.model.members.MembersHibernateDAO;
import com.twoclothing.utils.HibernateUtil;



public class MembersServiceImpl implements MembersService{
	private MembersDAO dao;
	
	public MembersServiceImpl() {
		dao = new MembersHibernateDAO(HibernateUtil.getSessionFactory());

	}
	
	@Override
	public Members addMembers(String email, String pswdHash) {
		Members members = new Members();
		
		members.setEmail(email);
		members.setPswdHash(pswdHash);
		
		dao.insert(members);
		
		return members;
	}
	
	//預留給 Struts 2 或 Spring MVC 用
	@Override
	public Members addMembers (Members members) {
		return members;
//		dao.insert(members);
	}

	@Override
	public List<Members> getAll() {
		return dao.getAll();
	}
	@Override
	public Members getByPrimaryKey(Integer mbrId) {		
		return dao.getByPrimaryKey(mbrId);
	}
	@Override
	public Members getByEmail(String email) {
		Members members = dao.getByEmail(email);
	    if (members != null ) {
	        return members; 
	    }
	    return null; 
	}

	@Override
	public Members updateMembers(Integer mbrId, Integer sellScore, Integer buyScore) {
	    Members members = dao.getByPrimaryKey(mbrId); // ============================先獲取現有的 Members 物件
	    if (members != null) {
	        members.setSellScore(sellScore);
	        members.setBuyScore(buyScore);
	        dao.update(members); // 使用現有物件進行更新
	    }
	    return members; // 返回已更新的 Members 物件
	}
	@Override
	public int deleteMembers(Integer mbrId) {
		return dao.delete(mbrId);
	}


	@Override
	public Members updateMembers(Members members) {
	    int updateResult = dao.update(members);
	    
	    if (updateResult == 1) {
	        // 更新成功
	        return members;
	    } else {
	       
	        
	        return null;
	    }
	}






	

}
