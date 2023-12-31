package com.twoclothing.model.abid.biditem;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "biditem")
public class BidItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "biditemid", updatable = false)
    private Integer bidItemId;

    @Column(name = "bidname", nullable = false)
    private String bidName;

    @Column(name = "grade", nullable = false, columnDefinition = "TINYINT")
    private Integer grade;

    @Column(name = "size", columnDefinition = "TINYINT")
    private Integer size;

    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "tagid", nullable = false)
    private Integer tagId;

    @Column(name = "mbrid", updatable = false, nullable = false)
    private Integer mbrId;

    @Column(name = "startprice", nullable = false)
    private Integer startPrice;

    @Column(name = "reserveprice")
    private Integer reservePrice;

    @Column(name = "directprice")
    private Integer directPrice;

    @Column(name = "bidstatus", insertable = false, nullable = false, columnDefinition = "TINYINT")
    private Integer bidStatus = 0;

    @Column(name = "starttime")
    private Timestamp startTime;

    @Column(name = "endtime")
    private Timestamp endTime;

    @Column(name = "empid", insertable = false)
    private Integer empId;

    public BidItem() {
    }

    public BidItem(String bidName, Integer grade, Integer size, String detail, Integer tagId, Integer mbrId, Integer startPrice, Integer reservePrice, Integer directPrice, Integer bidStatus, Timestamp startTime, Timestamp endTime, Integer empId) {
        this.bidName = bidName;
        this.grade = grade;
        this.size = size;
        this.detail = detail;
        this.tagId = tagId;
        this.mbrId = mbrId;
        this.startPrice = startPrice;
        this.reservePrice = reservePrice;
        this.directPrice = directPrice;
        this.bidStatus = bidStatus;
        this.startTime = startTime;
        this.endTime = endTime;
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "BidItem{" +
                "bidItemId=" + bidItemId +
                ", bidName='" + bidName + '\'' +
                ", grade=" + grade +
                ", size=" + size +
                ", detail='" + detail + '\'' +
                ", tagId=" + tagId +
                ", mbrId=" + mbrId +
                ", startPrice=" + startPrice +
                ", reservePrice=" + reservePrice +
                ", directPrice=" + directPrice +
                ", bidStatus=" + bidStatus +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", empId=" + empId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidItem bidItem = (BidItem) o;
        return Objects.equals(bidItemId, bidItem.bidItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bidItemId);
    }

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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getMbrId() {
        return mbrId;
    }

    public void setMbrId(Integer mbrId) {
        this.mbrId = mbrId;
    }

    public Integer getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Integer startPrice) {
        this.startPrice = startPrice;
    }

    public Integer getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(Integer reservePrice) {
        this.reservePrice = reservePrice;
    }

    public Integer getDirectPrice() {
        return directPrice;
    }

    public void setDirectPrice(Integer directPrice) {
        this.directPrice = directPrice;
    }

    public Integer getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(Integer bidStatus) {
        this.bidStatus = bidStatus;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
}
