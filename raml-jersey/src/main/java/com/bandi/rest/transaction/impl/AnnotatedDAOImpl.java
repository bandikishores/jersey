package com.bandi.rest.transaction.impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.CollectionUtils;

import com.bandi.rest.dao.entity.TestTable;

import lombok.Setter;

@Repository("annotatedDAO")
public class AnnotatedDAOImpl {

	@Inject
	protected SessionFactory sessionFactory;

	@Setter
	private String myString;

	public String checkDBAndReturnValue() {
		boolean actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
		System.out.println(actualTransactionActive);

		StringBuilder returnStr = new StringBuilder();
		returnStr.append("AnnotatedDAO Transaction State : ");
		returnStr.append(actualTransactionActive);
		returnStr.append("\n<br/>");

		Session session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("select * from TEST_TABLE where ID = ?");
		sqlQuery.setParameter(0, 1);

		List<Object[]> objects = sqlQuery.list();

		if (CollectionUtils.isEmpty(objects)) {
			returnStr.append("No Records loaded from DB!! Check INIT.sql and AppCtx script <br/>");
		} else {
			returnStr.append("Values loaded from DB are : <br/>");
			objects.stream().forEach(object -> {
				Arrays.stream(object).forEach(eachValue -> returnStr.append(eachValue).append("   "));
				returnStr.append("<br/>");
			});
			returnStr.append("<br/>");
		}

		return returnStr.toString();
	}

	public void bulkInsertData() {
		Session session = sessionFactory.getCurrentSession();

		// session.doWork(connection -> {
		for (int i = 0; i < 100; i++) {
			TestTable testTable = new TestTable();
			testTable.setTestInt(i);
			testTable.setTestChar(i + "");
			session.persist(testTable);

			if (i % 10 == 0) {
				session.flush();
				session.clear();
			}
		}
		// });

		System.out.println("Done with Bulk Insert");

	}

	private void printHibernateProperties() {
		try {
			Field f = SessionFactoryImpl.class.getDeclaredField("properties");
			f.setAccessible(true);
			Properties p = (Properties) f.get(sessionFactory);
			p.entrySet().forEach(System.out::println);
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Long getTotalDataCount() {
		Query query = sessionFactory.getCurrentSession().createQuery("select count(*) from TestTable");
		List list = query.list();
		if (CollectionUtils.isEmpty(list))
			return 0L;
		else
			return (Long) list.get(0);
	}

}
