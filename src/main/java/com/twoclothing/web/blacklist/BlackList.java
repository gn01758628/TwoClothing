package com.twoclothing.web.blacklist;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "blacklist")
public class BlackList implements Serializable {

    @EmbeddedId
    private CompositeDetail compositeKey;

    public BlackList() {
    }

    public BlackList(CompositeDetail compositeKey) {
        this.compositeKey = compositeKey;
    }

    @Override
    public String toString() {
        return "BlackList{" +
                "compositeKey=" + compositeKey +
                '}';
    }

    public CompositeDetail getCompositeKey() {
        return compositeKey;
    }

    public void setCompositeKey(CompositeDetail compositeKey) {
        this.compositeKey = compositeKey;
    }

    @Embeddable
    public static class CompositeDetail implements Serializable {
        private static final long serialVersionUID = 1L;

        @Column(name = "mbrid")
        private Integer mbrid;

        @Column(name = "blackid")
        private Integer blackid;

        public CompositeDetail() {
        }

        public CompositeDetail(Integer mbrid, Integer blackid) {
            this.mbrid = mbrid;
            this.blackid = blackid;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompositeDetail that = (CompositeDetail) o;
            return Objects.equals(mbrid, that.mbrid) && Objects.equals(blackid, that.blackid);
        }

        @Override
        public int hashCode() {
            return Objects.hash(mbrid, blackid);
        }

        public Integer getMbrid() {
            return mbrid;
        }

        public void setMbrid(Integer mbrid) {
            this.mbrid = mbrid;
        }

        public Integer getBlackid() {
            return blackid;
        }

        public void setBlackid(Integer blackid) {
            this.blackid = blackid;
        }
    }
}

