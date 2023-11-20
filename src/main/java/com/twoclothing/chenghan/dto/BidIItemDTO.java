package com.twoclothing.chenghan.dto;

import java.io.Serializable;

public class BidIItemDTO implements Serializable {

    private Integer bidItemId;

    private String bidName;

    private String bidAmountType;

    private Integer currentBid;

    private Long endTime;


    public Integer getBidItemId() {
        return bidItemId;
    }

    public void setBidItemId(Integer bidItemId) {
        this.bidItemId = bidItemId;
    }

    public String getBidName() {
        return bidName;
    }

    public void setBidName(String bidName) {
        this.bidName = bidName;
    }

    public String getBidAmountType() {
        return bidAmountType;
    }

    public void setBidAmountType(String bidAmountType) {
        this.bidAmountType = bidAmountType;
    }

    public Integer getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(Integer currentBid) {
        this.currentBid = currentBid;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
