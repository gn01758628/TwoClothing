package com.twoclothing.web.blacklist;

import java.util.List;

public interface BlackListDAO {

    public void insert(BlackList blackList);

    public BlackList getByCompositeKey(Integer mbrId, Integer blackId);

    public List<BlackList> getAll();

    public List<BlackList> getAllByMbrId(Integer mbrId);

    public List<BlackList> getAllByBlackId(Integer blackId);

    public void delete(Integer mbrId, Integer blackId);
}
