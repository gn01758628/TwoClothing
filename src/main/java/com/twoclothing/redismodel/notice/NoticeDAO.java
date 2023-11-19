package com.twoclothing.redismodel.notice;

import java.util.List;

public interface NoticeDAO {

    void insert(Notice notice, Integer mbrId);

    List<Notice> getAllByMbrId(Integer mbrId);

    List<Notice> getNoticesByMbrIdAndRead(Integer mbrId, boolean read);

    int getUnreadCountByMbrId(Integer mbrId);

    void markNoticesAsRead(String... noticeIds);


}
