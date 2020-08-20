/**
 * 
 */
package com.altimetrik.playground.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;


@RestControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ErrorDetails constraintViolationException(
			ConstraintViolationException constraintViolationException,WebRequest request) {
		
		ErrorDetails errorDetails = new ErrorDetails(constraintViolationException.getMessage(), 400,"Bad Request",new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss").format(new Date()),request.getDescription(false));
		
		addStackTraceInLogFile(constraintViolationException);
		
		return errorDetails;
	}
	

	@ExceptionHandler(value = { NoHandlerFoundException.class })
	public ErrorDetails noHandlerFoundException(Exception exception,WebRequest request) {
		
		ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), 404, "Not Found",new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss").format(new Date()),request.getDescription(false));
		
		addStackTraceInLogFile(exception);
		
		return errorDetails;
	}
	

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ErrorDetails handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception,
			WebRequest request) {

		String error = exception.getName() + " should be of type " + exception.getRequiredType().getName();

		ErrorDetails errorDetails = new ErrorDetails(error, 400, "Bad Request",new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss").format(new Date()),request.getDescription(false));
		
		addStackTraceInLogFile(exception);
		
		return errorDetails;
	}
	

	@ExceptionHandler(value = { NumberFormatException.class })
	public ErrorDetails numberFormatException(Exception exception,WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), 417,"Number Format Exception",new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss").format(new Date()),request.getDescription(false));
		
		addStackTraceInLogFile(exception);
		
		return errorDetails;
	}
	
	
	@ExceptionHandler(value = { org.hibernate.exception.DataException.class })
	public ErrorDetails dataException(Exception exception,WebRequest request) {
		
		ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), 417, "DataException",new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss").format(new Date()),request.getDescription(false));
		
		addStackTraceInLogFile(exception);
		
		return errorDetails;
	}
	
	@ExceptionHandler(value = { CapplanException.class })
	public ErrorDetails capplanException(CapplanException exception,WebRequest request) {
		
		//ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), 417, "Expectation Failed",new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss").format(new Date()),request.getDescription(false));
		ErrorDetails errorDetails = new ErrorDetails(exception.getErrorMessage(), 417, "Expectation Failed",new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss").format(new Date()),request.getDescription(false));
		
		addStackTraceInLogFile(exception);
		
		return errorDetails;
	}

	@ExceptionHandler(value = { Exception.class })
	public ErrorDetails unknownException(Exception exception,WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), 500,"Internal Server Error",new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss").format(new Date()),request.getDescription(false));
		
		addStackTraceInLogFile(exception);
		
		return errorDetails;
	}
	
	private void addStackTraceInLogFile(Exception exception) {
		
			StringWriter stringWriter = new StringWriter();
			exception.printStackTrace(new PrintWriter(stringWriter));
			logger.error(stringWriter.toString());
	}

}
