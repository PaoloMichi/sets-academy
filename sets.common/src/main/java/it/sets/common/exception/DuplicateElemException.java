package it.sets.common.exception;

public class DuplicateElemException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateElemException(String message) {
        super(message);
    }
	
	public <R> DuplicateElemException(String entityTypeDesc, R id) {
        super(String.format("Entity %s with Id %s already exists", entityTypeDesc, id.toString()));
    }
	
	public DuplicateElemException(String entityTypeDesc, String paramName, String paramValue) {
        super(String.format("Entity %s with param %s = %s already exists", entityTypeDesc, paramName, paramValue));
    }

}