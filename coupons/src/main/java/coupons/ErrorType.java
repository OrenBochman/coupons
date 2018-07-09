package coupons;

public enum ErrorType {

	UserNotfound(false,1,1,500),
	CompanyNotFound(false,1,1,500),
	CouponNotFount(false,1,1,500);

	private boolean isCritical;
	private int httpCode;
	private int internalCode;
	private int ExternalCode;
	
	private ErrorType(
			boolean isCritical, 
			int httpCode, 
			int internalCode, 
			int ExternalCode) {
		 this.isCritical=isCritical; 
		 this.httpCode=httpCode;
		 this.internalCode=internalCode;
		 this.ExternalCode=ExternalCode;
	}
	
	/**
	 * @return the isCritical
	 */
	public boolean isCritical() {
		return isCritical;
	}

	/**
	 * @return the httpCode
	 */
	public int getHttpCode() {
		return httpCode;
	}

	/**
	 * @return the internalCode
	 */
	public int getInternalCode() {
		return internalCode;
	}

	/**
	 * @return the externalCode
	 */
	public int getExternalCode() {
		return ExternalCode;
	}

	
}
