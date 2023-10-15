package com.twoclothing.model.blacklist;

import java.util.List;

public interface BlackListDAO {
    void insert(BlackList blackList);

    BlackList getByCompositeKey(Integer mbrId, Integer blackId);

    List<BlackList> getAll();

    List<BlackList> getAllByMbrId(Integer mbrId);

    List<BlackList> getAllByBlackId(Integer blackId);

    void delete(Integer mbrId, Integer blackId);
}