package it.sets.common.model.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.PageRequest;

public abstract class AbstractListResponse<T> extends BaseResponse implements IListResponse<T> {

	private int resultSize;

	private List<T> result;

	public AbstractListResponse() {

	}

	public AbstractListResponse(T elem) {
		this.result = new ArrayList<T>(Arrays.asList(elem));
		this.resultSize = 1;
	}

	public AbstractListResponse(List<T> list) {
		this.result = list;
		this.resultSize = result.size();
	}

	public AbstractListResponse(PageRequest page, int totalPage, List<T> result) {
		this.setPagination(page, totalPage);
		this.result = result;
		this.resultSize = result.size();
	}
	
	public AbstractListResponse(PageRequest page, int totalPage, List<T> result, List<TableFieldConfig> configurationList) {
		this.setPagination(page, totalPage);
		this.result = result;
		this.resultSize = result.size();
		this.setFieldsConfiguration(configurationList);
	}

	public int getResultSize() {
		return resultSize;
	}

	public void setResultSize(int resultSize) {
		this.resultSize = resultSize;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> list) {
		this.result = list;
		this.resultSize = list.size();
	}

}
