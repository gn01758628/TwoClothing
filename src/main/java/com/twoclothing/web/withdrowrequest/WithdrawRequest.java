package com.twoclothing.web.withdrowrequest;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class WithdrawRequest implements Serializable {
    private Integer wrId;
    private Integer mbrId;
    private Integer amount;
    private String account;
    private LocalDateTime reqDate;
    private Integer reqStatus;
    private Integer empId;
    private LocalDateTime checkDate;
    private String note;

    public WithdrawRequest() {
    }

    @Override
    public String toString() {
        return "WithdrawRequest{" +
                "wrId=" + wrId +
                ", mbrId=" + mbrId +
                ", amount=" + amount +
                ", account='" + account + '\'' +
                ", reqDate=" + reqDate +
                ", reqStatus=" + reqStatus +
                ", empId=" + empId +
                ", checkDate=" + checkDate +
                ", note='" + note + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WithdrawRequest that = (WithdrawRequest) o;
        return Objects.equals(wrId, that.wrId) && Objects.equals(mbrId, that.mbrId) && Objects.equals(amount, that.amount) && Objects.equals(account, that.account) && Objects.equals(reqDate, that.reqDate) && Objects.equals(reqStatus, that.reqStatus) && Objects.equals(empId, that.empId) && Objects.equals(checkDate, that.checkDate) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wrId, mbrId, amount, account, reqDate, reqStatus, empId, checkDate, note);
    }

    public Integer getWrId() {
        return wrId;
    }

    public void setWrId(Integer wrId) {
        this.wrId = wrId;
    }

    public Integer getMbrId() {
        return mbrId;
    }

    public void setMbrId(Integer mbrId) {
        this.mbrId = mbrId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public LocalDateTime getReqDate() {
        return reqDate;
    }

    public void setReqDate(LocalDateTime reqDate) {
        this.reqDate = reqDate;
    }

    public Integer getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Integer reqStatus) {
        this.reqStatus = reqStatus;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public LocalDateTime getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDateTime checkDate) {
        this.checkDate = checkDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
