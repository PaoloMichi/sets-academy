package it.sets.common.exception;

import org.springframework.http.HttpMethod;

public class MethodNotAllowedException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MethodNotAllowedException(HttpMethod method) {
        super(new StringBuilder("Unsupported method ").append(method.toString()).toString());
    }
	
}