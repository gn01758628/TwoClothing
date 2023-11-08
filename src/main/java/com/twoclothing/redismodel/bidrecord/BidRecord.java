package com.twoclothing.redismodel.bidrecord;

import com.twoclothing.utils.FormatUtil;

import java.io.Serializable;
import java.util.Objects;

public class BidRecord implements Comparable<BidRecord>, Serializable {

    private Integer mbrId;

    private String bidAmount;

    private String bidTime;

    public BidRecord() {
    }

    public BidRecord(Integer mbrId, String bidAmount, String bidTime) {
        this.mbrId = mbrId;
        this.bidAmount = bidAmount;
        this.bidTime = bidTime;
    }

    @Override
    public String toString() {
        return "BidRecord{" +
                "mbrId=" + mbrId +
                ", bidAmount='" + bidAmount + '\'' +
                ", bidTime='" + bidTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidRecord bidRecord = (BidRecord) o;
        return Objects.equals(mbrId, bidRecord.mbrId) && Objects.equals(bidAmount, bidRecord.bidAmount) && Objects.equals(bidTime, bidRecord.bidTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbrId, bidAmount, bidTime);
    }

    @Override
    public int compareTo(BidRecord bidRecord) {
        if (this.equals(bidRecord)) return 0;
        Integer thisAmount = FormatUtil.parseFormattedNumber(this.bidAmount);
        Integer thatAmount = FormatUtil.parseFormattedNumber(bidRecord.bidAmount);
        return thisAmount > thatAmount ? -1 : 1;
    }

    public Integer getMbrId() {
        return mbrId;
    }

    public void setMbrId(Integer mbrId) {
        this.mbrId = mbrId;
    }

    public String getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(String bidAmount) {
        this.bidAmount = bidAmount;
    }

    public String getBidTime() {
        return bidTime;
    }

    public void setBidTime(String bidTime) {
        this.bidTime = bidTime;
    }
}
