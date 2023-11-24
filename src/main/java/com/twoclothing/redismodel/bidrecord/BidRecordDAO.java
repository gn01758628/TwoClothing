package com.twoclothing.redismodel.bidrecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface BidRecordDAO {

    void insert(BidRecord bidRecord, Integer bidItemId, LocalDateTime endTime);

    List<BidRecord> getAll(Integer bidItemId);

    BidRecord getIndexRecordByKey(Integer bidItemId, int index);

    Set<Integer> getAllMbrIdByKey(Integer bidItemId);

    List<Integer> getAllBitItemIdByMbrId(Integer mbrId);
}
