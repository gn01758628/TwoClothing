package com.twoclothing.web.shipsetting;

import java.io.Serializable;
import java.util.Objects;

public class ShipSetting implements Serializable {
    private Integer shipId;
    private Integer mbrId;
    private String receiveName;
    private String receivePhone;
    private String receiveAddress;

    public ShipSetting() {
    }

    public ShipSetting(Integer shipId, Integer mbrId, String receiveName, String receivePhone, String receiveAddress) {
        this.shipId = shipId;
        this.mbrId = mbrId;
        this.receiveName = receiveName;
        this.receivePhone = receivePhone;
        this.receiveAddress = receiveAddress;
    }

    @Override
    public String toString() {
        return "ShipSetting{" +
                "shipId=" + shipId +
                ", mbrId=" + mbrId +
                ", receiveName='" + receiveName + '\'' +
                ", receivePhone='" + receivePhone + '\'' +
                ", receiveAddress='" + receiveAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipSetting that = (ShipSetting) o;
        return Objects.equals(shipId, that.shipId) && Objects.equals(mbrId, that.mbrId) && Objects.equals(receiveName, that.receiveName) && Objects.equals(receivePhone, that.receivePhone) && Objects.equals(receiveAddress, that.receiveAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipId, mbrId, receiveName, receivePhone, receiveAddress);
    }

    public Integer getShipId() {
        return shipId;
    }

    public void setShipId(Integer shipId) {
        this.shipId = shipId;
    }

    public Integer getMbrId() {
        return mbrId;
    }

    public void setMbrId(Integer mbrId) {
        this.mbrId = mbrId;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }
}
