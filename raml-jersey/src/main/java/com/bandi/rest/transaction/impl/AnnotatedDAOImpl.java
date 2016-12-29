package com.bandi.rest.transaction.impl;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.CollectionUtils;

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

}
