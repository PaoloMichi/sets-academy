package it.sets.common.exception;

public class MissingEntityFieldException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MissingEntityFieldException(String message) {
        super(message);
    }
	
	public MissingEntityFieldException(String entityTypeDes, String field) {
        super(String.format("Missing %s field [%s]", entityTypeDes, field));
    }
	
}