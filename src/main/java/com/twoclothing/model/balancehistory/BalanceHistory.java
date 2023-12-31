package com.twoclothing.model.balancehistory;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "balancehistory")
public class BalanceHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balanceid", insertable = false, updatable = false)
    private Integer balanceId;

    @Column(name = "mbrid", updatable = false, nullable = false)
    private Integer mbrId;

    @Column(name = "orderid", updatable = false)
    private Integer orderId;

    @Column(name = "bidorderid", updatable = false)
    private Integer bidOrderId;

    @Column(name = "wrid", updatable = false)
    private Integer wrId;

    @Column(name = "changedate", updatable = false, nullable = false)
    private Timestamp changeDate;

    @Column(name = "changeValue", updatable = false, nullable = false)
    private Integer changeValue;

    public BalanceHistory() {
    }

    public BalanceHistory(Integer balanceId, Integer mbrId, Integer orderId, Integer bidOrderId, Integer wrId, Timestamp changeDate, Integer changeValue) {
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

    public Timestamp getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Timestamp changeDate) {
        this.changeDate = changeDate;
    }

    public Integer getChangeValue() {
        return changeValue;
    }

    public void setChangeValue(Integer changeValue) {
        this.changeValue = changeValue;
    }
}