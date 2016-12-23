package com.bandi.rest.data;

import org.apache.commons.lang3.exception.ExceptionUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	public ErrorResponse(Throwable ex) {
		exceptionMessage = ex.getMessage();
		stackTrace = ExceptionUtils.getStackTrace(ex);
	}

	public ErrorResponse(String message) {
		errorMessage = message;
	}

	private String errorMessage;

	private String exceptionMessage;

	private String stackTrace;
}
