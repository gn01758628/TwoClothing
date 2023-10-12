package com.twoclothing.web.withdrawrequest;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "withdrawrequest")
public class WithdrawRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wrid", insertable = false, updatable = false)
    private Integer wrId;

    @Column(name = "mbrid", updatable = false, nullable = false)
    private Integer mbrId;

    @Column(name = "amount", updatable = false, nullable = false)
    private Integer amount;

    @Column(name = "mbraccount", updatable = false, nullable = false)
    private String mbrAccount;

    @Column(name = "reqdate", updatable = false, nullable = false)
    private Timestamp reqDate;

    @Column(name = "reqstatus", insertable = false, nullable = false, columnDefinition = "TINYINT")
    private Integer reqStatus;

    @Column(name = "empid", insertable = false)
    private Integer empId;

    @Column(name = "checkdate", insertable = false)
    private Timestamp checkDate;

    @Column(name = "note", insertable = false)
    private String note;

    public WithdrawRequest() {
    }

    public WithdrawRequest(Integer wrId, Integer mbrId, Integer amount, String mbrAccount, Timestamp reqDate, Integer reqStatus, Integer empId, Timestamp checkDate, String note) {
        this.wrId = wrId;
        this.mbrId = mbrId;
        this.amount = amount;
        this.mbrAccount = mbrAccount;
        this.reqDate = reqDate;
        this.reqStatus = reqStatus;
        this.empId = empId;
        this.checkDate = checkDate;
        this.note = note;
    }

    @Override
    public String toString() {
        return "WithdrawRequest{" +
                "wrId=" + wrId +
                ", mbrId=" + mbrId +
                ", amount=" + amount +
                ", mbrAccount='" + mbrAccount + '\'' +
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
        return Objects.equals(wrId, that.wrId) && Objects.equals(mbrId, that.mbrId) && Objects.equals(amount, that.amount) && Objects.equals(mbrAccount, that.mbrAccount) && Objects.equals(reqDate, that.reqDate) && Objects.equals(reqStatus, that.reqStatus) && Objects.equals(empId, that.empId) && Objects.equals(checkDate, that.checkDate) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wrId, mbrId, amount, mbrAccount, reqDate, reqStatus, empId, checkDate, note);
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

    public String getMbrAccount() {
        return mbrAccount;
    }

    public void setMbrAccount(String mbrAccount) {
        this.mbrAccount = mbrAccount;
    }

    public Timestamp getReqDate() {
        return reqDate;
    }

    public void setReqDate(Timestamp reqDate) {
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

    public Timestamp getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Timestamp checkDate) {
        this.checkDate = checkDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
