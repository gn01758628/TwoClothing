package com.twoclothing.model.follow;

import java.util.List;

public interface FollowDAO {
    void insert(Follow follow);

    Follow getByCompositeKey(Integer mbrId, Integer followId);

//    List<Follow> getAll();

//    List<Follow> getAllByMbrId(Integer mbrId);
    
    List<Follow> getAllByMbrId(Integer mbrId, int currentPage);
    
    long getTotal(Integer mbrId);

//    List<Follow> getAllByFollowId(Integer followId);

//    void delete(Integer mbrId, Integer followId);
    
    void delete(Follow follow);
}