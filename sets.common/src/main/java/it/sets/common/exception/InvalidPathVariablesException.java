package it.sets.common.exception;

public class InvalidPathVariablesException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPathVariablesException(String pathVariable) {
        super(String.format("Invalid required Path Variable %s", pathVariable));
    }
	
}