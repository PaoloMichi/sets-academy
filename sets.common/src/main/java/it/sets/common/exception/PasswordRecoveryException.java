package it.sets.common.exception;

public class PasswordRecoveryException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String shortMessage;
	
	private String token;
	
	private String key;

	public PasswordRecoveryException(String message, String token, String key) {
        super(new StringBuilder("Invalid Token or Key: ").append(token).append("/").append(key).toString());
        this.shortMessage = message;
        this.token = token;
        this.key = key;
    }

	public String getShortMessage() {
		return shortMessage;
	}
	
	public String getToken() {
		return token;
	}

	public String getKey() {
		return key;
	}

}