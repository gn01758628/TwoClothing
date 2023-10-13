package com.twoclothing.web.members;

import java.util.List;

public interface MembersDAO {

    /**
     * 新增時只新增(email,pswdhash)
     */
	int insert(Members members);

    Members getByPrimaryKey(Integer mbrId);

    List<Members> getAll();

    List<Members> getAllByMbrName(String mbrName);

    List<Members> getAllByMbrStatus(Integer mbrStatus);

    List<Members> getAllBySellScore(Integer sellScore);

    List<Members> getAllByBuyScore(Integer buyScore);

    int updateMbrName(Members members);

    int updatePSWDHash(Members members);

    int updateMbrStatus(Members members);

    int updateAvatar(Members members);

    int updateShopImg01(Members members);

    int updateShopImg02(Members members);

    int updateMbrPoint(Members members);

    int updateBalance(Members members);

    int updateBuyStarRating(Members members);

    int updateSellStarRating(Members members);

    int updateLastLogin(Members members);

    int updateSellScore(Members members);

    int updateBuyScore(Members members);

    int delete(Integer mbrId);
}
