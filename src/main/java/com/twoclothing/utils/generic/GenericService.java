package com.twoclothing.utils.generic;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class GenericService {

	private static final GenericService instance = new GenericService();

	private GenericService() {
		// 私有建構子，防止外部實例化
	}

	public static GenericService getInstance() {
		return instance;
	}

	// ========================= insert =========================

	public <T> Serializable insert(T entity) {
		GenericDAO dao = DAOSelector.getDAO(entity.getClass());
		return dao.insert(entity);
	}

	// ========================= update =========================

	public <T> boolean update(T entity) {
		GenericDAO dao = DAOSelector.getDAO(entity.getClass());
		return dao.update(entity);
	}

	// ========================= delete =========================

	public <T> int delete(Class<T> type, Serializable id) {
		GenericDAO dao = DAOSelector.getDAO(type);
		return dao.delete(id);
	}

	// ========================= query =========================

	// 查詢By PK
	public <T> T getByPK(Class<T> type, Serializable Id) {
		GenericDAO dao = DAOSelector.getDAO(type);
		return (T) dao.getByPK(Id);
	}

	public <T> List<T> getBy(Class<T> type,String fieldName, Serializable value) {
		GenericDAO dao = DAOSelector.getDAO(type);
		return dao.getBy(fieldName, value);
	}

	// 查詢全部 OrderBy PK
	public <T> List<T> getAll(Class<T> type) {
		GenericDAO dao = DAOSelector.getDAO(type);
		return dao.getAll();
	}

	// 查詢全部 OrderBy PK desc
	public <T> List<T> getAllDescByPK(Class<T> type) {
		GenericDAO dao = DAOSelector.getDAO(type);
		return dao.getAllDescByPK();
	}

	// 複合查詢
	public <T> List<T> getByQueryConditions(Class<T> type, List<Map<String, Object>> conditionList) {
		GenericDAO dao = DAOSelector.getDAO(type);
		return dao.getByQueryConditions(conditionList);
	}

	// 取得資料總筆數
	public <T> long getTotal(Class<T> type) {
		GenericDAO dao = DAOSelector.getDAO(type);
		return dao.getTotal();
	}

}
