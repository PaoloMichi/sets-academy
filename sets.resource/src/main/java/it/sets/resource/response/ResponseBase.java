package it.sets.resource.response;

public class ResponseBase<T> {
	private T response;
	private Integer code;
	private String message;
	
	public ResponseBase() {
		super();
	}

	public ResponseBase(T response, Integer code, String message) {
		super();
		this.response = response;
		this.code = code;
		this.message = message;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
