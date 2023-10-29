package com.twoclothing.model.abid.biditem;

import java.util.List;
import java.util.Map;

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
     * @return 狀態不是5或6的全部商品
     */
    List<BidItem> getAllLegalByMbrId(Integer mbrId);

    List<BidItem> getAllByCompositeQuery(Map<String, String[]> compositeQuery);

    /**
     * @return 修改是否成功
     */
    boolean update(BidItem bidItem);

}
