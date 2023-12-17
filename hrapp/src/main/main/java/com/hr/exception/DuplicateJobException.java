
package com.hr.exception;
 
import com.hr.util.ErrorResponse;
 
@SuppressWarnings("serial")

public class DuplicateJobException extends RuntimeException {

	private ErrorResponse errorResponse;
 
	public DuplicateJobException(ErrorResponse errorResponse) {

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