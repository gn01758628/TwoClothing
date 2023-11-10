package com.twoclothing.redismodel.bidItemViewHistory;

import java.io.Serializable;
import java.util.Objects;

public class BidItemViewHistory implements Serializable {

    private Integer mbrId;

    private Integer bidItemId;

    private long timestamp;

    public BidItemViewHistory() {
    }

    public BidItemViewHistory(Integer mbrId, Integer bidItemId, long timestamp) {
        this.mbrId = mbrId;
        this.bidItemId = bidItemId;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "BidItemViewHistory{" +
                "mbrId=" + mbrId +
                ", bidItemId=" + bidItemId +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidItemViewHistory that = (BidItemViewHistory) o;
        return timestamp == that.timestamp && Objects.equals(mbrId, that.mbrId) && Objects.equals(bidItemId, that.bidItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbrId, bidItemId, timestamp);
    }

    public Integer getMbrId() {
        return mbrId;
    }

    public void setMbrId(Integer mbrId) {
        this.mbrId = mbrId;
    }

    public Integer getBidItemId() {
        return bidItemId;
    }

    public void setBidItemId(Integer bidItemId) {
        this.bidItemId = bidItemId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
