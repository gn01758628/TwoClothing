package com.twoclothing.utils.generic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.twoclothing.utils.HibernateUtil;

import org.apache.commons.lang3.reflect.FieldUtils;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class GenericHibernateDAOImpl<T> implements GenericDAO<T> {
	private final Class<T> type;
	private final String className;
	private final Field[] fields;
	private final String tableName;
	private final Map<String, Class<?>> fieldMap = new HashMap<>();
	private final Map<String, String> PKMap = new LinkedHashMap<>();

	public GenericHibernateDAOImpl(Class<T> type) {
		this.type = type;
		this.className = type.getSimpleName();
		fields = type.getDeclaredFields();
		initializeFieldMap();
		this.tableName = initializeTableName();

	}

	public GenericHibernateDAOImpl(Class<T> type, SessionFactory factory) {
		this.type = type;
		this.className = type.getSimpleName();
		fields = type.getDeclaredFields();
		initializeFieldMap();
		this.tableName = initializeTableName();

	}

	private void initializeFieldMap() {
		for (Field field : fields) {
			field.setAccessible(true);
			fieldMap.put(field.getName(), field.getType());
			if (field.isAnnotationPresent(Id.class)) {
				Annotation annotation = field.getAnnotation(Id.class);
				String annotationName = annotation.annotationType().getSimpleName();
				PKMap.put(field.getName(), annotationName);
			} else if (field.isAnnotationPresent(EmbeddedId.class)) {
				Annotation annotation = field.getAnnotation(EmbeddedId.class);
				String annotationName = annotation.annotationType().getSimpleName();
				PKMap.put(field.getName(), annotationName);

				// 取得@EmbeddedId標註的屬性名稱
				String embeddedIdFieldName = field.getName();

				// 使用反射取得EmbeddedId中的屬性名稱
				Field[] embeddedIdFields = field.getType().getDeclaredFields();
				for (Field EmField : embeddedIdFields) {
					EmField.setAccessible(true);
					if (EmField.isAnnotationPresent(Column.class)) {
						String fieldName = EmField.getName();
						// 加上@EmbeddedId對應的變數名稱前綴
						String prefixedFieldName = embeddedIdFieldName + "." + fieldName;
						PKMap.put(prefixedFieldName, "Id");
					}

				}
			}
		}
	}

	private String initializeTableName() {
		Table tableAnnotation = type.getAnnotation(Table.class);
		String table;
		if (tableAnnotation != null) {
			table = tableAnnotation.name();
		} else {
			table = null;
		}
		return table;
	}

	// session執行序不安全,由各方法內部調用
	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	// ========================= insert =========================
	@Override
	public Serializable insert(T entity) {
		return getSession().save(entity);
	}

	// ========================= query =========================

	// 查詢By PK
	@Override
	public T getByPrimaryKey(Serializable id) {
		return getSession().get(type, id);
	}

	// 查詢By 欄位,值
	@Override
	public List<T> getBy(String fieldName, Serializable value) {
		Class<?> fieldType = fieldMap.get(fieldName);
		String hql;
		String alias;
		if (fieldName.contains(".")) {
			int dotIndex = fieldName.indexOf(".");
			alias = fieldName.substring(dotIndex + 1);
		} else {
			alias = fieldName;
		}
		if (fieldType != null && fieldType.isAssignableFrom(String.class)) {
			// 如果 fieldType 是 String 或者 String 的子類
			hql = "from " + className + " where " + fieldName + " like :" + alias;
		} else {
			// 如果 fieldType 不是 String 或者為 null
			hql = "from " + className + " where " + fieldName + " = :" + alias;
		}
		System.out.println(hql);

		Query query = getSession().createQuery(hql);
		query.setParameter(alias, value);
		return query.getResultList();
	}

	// 查詢全部 OrderBy PK
	@Override
	public List<T> getAll() {
		String orderBy;
		Map<String, String> orderByMap = new LinkedHashMap<>();

		for (Map.Entry<String, String> entry : PKMap.entrySet()) {
			if ("Id".equals(entry.getValue())) {
				orderByMap.put(entry.getKey(), "asc");
			}
		}

		orderBy = buildOrderBy(orderByMap);
		return getSession().createQuery("from " + className + orderBy, type).list();
	}

	// 查詢全部 OrderBy PK desc
	@Override
	public List<T> getAllDescByPK() {
		String orderBy;
		Map<String, String> orderByMap = new LinkedHashMap<>();

		for (Map.Entry<String, String> entry : PKMap.entrySet()) {
			if ("Id".equals(entry.getValue())) {
				orderByMap.put(entry.getKey(), "desc");
			}
		}

		orderBy = buildOrderBy(orderByMap);
		return getSession().createQuery("from " + className + orderBy, type).list();
	}

	// 複合查詢
	@Override
	public List<T> getByQueryConditions(List<Map<String, Object>> conditionList) {
		Session session = getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(type);
		Root<T> root = criteria.from(type);

		// 初始化最终的Predicate為null
		Predicate finalPredicate = null;
		for (Map<String, Object> conditionMap : conditionList) {
			String fieldName = (String) conditionMap.get("fieldName");
			String operator = (String) conditionMap.get("operator");
			Object value = conditionMap.get("value");
			String logicalOperator = (String) conditionMap.get("logicalOperator");

			// 根據fieldName、operator和value生成Predicate
			Predicate predicate = buildPredicate(fieldName, operator, value, builder, root);

			// 判斷邏輯運算符(AND OR)
			if (finalPredicate == null) {
				finalPredicate = predicate;

			} else {
				if ("and".equalsIgnoreCase(logicalOperator)) {
					finalPredicate = builder.and(finalPredicate, predicate);

				} else if ("or".equalsIgnoreCase(logicalOperator)) {
					finalPredicate = builder.or(finalPredicate, predicate);

				}
			}
		}

		// 將最終的Predicate應用到查詢
		if (finalPredicate != null) {
			criteria.where(finalPredicate);
		} else {
			return getAll();
		}

		List<T> resultList = session.createQuery(criteria).getResultList();
		return resultList;
	}

	// ========================= update =========================
	@Override
	public boolean update(T entity) {
		try {
			getSession().update(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// ========================= delete =========================
	@Override
	public int delete(Serializable id) {
		T entity = getSession().get(type, id);
		if (entity != null) {
			getSession().delete(entity);
			return 1;
		} else {
			return -1;
		}
	}

	// 取得資料總筆數
	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from " + className, Long.class).uniqueResult();
	}

//================================以下是非公開方法================================
	private String buildOrderBy(Map<String, String> orderFields) {
		if (orderFields == null || orderFields.isEmpty()) {
			return "";
		}

		StringBuilder orderByClause = new StringBuilder(" ORDER BY ");
		for (Map.Entry<String, String> entry : orderFields.entrySet()) {
			String fieldName = entry.getKey();
			String sortOrder = entry.getValue();

			orderByClause.append(fieldName).append(" ").append(sortOrder).append(", ");
		}

		// 删除末尾的逗號和空格
		orderByClause.delete(orderByClause.length() - 2, orderByClause.length());

		return orderByClause.toString();
	}

	private Predicate buildPredicate(String fieldName, String operator, Object value, CriteriaBuilder builder,
			Root<T> root) {
		switch (operator.toLowerCase()) {
		case "like":
			String stringValue = value.toString();
			if (!stringValue.contains("%")) {
				stringValue = "%" + stringValue + "%";
			}
			return builder.like(root.get(fieldName), stringValue);

		case "=":
			return builder.equal(root.get(fieldName), value);

		case ">":
			return builder.greaterThan(root.get(fieldName), (Comparable) value);

		case "<":
			return builder.lessThan(root.get(fieldName), (Comparable) value);

		case ">=":
			return builder.greaterThanOrEqualTo(root.get(fieldName), (Comparable) value);

		case "<=":
			return builder.lessThanOrEqualTo(root.get(fieldName), (Comparable) value);

		case "between":
			if (value instanceof Object[]) {
				Object[] values = (Object[]) value;
				if (values.length == 2) {
					return builder.between(root.get(fieldName), (Comparable) values[0], (Comparable) values[1]);
				}
			}
			// 如果不符合條件，可以拋出異常或返回一個默認的Predicate
			throw new IllegalArgumentException("Invalid 'between' operator values");

		default:
			// 如果運算符無法識別，可以拋出異常或返回一個默認的Predicate
			throw new IllegalArgumentException("Unsupported operator: " + operator);
		}
	}

}
