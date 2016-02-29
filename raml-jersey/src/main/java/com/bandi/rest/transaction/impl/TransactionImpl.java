package com.bandi.rest.transaction.impl;

import com.bandi.rest.transaction.Transaction;

public class TransactionImpl implements Transaction {

	@Override
	public String returnSuccess() {
		return " Spring transaction and DI successful ";
	}

}
