package com.twoclothing.web.pointhistory;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class PointHistory implements Serializable {
    private Integer pointId;
    private Integer mbrId;
    private Integer orderId;
    private Timestamp changeDate;
    private Integer changeValue;

    public PointHistory() {
    }

    public PointHistory(Integer pointId, Integer mbrId, Integer orderId, Timestamp changeDate, Integer changeValue) {
        this.pointId = pointId;
        this.mbrId = mbrId;
        this.orderId = orderId;
        this.changeDate = changeDate;
        this.changeValue = changeValue;
    }

    @Override
    public String toString() {
        return "PointHistory{" +
                "pointId=" + pointId +
                ", mbrId=" + mbrId +
                ", orderId=" + orderId +
                ", changeDate=" + changeDate +
                ", changeValue=" + changeValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointHistory that = (PointHistory) o;
        return Objects.equals(pointId, that.pointId) && Objects.equals(mbrId, that.mbrId) && Objects.equals(orderId, that.orderId) && Objects.equals(changeDate, that.changeDate) && Objects.equals(changeValue, that.changeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointId, mbrId, orderId, changeDate, changeValue);
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
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
