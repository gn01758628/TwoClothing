package com.twoclothing.gordon.service;

import java.util.List;

import com.twoclothing.model.shipsetting.ShipSetting;

public interface ShipSettingService {
	
	ShipSetting addShipSetting(Integer mbrId, String receiveName, String receivePhone, String receiveAddress);
	
	List<ShipSetting> getAll();
	
	ShipSetting getByPrimaryKey(Integer shipId);
	
	List<ShipSetting> getByMbrId(Integer mbrId);
	
	public ShipSetting updateShipSetting(Integer shipId, Integer mbrId, String receiveName, String receivePhone, String receiveAddress);
	
	Integer deleteShipSetting(Integer shipId);
}
