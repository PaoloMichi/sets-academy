package it.sets.common.web;

import org.slf4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public abstract class AbstractController {
	
	protected static final int MAX_PAGE_SIZE = 1000;
	
	protected abstract Logger getLogger();
	
	/**
	 * Creates PageRequest object mandatory to findAllPaginated methods and all other services that implement pagination.
	 * 
	 * @param page
	 * @param size
	 * @param direction
	 * @param sortField
	 * @return
	 */
	protected PageRequest populatePageRequest() {
		return populatePageRequest(null, null, null, null);
	}

	protected PageRequest populatePageRequest(Integer page, Integer size, Sort.Direction direction, String sortField) {
		
		final Integer pageFinal = ((page == null) ? 1 : page);
		final Integer sizeFinal = ((size == null) ? MAX_PAGE_SIZE : size);
		final Sort.Direction sortFinal = ((direction == null) ? Sort.Direction.DESC : direction);
		final String sortFieldFinal = ((sortField == null) ? "id" : sortField);

		return PageRequest.of(pageFinal - 1, sizeFinal, sortFinal, sortFieldFinal);
	}
	
	
	
}
