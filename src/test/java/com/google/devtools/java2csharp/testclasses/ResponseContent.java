package com.google.devtools.java2csharp.testclasses;

public class ResponseContent {
	private boolean success;

	private long errorCode;

	private String errorDescription;

	protected ResponseContent() {
		super();
	}

	public ResponseContent(boolean success, long errorCode,
			String errorDescription) {
		super();
		this.success = success;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

}