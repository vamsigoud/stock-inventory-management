package com.altimetrik.playground.exception;

import java.io.Serializable;

public class ErrorDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7107656885877470711L;

	private String exceptionMessage;
	private int httpStatusCode;
	private String httpErrorMessage;
	private String timestamp;
	private String path;

	public ErrorDetails(String exceptionMessage, int httpStatusCode, String httpErrorMessage, String timestamp,
			String path) {
		super();
		this.exceptionMessage = exceptionMessage;
		this.httpStatusCode = httpStatusCode;
		this.httpErrorMessage = httpErrorMessage;
		this.timestamp = timestamp;
		this.path = path;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getHttpErrorMessage() {
		return httpErrorMessage;
	}

	public void setHttpErrorMessage(String httpErrorMessage) {
		this.httpErrorMessage = httpErrorMessage;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
