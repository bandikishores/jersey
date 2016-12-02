package com.bandi.rest.transaction.impl;

import javax.inject.Inject;

import org.hibernate.SessionFactory;

import com.bandi.rest.transaction.Transaction;

import lombok.Setter;

public class TransactionImpl implements Transaction {

	@Inject
	protected SessionFactory sessionFactory;

	@Setter
	private String myString;

	@Override
	public String returnSuccess() {
		return myString;
	}

}
