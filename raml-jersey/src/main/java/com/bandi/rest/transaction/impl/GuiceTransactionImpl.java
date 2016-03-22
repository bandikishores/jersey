package com.bandi.rest.transaction.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import com.bandi.rest.data.ThreadLocalWrapper;
import com.bandi.rest.transaction.GuiceTransaction;

public class GuiceTransactionImpl implements GuiceTransaction {

	private String value = "";

	@Context
	private HttpServletRequest request;

	public GuiceTransactionImpl(String text) {
		value = text;
	}

	@Override
	public String returnSuccess() {
		return value + "  Request Value " + request + " Thread Local Value " + ThreadLocalWrapper.getInstance().get();
	}

}
