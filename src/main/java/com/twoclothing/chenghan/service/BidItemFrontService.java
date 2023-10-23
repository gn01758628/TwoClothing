package com.twoclothing.chenghan.service;

import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.categorytags.CategoryTags;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface BidItemFrontService {

    /**
     * @return 返回主鍵
     */
    int addBidItem(BidItem bidItem);

    void addBidItemImage(BidItemImage bidItemImage);

    BidItem getBidItemByBidItemId(Integer bidItemId);

    List<BidItem> getAllBidItemByMbrid(Integer mbrId);

    List<CategoryTags> getAllCategoryTags();

    List<Integer> getAllSelectableTagsId();

}
