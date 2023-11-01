package com.twoclothing.redismodel.notice;

import java.util.List;

public interface NoticeDAO {

    void insert(Notice notice, String mbrId);

    List<Notice> getAllByMbrId(String mbrId);
}
