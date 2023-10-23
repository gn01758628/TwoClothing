package com.twoclothing.utils.generic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Table;

public class DAOSelector {

	private static final Map<Class<?>, GenericDAO<?>> daoMap = new HashMap<>();

	private static final DAOSelector instance = new DAOSelector();

	private DAOSelector() {
		// 私有建構子，防止外部實例化
	}

	public static DAOSelector getInstance() {
		return instance;

	}

	// 印出當前Map中存放的類別及對應的DAO物件
	public static void printDaoMap() {
		System.out.println("DAO Map Contents:");
		for (Map.Entry<Class<?>, GenericDAO<?>> entry : daoMap.entrySet()) {
			Class<?> entityType = entry.getKey();
			GenericDAO<?> daoInstance = entry.getValue();
			System.out.println("Entity Type: " + entityType.getSimpleName() + ", DAO Instance: " + daoInstance);
		}
	}

	public static GenericDAO<?> getDAO(Class<?> entityType) {
		GenericDAO<?> daoInstance = daoMap.get(entityType);
		if (daoInstance == null) {
			Annotation[] annotations = entityType.getAnnotations();
	        for (Annotation annotation : annotations) {
	            if (Table.class.equals(annotation.annotationType())) {
	            	return createAndStoreDAO(entityType);
	            }
	        }
			System.out.println(entityType.getName()+"沒有關聯到資料庫不予受理");
		}
		return daoInstance;
	}

	// 以下方法不開放調用
	private static GenericDAO<?> createAndStoreDAO(Class<?> entityType) {
		String daoClassName = GenericHibernateDAOImpl.class.getName();
		try {
			@SuppressWarnings("unchecked")
			Class<GenericDAO<?>> daoClass = (Class<GenericDAO<?>>) Class.forName(daoClassName);
			Constructor<GenericDAO<?>> constructor = daoClass.getConstructor(Class.class);
			GenericDAO<?> daoInstance = constructor.newInstance(entityType);
			daoMap.put(entityType, daoInstance);
			return daoInstance;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
