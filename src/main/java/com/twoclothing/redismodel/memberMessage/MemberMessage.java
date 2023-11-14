package com.twoclothing.redismodel.memberMessage;

import java.io.Serializable;
import java.util.Objects;

public class MemberMessage implements Serializable {

    private Integer senderId;
    private Integer receiverId;
    private String content;
    private long timestamp;
    private boolean isRead = false;

    public MemberMessage() {
    }

    public MemberMessage(Integer senderId, Integer receiverId, String content, long timestamp, boolean isRead) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = timestamp;
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "MemberMessage{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", isRead=" + isRead +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberMessage that = (MemberMessage) o;
        return timestamp == that.timestamp && isRead == that.isRead && Objects.equals(senderId, that.senderId) && Objects.equals(receiverId, that.receiverId) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderId, receiverId, content, timestamp, isRead);
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}

