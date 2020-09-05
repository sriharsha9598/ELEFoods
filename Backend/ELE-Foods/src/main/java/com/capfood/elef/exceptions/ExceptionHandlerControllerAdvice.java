package com.capfood.elef.exceptions;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(UserNameExistsException.class)
	@ResponseStatus(value = HttpStatus.FOUND)
	public @ResponseBody ExceptionResponse handleUserNameExistsException(final UserNameExistsException exception,final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse(exception.getLocalizedMessage());
		return error;
	}

	@ExceptionHandler(InvalidLoginCredentialsException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleInvalidLoginCredentials(final InvalidLoginCredentialsException exception,final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse(exception.getLocalizedMessage());
		return error;
	}
	
	@ExceptionHandler(OutOfLocationRangeException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public @ResponseBody ExceptionResponse handleOutofLocationRange(final OutOfLocationRangeException exception,final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse(exception.getLocalizedMessage());
		return error;
	}

	@ExceptionHandler(OrderContainsInactiveItemsException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public @ResponseBody ExceptionResponse handleOrderContainsInactiveItems(final OrderContainsInactiveItemsException exception,final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse(exception.getLocalizedMessage());
		return error;
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleResourceNotFoundException(final ResourceNotFoundException exception,final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse(exception.getLocalizedMessage());
		return error;
	}
	
	@ExceptionHandler(CarryBoxEmptyException.class)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public @ResponseBody ExceptionResponse handleCarryBoxEmptyException(final CarryBoxEmptyException exception,final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse(exception.getLocalizedMessage());
		return error;
	}
}