package coupons.utils;

import coupons.ErrorType;
import coupons.ExceptionType;

public class ApplicationException extends Exception {
	
	public ApplicationException(String message){
		super(message);
	}
	
	public ApplicationException(String message, Exception e){
		super(message, e);
	}
	
	public ApplicationException(ExceptionType critical, Exception e, String message) {
		this(message, e);
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = -7117348639862841186L;

	public ErrorType getErrorType() {
		// TODO Auto-generated method stub
		return null;
	}

}
