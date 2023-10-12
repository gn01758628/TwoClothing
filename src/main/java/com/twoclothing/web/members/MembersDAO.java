package com.twoclothing.web.members;

import java.util.List;

public interface MembersDAO {

    /**
     * 新增時只新增(email,pswdhash)
     */
    void insert(Members members);

    Members getByPrimaryKey(Integer mbrId);

    List<Members> getAll();

    List<Members> getAllByMbrName(String mbrName);

    List<Members> getAllByMbrStatus(Integer mbrStatus);

    List<Members> getAllBySellScore(Integer sellScore);

    List<Members> getAllByBuyScore(Integer buyScore);

    void updateMbrName(Members members);

    void updatePSWDHash(Members members);

    void updateMbrStatus(Members members);

    void updateAvatar(Members members);

    void updateShopImg01(Members members);

    void updateShopImg02(Members members);

    void updateMbrPoint(Members members);

    void updateBalance(Members members);

    void updateBuyStarRating(Members members);

    void updateSellStarRating(Members members);

    void updateLastLogin(Members members);

    void updateSellScore(Members members);

    void updateBuyScore(Members members);

    void delete(Integer mbrId);
}
