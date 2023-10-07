package com.twoclothing.web.follow;

import java.util.List;

public interface FollowDAO {

    public void insert(Follow follow);

    public Follow getByCompositeKey(Integer mbrId, Integer followId);

    public List<Follow> getAll();

    public List<Follow> getAllByMbrId(Integer mbrId);

    public List<Follow> getAllByFollowId(Integer FollowId);

    public void delete(Integer mbrId, Integer followId);
}
