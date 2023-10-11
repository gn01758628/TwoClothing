package com.twoclothing.web.follow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "follow")
public class Follow implements Serializable {

    @Id
    @Column(name = "mbrid")
    private Integer mbrId;

    @Id
    @Column(name = "followid")
    private Integer followId;

    CompositeDetail getCompositeKey() {
        return new CompositeDetail(mbrId, followId);
    }

    void setCompositeKey(CompositeDetail key) {
        this.mbrId = key.getMbrId();
        this.followId = key.getFollowId();
    }

    public Follow() {
    }

    public Follow(Integer mbrId, Integer followId) {
        this.mbrId = mbrId;
        this.followId = followId;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "mbrId=" + mbrId +
                ", followId=" + followId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Follow follow = (Follow) o;
        return Objects.equals(mbrId, follow.mbrId) && Objects.equals(followId, follow.followId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbrId, followId);
    }

    public Integer getMbrId() {
        return mbrId;
    }

    public void setMbrId(Integer mbrId) {
        this.mbrId = mbrId;
    }

    public Integer getFollowId() {
        return followId;
    }

    public void setFollowId(Integer followId) {
        this.followId = followId;
    }


    static class CompositeDetail implements Serializable {
        private static final long serialVersionUID = 1L;
        private Integer mbrId;
        private Integer followId;

        public CompositeDetail() {
        }

        public CompositeDetail(Integer mbrId, Integer followId) {
            this.mbrId = mbrId;
            this.followId = followId;
        }

        @Override
        public String toString() {
            return "CompositeDetail{" +
                    "mbrId=" + mbrId +
                    ", followId=" + followId +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompositeDetail that = (CompositeDetail) o;
            return Objects.equals(mbrId, that.mbrId) && Objects.equals(followId, that.followId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(mbrId, followId);
        }

        public Integer getMbrId() {
            return mbrId;
        }

        public void setMbrId(Integer mbrId) {
            this.mbrId = mbrId;
        }

        public Integer getFollowId() {
            return followId;
        }

        public void setFollowId(Integer followId) {
            this.followId = followId;
        }
    }
}
