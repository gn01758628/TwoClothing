package com.twoclothing.chenghan.service;

import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.categorytags.CategoryTags;

import java.util.List;

public interface BidItemFrontService {

    void addBidItem(BidItem bidItem);

    void addBidItemImage(BidItemImage bidItemImage);

    List<CategoryTags> getAllCategoryTags();
}
