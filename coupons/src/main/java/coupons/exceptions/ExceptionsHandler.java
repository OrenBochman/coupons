package coupons.exceptions;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import coupons.ErrorType;
import coupons.utils.ApplicationException;

@ControllerAdvice
public class ExceptionsHandler {
	
	int httpErrrorCode =1;
	int externalErrorCode=1;
	@ExceptionHandler(Throwable.class)
	public void handleConflict(HttpServletResponse response,Throwable e) {

		if(e instanceof ApplicationException) {
			ApplicationException e2= (ApplicationException)  e;
			ErrorType errorType = e2.getErrorType();
			if(errorType.isCritical()) {
				e.printStackTrace();
			}
			//httpErrrorCode = e2.getCause();
			//externalErrorCode=e3.get
			
		}
	}
}
