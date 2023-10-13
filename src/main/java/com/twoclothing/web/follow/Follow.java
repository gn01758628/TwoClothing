package com.twoclothing.web.follow;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "follow")
public class Follow implements Serializable {
    @EmbeddedId
    private CompositeDetail compositeKey;

    public Follow() {
    }

    public Follow(CompositeDetail compositeKey) {
        this.compositeKey = compositeKey;
    }

    @Override
    public String toString() {
        return "Follow{" +
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
        private Integer mbrId;

        @Column(name = "followid")
        private Integer followId;

        public CompositeDetail() {
        }

        public CompositeDetail(Integer mbrId, Integer followId) {
            this.mbrId = mbrId;
            this.followId = followId;
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

        public void setFollowid(Integer followId) {
            this.followId = followId;
        }
    }
}