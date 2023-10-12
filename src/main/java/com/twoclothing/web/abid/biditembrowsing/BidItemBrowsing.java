package com.twoclothing.web.abid.biditembrowsing;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Table(name = "biditembrowsing")
public class BidItemBrowsing implements Serializable {

    @EmbeddedId
    private CompositeDrtail compositeKey;

	@Column(name = "browsingtime", updatable = false, nullable = false)
	private  Timestamp browsingTime;

    public BidItemBrowsing() {
    }

    public BidItemBrowsing(CompositeDrtail compositeKey, Timestamp browsingTime) {
        this.compositeKey = compositeKey;
        this.browsingTime = browsingTime;
    }

    @Override
    public String toString() {
        return "BidItemBrowsing{" +
                "compositeKey=" + compositeKey +
                ", browsingTime=" + browsingTime +
                '}';
    }

    public CompositeDrtail getCompositeKey() {
        return compositeKey;
    }

    public void setCompositeKey(CompositeDrtail compositeKey) {
        this.compositeKey = compositeKey;
    }

    public Timestamp getBrowsingTime() {
        return browsingTime;
    }

    public void setBrowsingTime(Timestamp browsingTime) {
        this.browsingTime = browsingTime;
    }

    @Embeddable
	public static class CompositeDrtail implements Serializable {

        private static final long serialVersionUID = 1L;

        @Column(name = "mbrid")
        private Integer mbrId;

        @Column(name = "biditemid")
        private Integer biditemid;

        public CompositeDrtail() {
        }

        public CompositeDrtail(Integer mbrId, Integer biditemid) {
            this.mbrId = mbrId;
            this.biditemid = biditemid;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompositeDrtail that = (CompositeDrtail) o;
            return Objects.equals(mbrId, that.mbrId) && Objects.equals(biditemid, that.biditemid);
        }

        @Override
        public int hashCode() {
            return Objects.hash(mbrId, biditemid);
        }

        public Integer getMbrId() {
            return mbrId;
        }

        public void setMbrId(Integer mbrId) {
            this.mbrId = mbrId;
        }

        public Integer getBiditemid() {
            return biditemid;
        }

        public void setBiditemid(Integer biditemid) {
            this.biditemid = biditemid;
        }
    }
}

	