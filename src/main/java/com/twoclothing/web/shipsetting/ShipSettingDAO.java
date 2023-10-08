package com.twoclothing.web.shipsetting;

import java.util.List;

public interface ShipSettingDAO {

    public void insert(ShipSetting shipSetting);

    public ShipSetting getByPrimaryKey(Integer shipId);

    public List<ShipSetting> getAll();

    public List<ShipSetting> getAllByMbrId(Integer mbrId);

    public void update(ShipSetting shipSetting);

    public void delete(Integer shipId);

}
