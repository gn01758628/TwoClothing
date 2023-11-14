package com.twoclothing.redismodel.memberMessage;

import java.util.Objects;

public class LastMessageInfo {

    private Integer partnerId;
    private String content;
    private boolean isSender;
    private long timestamp;

    public LastMessageInfo() {
    }

    public LastMessageInfo(Integer partnerId, String content, boolean isSender, long timestamp) {
        this.partnerId = partnerId;
        this.content = content;
        this.isSender = isSender;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "LastMessageInfo{" +
                "partnerId=" + partnerId +
                ", content='" + content + '\'' +
                ", isSender=" + isSender +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LastMessageInfo that = (LastMessageInfo) o;
        return isSender == that.isSender && timestamp == that.timestamp && Objects.equals(partnerId, that.partnerId) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partnerId, content, isSender, timestamp);
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSender() {
        return isSender;
    }

    public void setSender(boolean sender) {
        isSender = sender;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
