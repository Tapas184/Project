package com.nt.errorHandler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nt.customerror.ErrorInUserRegistration;

@ControllerAdvice
public class CustomErrorHandler {
	
	@ExceptionHandler(value = {ConstraintViolationException.class,ErrorInUserRegistration.class})
	public String errorHandler() {
		return "error";
	}

}
