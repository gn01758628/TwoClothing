package com.twoclothing.utils.test.generic;

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

	private Map<Class<?>, GenerciHibernateDAO<?>> daoMap = new HashMap<>();

	private static final GenericService instance = new GenericService();

	private GenericService() {
		// 私有建構子，防止外部實例化
	}

	public static GenericService getInstance() {
		return instance;

	}

	// ========================= insert =========================

	public <T> Serializable insert(T entity) {
		GenerciHibernateDAO dao = getDAO(entity.getClass());
		return dao.insert(entity);
	}

	// ========================= update =========================

	public <T> boolean update(T entity) {
		GenerciHibernateDAO dao = getDAO(entity.getClass());
		return dao.update(entity);
	}

	// ========================= delete =========================
	
	public <T> int delete(Class<T> type,Serializable id) {
		GenerciHibernateDAO dao = getDAO(type);
		return dao.delete(id);
	}
	
	// ========================= query  =========================

	public <T> T getByPK(Class<T> type, Serializable Id) {
		GenerciHibernateDAO dao = getDAO(type);
		return (T) dao.getByPK(Id);
	}

	// 查詢全部 OrderBy PK
	public <T> List<T> getAll(Class<T> type) {
		GenerciHibernateDAO dao = getDAO(type);
		return dao.getAll();
	}

	// 查詢全部 OrderBy PK desc
	public <T> List<T> getAllDescByPK(Class<T> type) {
		GenerciHibernateDAO dao = getDAO(type);
		return dao.getAllDescByPK();
	}

	// 複合查詢
	public <T> List<T> getByQueryConditions(Class<T> type, List<Map<String, Object>> conditionList) {
		GenerciHibernateDAO dao = getDAO(type);
		return dao.getByQueryConditions(conditionList);
	}

	// 印出當前Map中存放的類別及對應的DAO物件
	public void printDaoMap() {
		System.out.println("DAO Map Contents:");
		for (Map.Entry<Class<?>, GenerciHibernateDAO<?>> entry : daoMap.entrySet()) {
			Class<?> entityType = entry.getKey();
			GenerciHibernateDAO<?> daoInstance = entry.getValue();
			System.out.println("Entity Type: " + entityType.getSimpleName() + ", DAO Instance: " + daoInstance);
		}
	}

	// 以下方法不開放調用
	private GenerciHibernateDAO<?> getDAO(Class<?> entityType) {
		GenerciHibernateDAO<?> daoInstance = daoMap.get(entityType);
		if (daoInstance == null) {
			daoInstance = createAndStoreDAO(entityType);
		}
		return daoInstance;
	}

	private GenerciHibernateDAO<?> createAndStoreDAO(Class<?> entityType) {
		String daoClassName = GenerciHibernateDAO.class.getName();
		try {
			@SuppressWarnings("unchecked")
			Class<GenerciHibernateDAO<?>> daoClass = (Class<GenerciHibernateDAO<?>>) Class.forName(daoClassName);
			Constructor<GenerciHibernateDAO<?>> constructor = daoClass.getConstructor(Class.class);
			GenerciHibernateDAO<?> daoInstance = constructor.newInstance(entityType);
			daoMap.put(entityType, daoInstance);
			return daoInstance;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
