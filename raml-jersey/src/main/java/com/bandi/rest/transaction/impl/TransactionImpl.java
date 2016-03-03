package com.bandi.rest.transaction.impl;

import com.bandi.rest.transaction.Transaction;

import lombok.Setter;

public class TransactionImpl implements Transaction {
	
	@Setter
	private String myString;

	@Override
	public String returnSuccess() {
		return myString;
	}

}
