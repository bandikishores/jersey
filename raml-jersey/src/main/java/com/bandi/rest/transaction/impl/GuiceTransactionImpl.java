package com.bandi.rest.transaction.impl;

import com.bandi.rest.transaction.GuiceTransaction;

public class GuiceTransactionImpl implements GuiceTransaction {

	private String value = "";
	
	public GuiceTransactionImpl(String text) {
		value = text;
	}
	
	@Override
	public String returnSuccess() {
		return value;
	}

}
