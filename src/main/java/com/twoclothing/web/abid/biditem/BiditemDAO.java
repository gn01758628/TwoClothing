package com.twoclothing.web.abid.biditem;

import com.twoclothing.web.categorytags.CategoryTags;

import java.sql.Timestamp;
import java.util.List;

public interface BiditemDAO {

    /**
     * 新增
     *
     * @return PrimaryKey
     */
    int insert(BidItem biditem);

    BidItem getByPrimaryKey(Integer bidItemId);

    List<BidItem> getAll();

    List<BidItem> getAllByMbrId(Integer mbrId);

    List<BidItem> getAllByBidStatus(Integer bidStatus);

    List<BidItem> getAllByStartTime(Timestamp startTime);

    List<BidItem> getAllByStartTimeZone(Timestamp startTimeStart, Timestamp startTimeEnd);

    List<BidItem> getAllByEndtTime(Timestamp endtTime);

    List<BidItem> getAllByEndtTimeZone(Timestamp endTimeStart, Timestamp endTimeEnd);

    List<BidItem> getAllByEmpId(Integer empId);

    List<BidItem> getAllByTagId(List<CategoryTags> list);

    void delete(Integer bidItemId);

}
