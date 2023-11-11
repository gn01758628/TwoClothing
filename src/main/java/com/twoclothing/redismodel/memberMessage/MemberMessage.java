package com.twoclothing.redismodel.memberMessage;

import java.io.Serializable;
import java.util.Objects;

public class MemberMessage implements Serializable {

    private Integer senderId;
    private Integer receiverId;
    private String content;
    private long timestamp;
    private MessageStatus status = MessageStatus.UNREAD;

    // 內部枚舉類型
    public enum MessageStatus {
        READ("已讀"),
        UNREAD("未讀");

        private final String status;

        MessageStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return this.status;
        }
    }

    public MemberMessage() {
    }

    public MemberMessage(Integer senderId, Integer receiverId, String content, long timestamp, MessageStatus status) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = timestamp;
        this.status = status;
    }

    @Override
    public String toString() {
        return "MemberMessage{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberMessage message = (MemberMessage) o;
        return timestamp == message.timestamp && Objects.equals(senderId, message.senderId) && Objects.equals(receiverId, message.receiverId) && Objects.equals(content, message.content) && status == message.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderId, receiverId, content, timestamp, status);
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

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}

