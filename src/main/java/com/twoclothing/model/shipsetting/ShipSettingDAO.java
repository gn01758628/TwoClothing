package com.twoclothing.model.shipsetting;

import java.util.List;

public interface ShipSettingDAO {

    int insert(ShipSetting shipSetting);

    ShipSetting getByPrimaryKey(Integer shipId);

    List<ShipSetting> getAll();

    List<ShipSetting> getAllByMbrId(Integer mbrId);

    int update(ShipSetting shipSetting);

    int delete(Integer shipId);

}
