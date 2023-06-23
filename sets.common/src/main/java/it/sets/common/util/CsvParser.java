package it.sets.common.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public class CsvParser<T> {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CsvParser.class);
	
	private final static String NEW_LINE_CHARSEQ = "\n";
	private final static String COLUMN_DIVIDER = ";";
	
	private ICsvParserHandler<T> handler;
	
	private List<T> list;
	
	public CsvParser<T> handler(ICsvParserHandler<T> handler) {
		this.handler = handler;
		return this;
	}
	
	public CsvParser<T> list(List<T> list) {
		this.list = list;
		return this;
	}
	
	public List<String[]> readFileCsv(String filePath) {
		LOGGER.debug("Executing readCsv");
		if (null == filePath)
			throw new RuntimeException("CsvParserException: filePath is NULL");
		
		List<String[]> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(COLUMN_DIVIDER);
		        lines.add(values);
		    }
		} catch (FileNotFoundException e) {
			throw new RuntimeException("CsvParserException: file not found", e);
		} catch (IOException e) {
			throw new RuntimeException("CsvParserException: generic IO", e);
		}
		return lines;
	}
	
	public List<String[]> convertMultipart(MultipartFile file) {
        byte[] bytes;
		try {
			bytes = file.getBytes();
		} catch (IOException e) {
			throw new RuntimeException("CsvParserException: generic IO", e);
		}
	
        String completeData = new String(bytes, StandardCharsets.ISO_8859_1);
        String[] rows = completeData.split(NEW_LINE_CHARSEQ);
        return Stream.of(rows).map(elem -> elem.split(COLUMN_DIVIDER)).collect(Collectors.toList());
	}
	
	public List<String[]> convertMultipartInStringList(MultipartFile file) {
        byte[] bytes;
		try {
			bytes = file.getBytes();
		} catch (IOException e) {
			throw new RuntimeException("CsvParserException: generic IO", e);
		}
        String completeData = new String(bytes);
        String[] rows = completeData.split(NEW_LINE_CHARSEQ);
        for (int i = 0; i < rows.length; i++) {
			rows[i] = rows[i].replace(rows[i].substring(rows[i].length()-1), ";");
		}
        return Stream.of(rows).map(elem -> elem.split(COLUMN_DIVIDER)).collect(Collectors.toList());
	}
	
	public String buildString() {
		LOGGER.debug("Executing buildStringWriter");
		
		if (null == list)
			throw new RuntimeException("CsvParserException: list is null");
		if (null == handler)
			throw new RuntimeException("CsvParserException: handler is null");
		
		StringWriter writer = new StringWriter();
		writer.append(parseLine(this.handler.getHeader()));
		writer.append(NEW_LINE_CHARSEQ);
		handler.getBody(list).stream().map(elem -> parseLine(elem))
		.forEach(elem -> {
			writer.append(elem);
			writer.append(NEW_LINE_CHARSEQ);
		});
		String stringCsv = writer.toString();
		try {
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException("CsvParserException: unable to close StringWriter");
		}
		return stringCsv;
	}
	
	public String buildStringWithHeader() {
		LOGGER.debug("Executing buildStringWriter");
		
		if (null == list)
			throw new RuntimeException("CsvParserException: list is null");
		if (null == handler)
			throw new RuntimeException("CsvParserException: handler is null");
		
		StringWriter writer = new StringWriter();
		writer.append(parseLine(this.handler.getHeader(list)));
		writer.append(NEW_LINE_CHARSEQ);
		handler.getBody(list).stream().map(elem -> parseLine(elem))
		.forEach(elem -> {
			writer.append(elem);
			writer.append(NEW_LINE_CHARSEQ);
		});
		String stringCsv = writer.toString();
		try {
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException("CsvParserException: unable to close StringWriter");
		}
		return stringCsv;
	}
	
	public Resource buildResource() {
		LOGGER.debug("Executing buildResource");
		
		return new ByteArrayResource(this.buildString().getBytes(StandardCharsets.ISO_8859_1));
	}
	
	public Resource buildResourceWithHeader() {
		LOGGER.debug("Executing buildResource");
		
		return new ByteArrayResource(this.buildStringWithHeader().getBytes(StandardCharsets.ISO_8859_1));
	}
	
	private String parseLine(String[] data) {
		
        return Stream.of(data)
            .map(elem -> escapeSpecialCharacters(elem))
            .collect(Collectors.joining(COLUMN_DIVIDER));
    }

    private String escapeSpecialCharacters(String data) {
    	
    	if (null == data)
    		return "";
    	String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'") || data.contains(COLUMN_DIVIDER)) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

}
