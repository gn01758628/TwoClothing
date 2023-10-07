package com.twoclothing.web.pointhistory;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class PointHistory implements Serializable {
    private Integer pointId;
    private Integer mbrId;
    private Integer orderId;
    private LocalDateTime changeDate;
    private Integer changeValue;

    public PointHistory() {
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
