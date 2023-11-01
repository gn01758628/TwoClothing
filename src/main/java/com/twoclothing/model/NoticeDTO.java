package com.twoclothing.model;

public class NoticeDTO {

    // 消息類別
    private String type;

    // 消息內容
    private String content;

    // 連結(點下去可以前往的地方,可以沒有)
    private String link;

    // 圖片的Servlet連結連結(可以沒有)
    private String imageLink;

    // 是否讀過訊息,預設為false
    private boolean read = false;

    public NoticeDTO() {
    }

    public NoticeDTO(String type, String content, String link, String imageLink, boolean read) {
        this.type = type;
        this.content = content;
        this.link = link;
        this.imageLink = imageLink;
        this.read = read;
    }

    @Override
    public String toString() {
        return "NoticeDTO{" +
                "type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", link='" + link + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", read=" + read +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
