package com.bandi.rest.transaction.impl;

import com.bandi.rest.transaction.GuiceTransaction;

public class GuiceTransactionImpl implements GuiceTransaction {

	@Override
	public String returnSuccess() {
		return "Guice DI Success";
	}

}
