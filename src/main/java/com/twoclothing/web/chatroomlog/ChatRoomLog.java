package com.twoclothing.web.chatroomlog;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class ChatRoomLog implements Serializable {
    private Integer logId;
    private Integer receiveId;
    private Integer sentId;
    private Integer empId;
    private String message;
    private Timestamp messageTime;

    public ChatRoomLog() {
    }

    public ChatRoomLog(Integer logId, Integer receiveId, Integer sentId, Integer empId, String message, Timestamp messageTime) {
        this.logId = logId;
        this.receiveId = receiveId;
        this.sentId = sentId;
        this.empId = empId;
        this.message = message;
        this.messageTime = messageTime;
    }

    @Override
    public String toString() {
        return "ChatRoomLog{" +
                "logId=" + logId +
                ", receiveId=" + receiveId +
                ", sentId=" + sentId +
                ", empId=" + empId +
                ", message='" + message + '\'' +
                ", messageTime=" + messageTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRoomLog that = (ChatRoomLog) o;
        return Objects.equals(logId, that.logId) && Objects.equals(receiveId, that.receiveId) && Objects.equals(sentId, that.sentId) && Objects.equals(empId, that.empId) && Objects.equals(message, that.message) && Objects.equals(messageTime, that.messageTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, receiveId, sentId, empId, message, messageTime);
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    public Integer getSentId() {
        return sentId;
    }

    public void setSentId(Integer sentId) {
        this.sentId = sentId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }
}
