package com.twoclothing.chenghan;

import java.io.Serializable;

public class MemberInfoDTO implements Serializable {


    /**
     * 購物車數量(項目數)
     */
    private Integer carNum;

    public MemberInfoDTO() {
    }

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }
}
