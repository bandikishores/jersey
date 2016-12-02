package com.bandi.rest.transaction.impl;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.bandi.rest.transaction.Transaction;

import lombok.Setter;

public class TransactionImpl implements Transaction {

	@Inject
	protected SessionFactory sessionFactory;

	@Setter
	private String myString;

	@Override
	public String returnSuccess() {

		StringBuilder str = new StringBuilder();

		boolean actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
		System.out.println(actualTransactionActive);

		str.append("Current Transaction State : " + actualTransactionActive + "\n<br/>");

		// Stream.of(1).forEach(value -> str.append(transaction.returnSuccess()));

		Stream.of(1).map(value -> {
			str.append("Inside Transaction State : " + TransactionSynchronizationManager.isActualTransactionActive()
					+ "\n<br/>");
			str.append(myString);
			return 0;
		}).collect(Collectors.toList());
		
		return str.toString();
	}

}
