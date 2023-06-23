package it.sets.common.exception;

public class InvalidEntityFieldException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidEntityFieldException(String field) {
        super(String.format("Invalid entity field [%s]", field));
    }
	
	public InvalidEntityFieldException(String entityTypeDesc, String field) {
        super(String.format("Invalid %s field [%s]", entityTypeDesc, field));
    }
	
}