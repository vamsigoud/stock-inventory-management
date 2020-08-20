package com.altimetrik.playground.exception;

public class CapplanException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8715717783041378905L;

	private String errorMessage;
	private String errorCode;

	public CapplanException(String errorMessage, Exception e) {
		super(e);
		this.errorMessage = errorMessage;
	}
	
    public CapplanException(Exception e) {
	    super(e);
	}
	
	public CapplanException(String errorMessage, String errorCode, Exception e) {
		super(e);
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
