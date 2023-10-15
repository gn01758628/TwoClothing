package com.twoclothing.model.chatroomlog;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "chatroomlog")
public class ChatRoomLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logid", updatable = false)
    private Integer logId;

    @Column(name = "receiveid", updatable = false)
    private Integer receiveId;

    @Column(name = "sentid", updatable = false)
    private Integer sentId;

    @Column(name = "empid", updatable = false)
    private Integer empId;

    @Column(name = "message", updatable = false, nullable = false)
    private String message;

    @Column(name = "messagetime", updatable = false, nullable = false)
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
        return Objects.equals(logId, that.logId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId);
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
