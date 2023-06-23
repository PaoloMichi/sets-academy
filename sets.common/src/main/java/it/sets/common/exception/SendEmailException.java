package it.sets.common.exception;

public class SendEmailException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SendEmailException(String message, Throwable e) {
        super(message, e);
    }

}