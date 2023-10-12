package com.twoclothing.web.members;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

public class Members implements Serializable {
    private Integer mbrId;
    private String mbrName;
    private String email;
    private String pswdHash;
    private Integer mbrStatus;
    private byte[] avatar = null;
    private byte[] shopImg01 = null;
    private byte[] shopImg02 = null;
    private Integer mbrPoint = 0;
    private Integer balance = 0;
    private Integer buyStar = 0;
    private Integer buyRating = 0;
    private Integer sellStar = 0;
    private Integer sellRating = 0;
    private Timestamp lastLogin = null;
    private Integer sellScore = 10;
    private Integer buyScore = 10;

    public Members() {
    }

    public Members(String email, String pswdHash, Integer mbrStatus) {
        this.email = email;
        this.pswdHash = pswdHash;
        this.mbrStatus = mbrStatus;
    }

    @Override
    public String toString() {
        return "Members{" +
                "mbrId=" + mbrId +
                ", mbrName='" + mbrName + '\'' +
                ", email='" + email + '\'' +
                ", pswdHash='" + pswdHash + '\'' +
                ", mbrStatus=" + mbrStatus +
                ", avatar=" + Arrays.toString(avatar) +
                ", shopImg01=" + Arrays.toString(shopImg01) +
                ", shopImg02=" + Arrays.toString(shopImg02) +
                ", mbrPoint=" + mbrPoint +
                ", balance=" + balance +
                ", buyStar=" + buyStar +
                ", buyRating=" + buyRating +
                ", sellStar=" + sellStar +
                ", sellRating=" + sellRating +
                ", lastLogin=" + lastLogin +
                ", sellScore=" + sellScore +
                ", buyScore=" + buyScore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Members members = (Members) o;
        return Objects.equals(mbrId, members.mbrId) && Objects.equals(mbrName, members.mbrName) && Objects.equals(email, members.email) && Objects.equals(pswdHash, members.pswdHash) && Objects.equals(mbrStatus, members.mbrStatus) && Arrays.equals(avatar, members.avatar) && Arrays.equals(shopImg01, members.shopImg01) && Arrays.equals(shopImg02, members.shopImg02) && Objects.equals(mbrPoint, members.mbrPoint) && Objects.equals(balance, members.balance) && Objects.equals(buyStar, members.buyStar) && Objects.equals(buyRating, members.buyRating) && Objects.equals(sellStar, members.sellStar) && Objects.equals(sellRating, members.sellRating) && Objects.equals(lastLogin, members.lastLogin) && Objects.equals(sellScore, members.sellScore) && Objects.equals(buyScore, members.buyScore);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(mbrId, mbrName, email, pswdHash, mbrStatus, mbrPoint, balance, buyStar, buyRating, sellStar, sellRating, lastLogin, sellScore, buyScore);
        result = 31 * result + Arrays.hashCode(avatar);
        result = 31 * result + Arrays.hashCode(shopImg01);
        result = 31 * result + Arrays.hashCode(shopImg02);
        return result;
    }

    public Integer getMbrId() {
        return mbrId;
    }

    public void setMbrId(Integer mbrId) {
        this.mbrId = mbrId;
    }

    public String getMbrName() {
        return mbrName;
    }

    public void setMbrName(String mbrName) {
        this.mbrName = mbrName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPswdHash() {
        return pswdHash;
    }

    public void setPswdHash(String pswdHash) {
        this.pswdHash = pswdHash;
    }

    public Integer getMbrStatus() {
        return mbrStatus;
    }

    public void setMbrStatus(Integer mbrStatus) {
        this.mbrStatus = mbrStatus;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public byte[] getShopImg01() {
        return shopImg01;
    }

    public void setShopImg01(byte[] shopImg01) {
        this.shopImg01 = shopImg01;
    }

    public byte[] getShopImg02() {
        return shopImg02;
    }

    public void setShopImg02(byte[] shopImg02) {
        this.shopImg02 = shopImg02;
    }

    public Integer getMbrPoint() {
        return mbrPoint;
    }

    public void setMbrPoint(Integer mbrPoint) {
        this.mbrPoint = mbrPoint;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getBuyStar() {
        return buyStar;
    }

    public void setBuyStar(Integer buyStar) {
        this.buyStar = buyStar;
    }

    public Integer getBuyRating() {
        return buyRating;
    }

    public void setBuyRating(Integer buyRating) {
        this.buyRating = buyRating;
    }

    public Integer getSellStar() {
        return sellStar;
    }

    public void setSellStar(Integer sellStar) {
        this.sellStar = sellStar;
    }

    public Integer getSellRating() {
        return sellRating;
    }

    public void setSellRating(Integer sellRating) {
        this.sellRating = sellRating;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getSellScore() {
        return sellScore;
    }

    public void setSellScore(Integer sellScore) {
        this.sellScore = sellScore;
    }

    public Integer getBuyScore() {
        return buyScore;
    }

    public void setBuyScore(Integer buyScore) {
        this.buyScore = buyScore;
    }
}