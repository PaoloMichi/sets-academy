package it.sets.common.model.web;

public class Pagination {
	
	private int currentPage;
	
	private int pageSize;

	private int beginIndex;
	
	private int endIndex;
	
	private String nextPage;
	
	public Pagination() {
		
	}
	
	public Pagination(int currentPage, int pageSize, int beginIndex, int endIndex, String nextPage) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
		this.nextPage = nextPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}
	
}
