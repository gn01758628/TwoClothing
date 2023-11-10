package com.twoclothing.redismodel.bidItemViewHistory;

import java.util.List;

public interface BidItemViewHistoryDAO {

    void insert(BidItemViewHistory history);

    List<Integer> getAllBidItemIdByMbrId(Integer mbrId);
}
