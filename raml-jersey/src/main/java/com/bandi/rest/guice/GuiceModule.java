package com.bandi.rest.guice;

import com.bandi.rest.transaction.GuiceTransaction;
import com.bandi.rest.transaction.impl.GuiceTransactionImpl;
import com.google.inject.AbstractModule;

public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(GuiceTransaction.class).to(GuiceTransactionImpl.class);
	}

}
