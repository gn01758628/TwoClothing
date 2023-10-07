package com.twoclothing.web.blacklist;

import java.io.Serializable;
import java.util.Objects;

public class BlackList implements Serializable {
    private Integer mbrId;
    private Integer blackId;

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
}
