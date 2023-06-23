package it.sets.common.model.web;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Order;

public class BaseResponse {
	
	private TechHeader techHeader;
	
	private ApiError error;
	
	private Pagination pagination;
	
	private List<TableFieldConfig> fieldsConfiguration;
	
	public BaseResponse() {
		
	}
	
	public BaseResponse(ApiError error) {
		this.error = error;
	}
	
	public BaseResponse(TechHeader techHeader) {
		this.techHeader = techHeader;
	}

	public TechHeader getTechHeader() {
		return techHeader;
	}

	public void setTechHeader(TechHeader techHeader) {
		this.techHeader = techHeader;
	}
	
	public ApiError getError() {
		return error;
	}

	public void setError(ApiError error) {
		this.error = error;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<TableFieldConfig> getFieldsConfiguration() {
		return fieldsConfiguration;
	}

	public void setFieldsConfiguration(List<TableFieldConfig> fieldsConfiguration) {
		this.fieldsConfiguration = fieldsConfiguration;
	}

	public void setPagination(PageRequest page, int totalPage) {
		String nextPage = null;
		Order order = page.getSort().iterator().next();
		if (totalPage > (page.getPageNumber() + 1)) {
			nextPage = new StringBuilder("currentPage=").append(String.valueOf(page.getPageNumber() + 2))
					.append("&size=").append(String.valueOf(page.getPageSize()))
					.append("&direction=").append(order.getDirection().toString())
					.append("&sortField=").append(order.getProperty())
					.toString();
		}
		this.pagination = new Pagination(page.getPageNumber() + 1, page.getPageSize(), 1, totalPage, nextPage);
	}

}
