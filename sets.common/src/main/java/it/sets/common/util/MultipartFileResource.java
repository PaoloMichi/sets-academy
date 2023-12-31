package it.sets.common.util;

import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileResource extends ByteArrayResource {

	private String filename;

	public MultipartFileResource(MultipartFile multipartFile) throws IOException {
		super(multipartFile.getBytes());
		this.filename = multipartFile.getOriginalFilename();
	}

	@Override
	public String getFilename() {
		return this.filename;
	}
	
}