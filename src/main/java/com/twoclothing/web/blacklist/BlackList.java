package com.twoclothing.web.blacklist;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "blacklist")
public class BlackList implements Serializable {

    @Id
    @Column(name = "mbrid")
    private Integer mbrId;

    @Id
    @Column(name = "blackid")
    private Integer blackId;

    CompositeDetail getCompositeKey() {
        return new CompositeDetail(mbrId, blackId);
    }

    void setCompositeKey(CompositeDetail key) {
        this.mbrId = key.getMbrId();
        this.blackId = key.getBlackId();
    }

    public BlackList() {
    }

    public BlackList(Integer mbrId, Integer blackId) {
        this.mbrId = mbrId;
        this.blackId = blackId;
    }

    @Override
    public String toString() {
        return "BlackList{" +
                "mbrId=" + mbrId +
                ", BlackId=" + blackId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackList blackList = (BlackList) o;
        return Objects.equals(mbrId, blackList.mbrId) && Objects.equals(blackId, blackList.blackId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbrId, blackId);
    }

    public Integer getMbrId() {
        return mbrId;
    }

    public void setMbrId(Integer mbrId) {
        this.mbrId = mbrId;
    }

    public Integer getBlackId() {
        return blackId;
    }

    public void setBlackId(Integer blackId) {
        this.blackId = blackId;
    }

    static class CompositeDetail implements Serializable {
        private Integer mbrId;
        private Integer blackId;

        public CompositeDetail() {
        }

        public CompositeDetail(Integer mbrId, Integer blackId) {
            this.mbrId = mbrId;
            this.blackId = blackId;
        }

        @Override
        public String toString() {
            return "CompositeDetail{" +
                    "mbrId=" + mbrId +
                    ", blackId=" + blackId +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompositeDetail that = (CompositeDetail) o;
            return Objects.equals(mbrId, that.mbrId) && Objects.equals(blackId, that.blackId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(mbrId, blackId);
        }

        public Integer getMbrId() {
            return mbrId;
        }

        public void setMbrId(Integer mbrId) {
            this.mbrId = mbrId;
        }

        public Integer getBlackId() {
            return blackId;
        }

        public void setBlackId(Integer blackId) {
            this.blackId = blackId;
        }
    }
}
