package com.twoclothing.web.blacklist;

<<<<<<< HEAD
=======
import javax.persistence.*;
>>>>>>> refs/heads/master
import java.io.Serializable;
import java.util.Objects;

public class BlackList implements Serializable {
<<<<<<< HEAD
    private Integer mbrId;
    private Integer blackId;
=======

    @EmbeddedId
    private CompositeDetail compositeKey;
>>>>>>> refs/heads/master

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

<<<<<<< HEAD
    public Integer getBlackId() {
        return blackId;
    }

    public void setBlackId(Integer blackId) {
        this.blackId = blackId;
=======
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
>>>>>>> refs/heads/master
    }
}

