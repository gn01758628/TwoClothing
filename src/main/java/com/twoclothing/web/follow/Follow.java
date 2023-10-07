package com.twoclothing.web.follow;

import java.io.Serializable;
import java.util.Objects;

public class Follow implements Serializable {
    private Integer mbrId;
    private Integer followId;

    public Follow() {
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
}
