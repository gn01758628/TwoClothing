package com.twoclothing.redismodel.notice;

import java.io.Serializable;
import java.util.Objects;

public class Notice implements Serializable {

    // 通知類別
    private String type;

    // 通知標題
    private String head;

    // 通知內容
    private String content;

    // 點下去可以前往的連結(不可以沒有)
    private String link;

    // 圖片的Servlet連結連結(不可以沒有)
    private String imageLink;

    // 是否讀過訊息,預設為false
    private boolean read = false;

    // 儲存該通知在redis裡的Id
    private String noticeId;

    // 訊息發布的時間
    private Long timestamp;

    public Notice() {
    }

    @Override
    public String toString() {
        return "Notice{" +
                "type='" + type + '\'' +
                ", head='" + head + '\'' +
                ", content='" + content + '\'' +
                ", link='" + link + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", read=" + read +
                ", noticeId='" + noticeId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notice notice = (Notice) o;
        return read == notice.read && Objects.equals(type, notice.type) && Objects.equals(head, notice.head) && Objects.equals(content, notice.content) && Objects.equals(link, notice.link) && Objects.equals(imageLink, notice.imageLink) && Objects.equals(noticeId, notice.noticeId) && Objects.equals(timestamp, notice.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, head, content, link, imageLink, read, noticeId, timestamp);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
