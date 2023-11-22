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

    List<BidItem> getAllSubByTagId(Integer tagId);

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

    // 瀏覽頁面相關 (競標商品已上架/分頁查詢)

    /**
     * @return 不分類分頁查詢
     */
    List<BidItem> getAllLimit(int currentPage, int pageMaxResult);

    /**
     * @return 上架中的總數量
     */
    int countByActive();

    /**
     * @return 類別分頁查詢
     */
    List<BidItem> getAllByTagsLimit(int currentPage, int pageMaxResult, Integer tagId);

    /**
     * @return 類別上架總數量
     */
    int countByTags(Integer tagId);

}
