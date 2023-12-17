
package com.hr.exception;
 
import com.hr.util.ErrorResponse;
 
@SuppressWarnings("serial")

public class DuplicateLocationException extends RuntimeException {

	private ErrorResponse errorResponse;
 
	public DuplicateLocationException(ErrorResponse errorResponse) {

		super();

		this.errorResponse = errorResponse;

	}
 
	public ErrorResponse getErrorResponse() {

		return errorResponse;

	}
 
	public void setErrorResponse(ErrorResponse errorResponse) {

		this.errorResponse = errorResponse;

	}


}