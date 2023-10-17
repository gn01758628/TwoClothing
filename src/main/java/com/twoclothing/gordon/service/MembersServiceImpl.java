package com.twoclothing.gordon.service;

import java.util.List;

import com.twoclothing.model.members.Members;
import com.twoclothing.model.members.MembersDAO;
import com.twoclothing.model.members.MembersHibernateDAO;
import com.twoclothing.utils.HibernateUtil;



public class MembersServiceImpl {
	private MembersDAO dao;
	
	public MembersServiceImpl() {
		dao = new MembersHibernateDAO(HibernateUtil.getSessionFactory());
//		dao = new EmpDAO();
	}
	
	
	public Members addMembers(String email, String pswdHash) {
		Members members = new Members();
		members.setEmail(email);
		members.setPswdHash(pswdHash);
		
		dao.insert(members);
		
		return members;
	}
	
	//預留給 Struts 2 或 Spring MVC 用
//	public void addMembers(Members members) {
//		dao.insert(members);
//	}

	
	public List<Members> getAll() {
		return dao.getAll();
	}

	public Members getByPrimaryKey(Integer mbrId) {		
		return dao.getByPrimaryKey(mbrId);
	}

	public int updateMembers(Members members) {
		return dao.update(members);
	}
	public int deleteMembers(Integer mbrId) {
		return dao.delete(mbrId);
	}
	

}
