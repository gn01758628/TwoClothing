package com.twoclothing.model.abid.biditem;

import java.sql.Timestamp;
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

    List<BidItem> getAllByBidStatus(Integer bidStatus);

    /**
     * @return 狀態不是5或6的全部商品
     */
    List<BidItem> getAllLegalByMbrId(Integer mbrId);

    /**
     * @return 上架中且在指定時間之前結束
     */
    List<BidItem> getAllActiveBidItemsByEndTime(Timestamp time);

    /**
     * @return 已過審且在指定時間之前開始
     */
    List<BidItem> getAllPassBidItemsByStartTime(Timestamp time);

    List<BidItem> getAllByCompositeQuery(Map<String, String[]> compositeQuery);

    /**
     * @return 修改是否成功
     */
    boolean update(BidItem bidItem);

}
