package com.twoclothing.web.balancehistory;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class BalanceHistory implements Serializable {
    private Integer balanceId;
    private Integer mbrId;
    private Integer orderId;
    private Integer bidOrderId;
    private Integer wrId;
    private LocalDateTime changeDate;
    private Integer changeValue;

    public BalanceHistory() {
    }

    public BalanceHistory(Integer balanceId, Integer mbrId, Integer orderId, Integer bidOrderId, Integer wrId, LocalDateTime changeDate, Integer changeValue) {
        this.balanceId = balanceId;
        this.mbrId = mbrId;
        this.orderId = orderId;
        this.bidOrderId = bidOrderId;
        this.wrId = wrId;
        this.changeDate = changeDate;
        this.changeValue = changeValue;
    }

    @Override
    public String toString() {
        return "BalanceHistory{" +
                "balanceId=" + balanceId +
                ", mbrId=" + mbrId +
                ", orderId=" + orderId +
                ", bidOrderId=" + bidOrderId +
                ", wrId=" + wrId +
                ", changeDate=" + changeDate +
                ", changeValue=" + changeValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceHistory that = (BalanceHistory) o;
        return Objects.equals(balanceId, that.balanceId) && Objects.equals(mbrId, that.mbrId) && Objects.equals(orderId, that.orderId) && Objects.equals(bidOrderId, that.bidOrderId) && Objects.equals(wrId, that.wrId) && Objects.equals(changeDate, that.changeDate) && Objects.equals(changeValue, that.changeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balanceId, mbrId, orderId, bidOrderId, wrId, changeDate, changeValue);
    }

    public Integer getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Integer balanceId) {
        this.balanceId = balanceId;
    }

    public Integer getMbrId() {
        return mbrId;
    }

    public void setMbrId(Integer mbrId) {
        this.mbrId = mbrId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getBidOrderId() {
        return bidOrderId;
    }

    public void setBidOrderId(Integer bidOrderId) {
        this.bidOrderId = bidOrderId;
    }

    public Integer getWrId() {
        return wrId;
    }

    public void setWrId(Integer wrId) {
        this.wrId = wrId;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDateTime changeDate) {
        this.changeDate = changeDate;
    }

    public Integer getChangeValue() {
        return changeValue;
    }

    public void setChangeValue(Integer changeValue) {
        this.changeValue = changeValue;
    }
}
