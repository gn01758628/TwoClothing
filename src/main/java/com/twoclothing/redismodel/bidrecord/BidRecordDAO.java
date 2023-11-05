package com.twoclothing.redismodel.bidrecord;

import java.time.LocalDateTime;
import java.util.List;

public interface BidRecordDAO {

    void insert(BidRecord bidRecord, Integer bidItemId, LocalDateTime endTime);

    List<BidRecord> getAll(Integer bidItemId);
}
