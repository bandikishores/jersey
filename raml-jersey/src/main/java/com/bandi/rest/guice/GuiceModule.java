package com.bandi.rest.guice;

import com.bandi.rest.transaction.GuiceTransaction;
import com.bandi.rest.transaction.impl.GuiceTransactionImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.name.Names;

public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		// bind(GuiceTransaction.class).to(GuiceTransactionImpl.class);

		bind(String.class).annotatedWith(Names.named("testString")).toInstance("Guice DI Success");

		bind(GuiceTransaction.class).toProvider(new MagicProvider<GuiceTransaction>(GuiceTransactionImpl.class,
				Key.get(String.class, Names.named("testString"))));

	}

}
