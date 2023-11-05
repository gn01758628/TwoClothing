package com.twoclothing.chenghan.service;

import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.members.Members;
import com.twoclothing.redismodel.bidrecord.BidRecord;
import com.twoclothing.redismodel.notice.Notice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BidItemService {

    /**
     * @return 返回主鍵
     */
    int addBidItem(BidItem bidItem);

    void addBidItemImage(BidItemImage bidItemImage);

    void addVentNotices(Notice notice, Integer mbrId);

    void addBidRecord(BidRecord bidRecord, Integer bidItemId, LocalDateTime endTime);

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

    boolean updateBidItem(BidItem bidItem);

}
