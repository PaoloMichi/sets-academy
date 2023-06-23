package it.sets.common.exception;

import it.sets.common.model.ERepositoryMethod;

public class RepositoryException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepositoryException(String message) {
        super(message);
    }
	
	public RepositoryException(String entityTypeDesc, ERepositoryMethod repositoryMethod, Throwable exception) {
        super(String.format("Error while executing %s %s: %s", entityTypeDesc, repositoryMethod.toString(), exception.getLocalizedMessage()), exception);
    }
	
	public RepositoryException(String entityTypeDesc, ERepositoryMethod repositoryMethod, String message, Throwable exception) {
        super(String.format("Error while executing %s %s: %s", entityTypeDesc, repositoryMethod.toString(), message), exception);
    }
	
	public RepositoryException(String message, Throwable exception) {
        super(message, exception);
    }
	
}