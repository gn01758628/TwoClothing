package com.twoclothing.chengHan.service;

import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditemimage.BidItemImage;

public interface BidItemFrontService {

    void addBidItem(BidItem bidItem);

    void addBidItemImage(BidItemImage bidItemImage);
}
