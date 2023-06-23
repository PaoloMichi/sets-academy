package it.sets.common.model.web;

public abstract class AbstractResponse<T> extends BaseResponse implements IResponse<T> {

	private T result;

	public AbstractResponse() {

	}

	public AbstractResponse(T elem) {
		this.result = elem;
	}

	@Override
	public T getResult() {
		return result;
	}

	public void setResult(T elem) {
		this.result = elem;
	}

}
