package com.twoclothing.model.abid.biditem;

import java.util.List;

public interface BidItemDAO {

    /**
     * @return PrimaryKey
     */
    int insert(BidItem biditem);

    BidItem getByPrimaryKey(Integer bidItemId);

    List<BidItem> getAll();

    List<BidItem> getAllByEmpId(Integer empId);

    List<BidItem> getAllByMbrId(Integer mbrId);

    /**
     * @return 修改是否成功
     */
    boolean update(BidItem bidItem);

}
