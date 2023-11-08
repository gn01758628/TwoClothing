package com.twoclothing.chi.service;

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
	public void addItemBrowsing(ItemBrowsing itemBrowsing) {
		dao.insert(itemBrowsing);
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
	public void updateItemBrowsing(ItemBrowsing itemBrowsing) {
		dao.update(itemBrowsing);
	}
}
