package coupons;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 7424529731588818168L;
	private ErrorType errorType;

	
	public ApplicationException() {}
	
	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(String message,ErrorType errorType) {
		super(message);
		this.setErrorType(errorType);
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	protected void setErrorType(ErrorType errorType) {
		this.errorType = errorType; 
	}

}


