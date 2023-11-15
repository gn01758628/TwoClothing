package com.twoclothing.gordon.service;

import java.util.List;

import com.twoclothing.model.shipsetting.ShipSetting;

public interface ShipSettingService {
	
	ShipSetting addShipSetting(ShipSetting shipSetting);
	
	List<ShipSetting> getAll();
	
	ShipSetting getByPrimaryKey(Integer shipId);
	
	List<ShipSetting> getByMbrId(Integer mbrId);
	
	public ShipSetting updateShipSetting(ShipSetting shipSetting);
	
	Integer deleteShipSetting(Integer shipId);
}
