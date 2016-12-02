package com.bandi.rest.data;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalWrapper {

	private static ThreadLocal<List<String>> listOfStringLocals;

	public static synchronized ThreadLocal<List<String>> getInstance() {
		if (listOfStringLocals == null) {
			listOfStringLocals = new ThreadLocal<List<String>>() {
				@Override
				protected List<String> initialValue() {
					return new ArrayList<String>();
				}
			};
		}
		return listOfStringLocals;
	}
}
