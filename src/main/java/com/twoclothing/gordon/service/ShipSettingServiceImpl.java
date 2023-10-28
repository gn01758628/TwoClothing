package com.twoclothing.gordon.service;

import java.util.List;

import javax.transaction.Transactional;

import com.twoclothing.model.members.Members;
import com.twoclothing.model.shipsetting.ShipSetting;
import com.twoclothing.model.shipsetting.ShipSettingDAO;
import com.twoclothing.model.shipsetting.ShipSettingHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class ShipSettingServiceImpl implements ShipSettingService{
	private ShipSettingDAO dao;
	
	public ShipSettingServiceImpl() {
		dao = new ShipSettingHibernateDAO(HibernateUtil.getSessionFactory());
	}
	
	@Transactional
	@Override
	public ShipSetting addShipSetting(Integer mbrId, String receiveName, String receivePhone, String receiveAddress ) {
		ShipSetting shipSetting = new ShipSetting();
		
		shipSetting.setMbrId(mbrId);
		shipSetting.setReceiveName(receiveName);
		shipSetting.setReceivePhone(receivePhone);
		shipSetting.setReceiveAddress(receiveAddress);
		dao.insert(shipSetting);
		return shipSetting;
	}
	@Transactional
	@Override
	public List<ShipSetting> getAll() {
		return dao.getAll();
	}
	@Transactional
	@Override
	public ShipSetting getByPrimaryKey(Integer shipId) {
		return dao.getByPrimaryKey(shipId);
	}
	@Transactional
	@Override
	public List<ShipSetting> getByMbrId(Integer mbrId) {
		List<ShipSetting> shipSetting =  dao.getAllByMbrId(mbrId);
		if(shipSetting != null) {
			return shipSetting;
			}
		return null;
	}
	@Transactional
	@Override
	public ShipSetting updateShipSetting(Integer shipId, Integer mbrId, String receiveName, String receivePhone, String receiveAddress) {
		ShipSetting shipSetting = new ShipSetting();
		
		shipSetting.setShipId(shipId);
		shipSetting.setMbrId(mbrId);
		shipSetting.setReceiveName(receiveName);
		shipSetting.setReceivePhone(receivePhone);
		shipSetting.setReceiveAddress(receiveAddress);
		dao.update(shipSetting);
	
		return shipSetting ;
	}
	@Transactional
	@Override
	public Integer deleteShipSetting(Integer shipId) {
		return dao.delete(shipId);
	}

}
