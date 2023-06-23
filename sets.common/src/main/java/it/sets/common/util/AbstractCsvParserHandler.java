package it.sets.common.util;

import java.util.Date;

public abstract class AbstractCsvParserHandler<T> implements ICsvParserHandler<T> {
	
	protected String getStringFromDate(Date data) {
		return getStringFromDate(data, GenericUtils.SHORT_SLASH_DATE_FORMATTER);
	}
	
	protected String getStringFromDate(Date data, String formatter) {
		return GenericUtils.getStringFromDate(data, formatter);
	}
	
	protected String getStringFromBoolean(Boolean bool) {
		if (bool) return "Si";
		if (!bool) return "No";
		return null;
	}

}
