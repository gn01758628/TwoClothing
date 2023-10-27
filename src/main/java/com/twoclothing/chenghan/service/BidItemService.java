package com.twoclothing.chenghan.service;

import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.members.Members;

import java.util.List;

public interface BidItemService {

    /**
     * @return 返回主鍵
     */
    int addBidItem(BidItem bidItem);

    void addBidItemImage(BidItemImage bidItemImage);

    BidItem getBidItemByBidItemId(Integer bidItemId);

    Members getMembersByMbrId(Integer mbrId);

    List<BidItem> getAllBidItemByMbrid(Integer mbrId);

    List<CategoryTags> getAllCategoryTags();

    List<Employee> getAllEmployee();

    List<Integer> getAllSelectableTagsId();

}
