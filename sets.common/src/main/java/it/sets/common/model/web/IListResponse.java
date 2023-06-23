package it.sets.common.model.web;

import java.util.List;

public interface IListResponse<T> {
	
	ApiError getError();
	
	Pagination getPagination();
	
	List<?> getResult();

}
