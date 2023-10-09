package com.twoclothing.web.shipsetting;

import java.util.List;

public interface ShipSettingDAO {

    void insert(ShipSetting shipSetting);

    ShipSetting getByPrimaryKey(Integer shipId);

    List<ShipSetting> getAll();

    List<ShipSetting> getAllByMbrId(Integer mbrId);

    void update(ShipSetting shipSetting);

    void delete(Integer shipId);

}
