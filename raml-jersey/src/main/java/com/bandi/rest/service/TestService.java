package com.bandi.rest.service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.bandi.rest.transaction.Transaction;

@Service("testService")
public class TestService {
	
	@Inject
	Transaction transaction;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String returnString() {

		StringBuilder str = new StringBuilder();
		
		boolean actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
		System.out.println(actualTransactionActive);
		
		str.append("Current Transaction State : " + actualTransactionActive + "\n<br/>");
		
		// Stream.of(1).forEach(value -> str.append(transaction.returnSuccess()));
		
		Stream.of(1).map(value -> str.append(transaction.returnSuccess())).collect(Collectors.toList());
		
		// str.append(transaction.returnSuccess());
		return str.toString();
	}

}
