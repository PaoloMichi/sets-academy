package it.sets.common.exception;

public class EntityNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
        super(message);
    }
	
	public <R> EntityNotFoundException(String entityTypeDesc, R id) {
        super(String.format("Entity %s with Id %s not found", entityTypeDesc, id.toString()));
    }
	
	public EntityNotFoundException(String entityTypeDesc, String paramName, String paramValue) {
        super(String.format("Entity %s with param %s = %s not found", entityTypeDesc, paramName, paramValue));
    }
	
}