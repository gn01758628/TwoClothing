package com.twoclothing.web.aproduct.itembrowsing;

import java.util.List;

public interface ItemBrowsingDAO {
    void insert(ItemBrowsing itemBrowsing);

    ItemBrowsing getByCompositeKey(Integer itemId, Integer mbrId);

    List<ItemBrowsing> getAll();

    List<ItemBrowsing> getAllByMbrId(Integer mbrId);

    List<ItemBrowsing> getAllByItemId(Integer itemId);
    
    void update(ItemBrowsing itemBrowsing);
}
