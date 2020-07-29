package com.pkg.food.delivery.system.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderDetailsNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public OrderDetailsNotFoundException(String message) {
		super(message);
	}
}
