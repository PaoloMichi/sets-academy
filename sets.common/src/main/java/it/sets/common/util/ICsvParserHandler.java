package it.sets.common.util;

import java.util.List;

public interface ICsvParserHandler<T> {
	
	String[] getHeader();

	List<String[]> getBody(List<T> list);
	
	String[] getHeader(List<T> list);

}
