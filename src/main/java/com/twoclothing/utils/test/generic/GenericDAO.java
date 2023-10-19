package com.twoclothing.utils.test.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDAO<T> {

	public Serializable insert(T entity);
	
	public boolean update(T entity);
	
	public int delete(Serializable id);
	
	public T getByPK(Serializable Id);
	
	public List<T> getAll();
	
	public List<T> getAllDescByPK();
	
	public List<T> getByQueryConditions(List<Map<String, Object>> conditionList);
}
