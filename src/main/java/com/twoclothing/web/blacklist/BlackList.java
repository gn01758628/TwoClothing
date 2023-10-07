package com.twoclothing.web.blacklist;

import java.io.Serializable;
import java.util.Objects;

public class BlackList implements Serializable {
    private Integer mbrId;
    private Integer BlackId;

    public BlackList() {
    }

    @Override
    public String toString() {
        return "BlackList{" +
                "mbrId=" + mbrId +
                ", BlackId=" + BlackId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackList blackList = (BlackList) o;
        return Objects.equals(mbrId, blackList.mbrId) && Objects.equals(BlackId, blackList.BlackId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbrId, BlackId);
    }

    public Integer getMbrId() {
        return mbrId;
    }

    public void setMbrId(Integer mbrId) {
        this.mbrId = mbrId;
    }

    public Integer getBlackId() {
        return BlackId;
    }

    public void setBlackId(Integer blackId) {
        BlackId = blackId;
    }
}
