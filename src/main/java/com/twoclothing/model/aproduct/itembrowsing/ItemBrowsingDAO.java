package com.twoclothing.model.aproduct.itembrowsing;

import java.sql.Timestamp;
import java.util.List;

public interface ItemBrowsingDAO {
    void insert(ItemBrowsing itemBrowsing);

    ItemBrowsing getByCompositeKey(Integer itemId, Integer mbrId);

    List<ItemBrowsing> getAll();
    
    List<ItemBrowsing> getAllByItemId(Integer itemId);

    List<ItemBrowsing> getAllByMbrId(Integer mbrId);
    
    void update(Timestamp browsingTime, Integer itemId, Integer mbrId);
}