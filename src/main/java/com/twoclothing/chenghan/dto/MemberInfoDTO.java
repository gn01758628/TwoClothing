package com.twoclothing.chenghan.dto;

import java.io.Serializable;

public class MemberInfoDTO implements Serializable {


    /**
     * 購物車數量(項目數)
     */
    private Integer carNum;

    /**
     * 未讀通知數量
     */
    private Integer noticeNum;

    /**
     * 聊天未讀訊息數量
     */
    private Integer messageNum;

    public MemberInfoDTO() {
    }

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }

    public Integer getNoticeNum() {
        return noticeNum;
    }

    public void setNoticeNum(Integer noticeNum) {
        this.noticeNum = noticeNum;
    }

    public Integer getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(Integer messageNum) {
        this.messageNum = messageNum;
    }
}
