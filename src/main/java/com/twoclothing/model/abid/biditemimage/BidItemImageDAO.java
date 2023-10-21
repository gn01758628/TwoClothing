package com.twoclothing.model.abid.biditemimage;

import java.util.List;

public interface BidItemImageDAO {

    /**
     * @return PrimaryKey
     */
    int insert(BidItemImage bidItemImage);

    BidItemImage getByPrimaryKey(Integer imageId);

    /**
     * @param position 第幾張圖片
     */
    BidItemImage getPositionImageByBidItemId(Integer bidItemId, int position);

    List<BidItemImage> getAllByBidItemId(Integer bidItemId);


    /**
     * @return 修改是否成功
     */
    boolean update(BidItemImage bidItemImage);


}
