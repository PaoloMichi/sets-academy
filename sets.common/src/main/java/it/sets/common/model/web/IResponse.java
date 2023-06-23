package it.sets.common.model.web;

public interface IResponse<T> {
	
	ApiError getError();
	
	T getResult();

}
