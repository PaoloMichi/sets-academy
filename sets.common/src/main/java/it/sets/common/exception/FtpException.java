package it.sets.common.exception;

public class FtpException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FtpException(String message, Throwable e) {
        super(message, e);
    }
	
}
