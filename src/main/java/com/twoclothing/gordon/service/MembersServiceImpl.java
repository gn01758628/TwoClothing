package com.twoclothing.gordon.service;

import java.sql.Timestamp;
import java.util.List;

import com.twoclothing.model.members.Members;
import com.twoclothing.model.members.MembersDAO;
import com.twoclothing.model.members.MembersHibernateDAO;
import com.twoclothing.utils.HibernateUtil;



public class MembersServiceImpl implements MembersService{
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
	public Members addMembers (Members members) {
		return members;
//		dao.insert(members);
	}

	
	public List<Members> getAll() {
		return dao.getAll();
	}

	public Members getByPrimaryKey(Integer mbrId) {		
		return dao.getByPrimaryKey(mbrId);
	}


	
	public Members updateMembers(Integer mbeId, Integer sellScore, Integer buyScore) {
	    Members members = dao.getByPrimaryKey(mbeId); // ============================先獲取現有的 Members 物件
	    if (members != null) {
	        members.setSellScore(sellScore);
	        members.setBuyScore(buyScore);
	        dao.update(members); // 使用現有物件進行更新
	    }
	    return members; // 返回已更新的 Members 物件
	}
	public int deleteMembers(Integer mbrId) {
		return dao.delete(mbrId);
	}


	@Override
	public Members updateMembers(Members members) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
