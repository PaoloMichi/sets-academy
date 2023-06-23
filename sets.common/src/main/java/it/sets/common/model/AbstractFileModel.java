package it.sets.common.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class AbstractFileModel extends AbstractLongModel {
	
	@NotBlank
	@Size(max = 255)
	@Column(nullable = false, length = 255)
	private String virtualFileName;
	
    @Size(max = 255)
    @Column(nullable = false, length = 255)
	private String originalFileName;
    
    @JsonIgnore
    @Column(nullable = false, length = 255)
	private String fileSystemPath;
    
    @JsonIgnore
    @Column(nullable = true, length = 255)
	private String fileNamePrefix;
	
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'hh:mm:ss.SSSZ")
    @Column(insertable = false, updatable = false, columnDefinition="DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'hh:mm:ss.SSSZ")
    @Column(nullable = true, updatable = false, columnDefinition="DATETIME NULL ON UPDATE CURRENT_TIMESTAMP")
	private Date updateAt;
    
	private Long size;
	
    @Size(max = 10)
    @Column(nullable = false, length = 10)
	private String extension;
	
	public AbstractFileModel() {
		
	}

	public AbstractFileModel(String virtualFileName, String originalFileName, String fileSystemPath, String fileNamePrefix, 
			Date createdAt, Date updateAt, Long size, String extension) {
		this.virtualFileName = virtualFileName;
		this.originalFileName = originalFileName;
		this.fileSystemPath = fileSystemPath;
		this.fileNamePrefix = fileNamePrefix;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
		this.size = size;
		this.extension = extension;
	}
	
	public AbstractFileModel(Long id, String virtualFileName, String originalFileName, String fileSystemPath, String fileNamePrefix, 
			Date createdAt, Date updateAt, Long size, String extension) {
		super(id);
		this.virtualFileName = virtualFileName;
		this.originalFileName = originalFileName;
		this.fileSystemPath = fileSystemPath;
		this.fileNamePrefix = fileNamePrefix;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
		this.size = size;
		this.extension = extension;
	}

	public String getVirtualFileName() {
		return virtualFileName;
	}

	public void setVirtualFileName(String virtualFileName) {
		this.virtualFileName = virtualFileName;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getFileSystemPath() {
		return fileSystemPath;
	}

	public void setFileSystemPath(String fileSystemPath) {
		this.fileSystemPath = fileSystemPath;
	}

	public String getFileNamePrefix() {
		return fileNamePrefix;
	}

	public void setFileNamePrefix(String fileNamePrefix) {
		this.fileNamePrefix = fileNamePrefix;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
	
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	/**
	 * Used in Update service method (without multipart File).
	 * 
	 * @param storedEntity
	 */
	public void setFileStoredFields(AbstractFileModel storedEntity) {
		setId(storedEntity.getId());
		this.virtualFileName = storedEntity.getVirtualFileName();
		this.originalFileName = storedEntity.getOriginalFileName();
		this.fileSystemPath = storedEntity.getFileSystemPath();
		this.fileNamePrefix = storedEntity.getFileNamePrefix();
		this.createdAt = storedEntity.getCreatedAt();
		this.updateAt = Calendar.getInstance().getTime();
		this.size = storedEntity.getSize();
		this.extension = storedEntity.getExtension();
	}
	
	/**
	 * Use in toString() implementation
	 * 
	 * @return
	 */
	protected String toStringAbstractFileModel() {
		return new StringBuilder(toStringAbstractModel()).append(", \"virtualFileName\":\"").append(virtualFileName)
				.append("\", \"originalFileName\":\"").append(originalFileName).append("\", \"fileSystemPath\":\"")
				.append(fileSystemPath).append("\", \"fileNamePrefix\":\"").append(fileNamePrefix)
				.append("\", \"createdAt\":\"").append(createdAt).append("\", \"updateAt\":\"").append(updateAt)
				.append("\", \"size\":").append(size).append(", \"extension\":\"").append(extension).append("\"")
				.toString();
	}

	/**
	 * Use in toMultiValueMap() implementation.
	 * 
	 * @param map
	 * @return
	 */
	protected MultiValueMap<String, Object> toMultiValueMapAbstractFileModel(MultiValueMap<String, Object> map) {
		if (null != getId())
			map.add("id", getId());
		if (null != this.virtualFileName)
			map.add("fileName", this.virtualFileName);
		if (null != this.originalFileName)
			map.add("originalFileName", this.originalFileName);
		if (null != this.createdAt)
			map.add("createdAt", this.createdAt);
		if (null != this.updateAt)
			map.add("updateAt", this.updateAt);
		if (null != this.size)
			map.add("size", this.size);
		if (null != this.extension)
			map.add("extension", this.extension);
		return map;
	}
	
	/**
	 * Use in toMap() implementation.
	 * 
	 * @param map
	 * @return
	 */
	protected Map<String, Object> toMapAbstractFileModel(Map<String, Object> map) {
		if (null != getId())
			map.put("id", getId());
		if (null != this.virtualFileName)
			map.put("fileName", this.virtualFileName);
		if (null != this.originalFileName)
			map.put("originalFileName", this.originalFileName);
		if (null != this.createdAt)
			map.put("createdAt", this.createdAt);
		if (null != this.updateAt)
			map.put("updateAt", this.updateAt);
		if (null != this.size)
			map.put("size", this.size);
		if (null != this.extension)
			map.put("extension", this.extension);
		return map;
	}
	
}
