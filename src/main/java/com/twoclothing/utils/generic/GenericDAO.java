package com.twoclothing.utils.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDAO<T> {

	public Serializable insert(T entity);
	
	public T getByPrimaryKey(Serializable Id);
	
	public List<T> getBy(String fieldName,Serializable value);
	
	public List<T> getAll();
	
	public List<T> getAllDescByPK();
	
	public List<T> getByQueryConditions(List<Map<String, Object>> conditionList);
	
	public boolean update(T entity);
	
	public int delete(Serializable id);
	
	public long getTotal();
}
