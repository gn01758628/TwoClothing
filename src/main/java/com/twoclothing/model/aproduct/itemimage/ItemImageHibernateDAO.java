package com.twoclothing.model.aproduct.itemimage;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ItemImageHibernateDAO implements ItemImageDAO{
	
	private SessionFactory factory;

	public ItemImageHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(ItemImage itemImage) {
		return (Integer) getSession().save(itemImage);
		
	}

	@Override
	public ItemImage getByPrimaryKey(Integer imgId) {
		 return getSession().get(ItemImage.class, imgId);
	}

	@Override
	public List<ItemImage> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemImage> getAllByItemId(Integer itemId) {
		return getSession().createQuery("from ItemImage where itemId = :itemId order by itemId", ItemImage.class)
                .setParameter("itemId", itemId)
                .list();
	}

	@Override
	public boolean update(ItemImage itemImage) {
		try {
            getSession().update(itemImage);
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	@Override
	public void delete(Integer imgId) {
		// TODO Auto-generated method stub
		
	}

}
