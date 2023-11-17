package com.twoclothing.redismodel.notice;

import java.util.List;

public interface NoticeDAO {

    void insert(Notice notice, Integer mbrId);

    List<Notice> getAllByMbrId(Integer mbrId);


}
