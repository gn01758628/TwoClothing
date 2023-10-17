package com.twoclothing.utils.test.generic;

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

public class GenerciHibernateDAO<T> {
	private final Class<T> type;
	private final String className;
	private final Field[] fields;
	private final String tableName;
	private final Map<String, Class<?>> fieldMap = new HashMap<>();
	private final Map<String, String> PKMap = new LinkedHashMap<>();

	private final SessionFactory factory;

	public GenerciHibernateDAO(Class<T> type) {
		this.type = type;
		this.className = type.getSimpleName();
		fields = type.getDeclaredFields();
		initializeFieldMap();
		this.tableName = initializeTableName();
		this.factory = HibernateUtil.getSessionFactory();


		// TODO Auto-generated constructor stub
	}

	public GenerciHibernateDAO(Class<T> type, SessionFactory factory) {
		this.type = type;
		this.className = type.getSimpleName();
		fields = type.getDeclaredFields();
		initializeFieldMap();
		this.tableName = initializeTableName();
		this.factory = factory;
		System.out.println(getSession());
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
		return factory.getCurrentSession();
	}

	public T getByPK(Serializable Id) {
		return getSession().get(type, Id);
	}

	public List<T> getAll() {
		String orderBy;
		Map<String, String> orderByMap = new LinkedHashMap<>();

		for (Map.Entry<String, String> entry : PKMap.entrySet()) {
			if ("Id".equals(entry.getValue())) {
				orderByMap.put(entry.getKey(), "desc");
			}
		}

		orderBy = buildOrderBy(orderByMap);
		System.out.println("from " + tableName + orderBy);
		return getSession().createQuery("from " + className + orderBy, type).list();
	}

	public List<T> getAllDescByPK() {
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

	public List<T> getByQueryConditions(List<Map<String, Object>> conditionList) {
		Session session = getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(type);
		Root<T> root = criteria.from(type);

		// 初始化最终的Predicate为null
		Predicate finalPredicate = null;
		for (Map<String, Object> conditionMap : conditionList) {
			String fieldName = (String) conditionMap.get("fieldName");
			String operator = (String) conditionMap.get("operator");
			Object value = conditionMap.get("value");
			String logicalOperator = (String) conditionMap.get("logicalOperator");

			// 根据fieldName、operator和value构建单个Predicate
			Predicate predicate = buildPredicate(fieldName, operator, value, builder, root);


			// 判断逻辑运算符
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
		System.out.println("Final Predicate: " + criteria.getRestriction());
		// 将最终的Predicate应用到查询
		if (finalPredicate != null) {
			criteria.where(finalPredicate);
			System.out.println("Final Predicate: " + criteria.getRestriction());
		} else {
			return getAll();
		}

		List<T> resultList = session.createQuery(criteria).getResultList();
		return resultList;
	}

	public boolean update(T entity) {
		try {
			getSession().update(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
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
// 如果不符合条件，可以抛出异常或返回一个默认的Predicate
			throw new IllegalArgumentException("Invalid 'between' operator values");

		default:
// 如果操作符无法识别，可以抛出异常或返回一个默认的Predicate
			throw new IllegalArgumentException("Unsupported operator: " + operator);
		}
	}

}
