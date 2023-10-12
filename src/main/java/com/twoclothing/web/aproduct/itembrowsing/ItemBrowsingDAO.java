package com.twoclothing.web.aproduct.itembrowsing;

import java.util.List;

public interface ItemBrowsingDAO {

    public void insert(ItemBrowsing itemBrowsing);

    public ItemBrowsing getByCompositeKey(Integer itemId, Integer mbrId);

    public List<ItemBrowsing> getAll();

    public List<ItemBrowsing> getAllByMbrId(Integer mbrId);

    public List<ItemBrowsing> getAllByItemId(Integer itemId);
}
