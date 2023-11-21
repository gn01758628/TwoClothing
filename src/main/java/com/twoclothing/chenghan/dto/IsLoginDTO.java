package com.twoclothing.chenghan.dto;

import java.io.Serializable;
import java.util.Objects;

public class IsLoginDTO implements Serializable {
    private boolean isLogin;

    private String mbrStatus;

    private String mbrAccount;

    private Integer mbrPoint;

    private Integer balance;

    private Integer mbrId;

    public IsLoginDTO() {
    }

    public IsLoginDTO(boolean isLogin, String mbrStatus, String mbrAccount, Integer mbrPoint, Integer balance, Integer mbrId) {
        this.isLogin = isLogin;
        this.mbrStatus = mbrStatus;
        this.mbrAccount = mbrAccount;
        this.mbrPoint = mbrPoint;
        this.balance = balance;
        this.mbrId = mbrId;
    }

    @Override
    public String toString() {
        return "IsLoginDTO{" +
                "isLogin=" + isLogin +
                ", mbrStatus='" + mbrStatus + '\'' +
                ", mbrAccount='" + mbrAccount + '\'' +
                ", mbrPoint=" + mbrPoint +
                ", balance=" + balance +
                ", mbrId=" + mbrId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IsLoginDTO that = (IsLoginDTO) o;
        return isLogin == that.isLogin && Objects.equals(mbrStatus, that.mbrStatus) && Objects.equals(mbrAccount, that.mbrAccount) && Objects.equals(mbrPoint, that.mbrPoint) && Objects.equals(balance, that.balance) && Objects.equals(mbrId, that.mbrId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isLogin, mbrStatus, mbrAccount, mbrPoint, balance, mbrId);
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getMbrStatus() {
        return mbrStatus;
    }

    public void setMbrStatus(String mbrStatus) {
        this.mbrStatus = mbrStatus;
    }

    public String getMbrAccount() {
        return mbrAccount;
    }

    public void setMbrAccount(String mbrAccount) {
        this.mbrAccount = mbrAccount;
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

    public Integer getMbrId() {
        return mbrId;
    }

    public void setMbrId(Integer mbrId) {
        this.mbrId = mbrId;
    }
}
