package com.twoclothing.chenghan.service;

import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.members.Members;
import com.twoclothing.redismodel.bidItemViewHistory.BidItemViewHistory;
import com.twoclothing.redismodel.bidrecord.BidRecord;
import com.twoclothing.redismodel.notice.Notice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BidItemService {

    /**
     * @return 返回主鍵
     */
    int addBidItem(BidItem bidItem);

    void addBidItemImage(BidItemImage bidItemImage);

    void addNotice(Notice notice, Integer mbrId);

    void addBidRecord(BidRecord bidRecord, Integer bidItemId, LocalDateTime endTime);

    void addBidItemViewHistory(BidItemViewHistory bidItemViewHistory);

    BidItem getBidItemByBidItemId(Integer bidItemId);

    Members getMembersByMbrId(Integer mbrId);

    Employee getEmployeeByEmpId(Integer empId);

    CategoryTags getCategoryTagsByTagId(Integer tagId);

    List<BidItem> getAllLegalBidItemByMbrid(Integer mbrId);

    List<BidItem> getAllBidItemByCompositeQuery(Map<String, String[]> compositeQuery);

    List<CategoryTags> getAllCategoryTags();

    List<Employee> getAllEmployee();

    List<Integer> getAllSelectableTagsId();

    List<BidRecord> getAllBidRecordByBidItemId(Integer bidItemId);

    Set<Integer> getAllMbrIdInBidRecord(Integer bidItemId);

    BidRecord getBidRecordByIndex(Integer bidItemId, int index);

    boolean updateBidItem(BidItem bidItem);

}
