package com.twoclothing.model.members;

import java.util.List;

public interface MembersDAO {

    /**
     * 新增時只新增(email,pswdhash)
     */
	int insert(Members members);

    Members getByPrimaryKey(Integer mbrId);

    List<Members> getAll();

    List<Members> getAllByMbrName(String mbrName);
    
    List<Members> getAllByEmail(String email);

    List<Members> getAllByMbrStatus(Integer mbrStatus);

    List<Members> getAllBySellScore(Integer sellScore);

    List<Members> getAllByBuyScore(Integer buyScore);

    int update(Members members);
    
    int delete(Integer mbrId);
    
   
}
