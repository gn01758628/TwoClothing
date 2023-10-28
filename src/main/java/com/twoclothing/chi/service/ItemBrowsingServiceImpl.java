package com.twoclothing.chi.service;

import java.sql.Timestamp;
import java.util.List;

import com.twoclothing.model.aproduct.itembrowsing.ItemBrowsing;
import com.twoclothing.model.aproduct.itembrowsing.ItemBrowsingDAO;
import com.twoclothing.model.aproduct.itembrowsing.ItemBrowsingHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

public class ItemBrowsingServiceImpl implements ItemBrowsingService {
	private ItemBrowsingDAO dao;

	public ItemBrowsingServiceImpl() {
		dao = new ItemBrowsingHibernateDAO(HibernateUtil.getSessionFactory());
	}

	@Override
	public ItemBrowsing addItemBrowsing(ItemBrowsing itemBrowsing) {
		dao.insert(itemBrowsing);
		return itemBrowsing;
	}

	@Override
	public List<ItemBrowsing> getAllByMbrId(Integer mbrId, int currentPage) {
		return dao.getAllByMbrId(mbrId, currentPage);
	}

	@Override
	public int getPageTotal(Integer mbrId) {
		long total = dao.getTotal(mbrId);
		int pageQty = (int) (total % 10 == 0 ? (total / 10) : (total / 10 + 1));
		return pageQty;
	}

	@Override
	public ItemBrowsing getByPrimaryKey(Integer itemId, Integer mbrId) {
		ItemBrowsing itemBrowsing = dao.getByCompositeKey(itemId, mbrId);
		return itemBrowsing;
	}

	@Override
	public ItemBrowsing updateItemBrowsing(Integer itemId, Integer mbrId, Timestamp browsingTime) {
		ItemBrowsing itemBrowsing = dao.getByCompositeKey(itemId, mbrId);
		if (itemBrowsing != null) {
			itemBrowsing.setBrowsingTime(browsingTime);
			dao.update(itemBrowsing);
		}
		return itemBrowsing;
	}
}
