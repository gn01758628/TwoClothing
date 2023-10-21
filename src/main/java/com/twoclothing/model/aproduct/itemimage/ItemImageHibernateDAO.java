package com.twoclothing.model.aproduct.itemimage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ItemImageHibernateDAO implements ItemImageDAO{
	
	private final SessionFactory factory;

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
	public ItemImage getPositionImageByItemId(Integer itemId, int position) {
		return getSession().createQuery("from ItemImage where itemId = :itemId order by itemId", ItemImage.class)
				.setParameter("itemId",itemId)
				.setFirstResult(position - 1)
				.setMaxResults(1)
				.uniqueResult();
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
