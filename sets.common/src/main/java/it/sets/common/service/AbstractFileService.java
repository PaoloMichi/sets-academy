package it.sets.common.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import it.sets.common.exception.EntityNotFoundException;
import it.sets.common.exception.FileException;
import it.sets.common.exception.MissingEntityFieldException;
import it.sets.common.model.AbstractFileModel;
import it.sets.common.model.IFileModelProperties;
import it.sets.common.repository.IFileRepository;
import it.sets.common.util.FileUtils;
import it.sets.common.util.GenericUtils;

public abstract class AbstractFileService<T extends AbstractFileModel> extends AbstractLongService<T> {
	
	private final Path rootLocation;
	
	private String fileNamePrefix;
	
	private String fileNameSeparator = "_";
	
	private Path fileLocation;
	
	private String newVirtualFileName;
	
	protected AbstractFileService(IFileModelProperties properties) {
		if (null == properties)
			throw new RuntimeException("FileModelProperties is NULL");
		this.rootLocation = Paths.get(properties.getLocation());
	}
	
	protected abstract IFileRepository<T> getRepository();
	
	protected Path getRootLocation() {
		if (null == rootLocation)
			throw new RuntimeException(new StringBuilder(getEntityTypeDesc()).append(" RootLocation is NULL").toString());
		return rootLocation;
	}
	
	/**
	 * Initialize rootLocation folder.
	 * Override it if you have more than one locations.
	 * 
	 * @param rootLocation
	 */
	@PostConstruct
	protected void initLocation() {
		try {
			Files.createDirectories(getRootLocation());
		} catch (IOException e) {
			throw new FileException("Could not initialize storage", e);
		}
	}
	
	@Override
	public T findByVirtualFileName(String virtualFileName) {
		getLogger().info("Executing abstractFile findByVirtualFileName");
		return getRepository().findByVirtualFileName(virtualFileName)
				.orElseThrow(() -> new EntityNotFoundException(getEntityTypeDesc(), "virtualFileName", virtualFileName));
	}
	
	@Override
	public Optional<T> findByVirtualFileNameOpz(String virtualFileName) {
		getLogger().info("Executing abstractFile findByVirtualFileNameOpz");
		return getRepository().findByVirtualFileName(virtualFileName);
	}
	
	@Override
	public Resource serveFileByVirtualFileName(String virtualFileName) {
		getLogger().info("Executing abstractFile serveFileByVirtualFileName");
		Optional<T> entity = getRepository().findByVirtualFileName(virtualFileName);
		if (!entity.isPresent())
			throw new EntityNotFoundException(getEntityTypeDesc(), "virtualFileName", virtualFileName);
		return serveFile(virtualFileName, entity.get().getFileSystemPath(), entity.get().getFileNamePrefix());
	}
	
	@Override
	public Resource serveFileById(Long id) {
		getLogger().info("Executing abstract serveFileById by id");
		T entity = super.findById(id);
		return serveFile(entity.getVirtualFileName(), entity.getFileSystemPath(), entity.getFileNamePrefix());
	}
	
	public Resource serveFile(String fileName, String path, String prefix) {
		try {
			String cleanedFileName = cleanFileName(prefix, fileName);
			Path file = FileUtils.load(Paths.get(path), cleanedFileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new FileException(new StringBuilder("Could not read file ").append(cleanedFileName).append(" in ").append(path).toString());
			}
		} catch (MalformedURLException e) {
			throw new FileException(new StringBuilder("Could not found file: ").append(fileName).toString(), e);
		}
	}
	
	@Override
	@Transactional
	public T createFile(T entity, MultipartFile file) {
		getLogger().info("Executing abstract createFile");
		entity = buildBaseFile(entity, buildDefaultPrefix(), file, trimFileName(file.getOriginalFilename()));
		T newEntity = super.create(entity);
		store(file);
		return newEntity;
	}
	
	@Override
	@Transactional
	public void deleteFileById(Long id) {
		getLogger().info("Executing abstract deleteFileById");
		T entity = findById(id);
	    super.deleteById(id);
	    removeExistingFile(entity);
	}
	
	@Override
	public T update(Long id, T entity) {
		T storedEntity = super.findById(id);
		entity.setFileStoredFields(storedEntity);
		return super.save(entity);
	}
	
	@Override
	@Transactional
	public void updateFile(Long id, T entity, MultipartFile file) {
		getLogger().info("Executing abstract updateFile");
		if (null == file)
			throw new MissingEntityFieldException(getEntityTypeDesc(), "file");
		T storedEntity = findById(id);
		removeExistingFile(storedEntity);
		entity = buildExistingBaseFile(entity, storedEntity, buildDefaultPrefix(), file, trimFileName(file.getOriginalFilename()));
		save(entity);
		store(file);
	}
	
	@Override
	public void patchFile(Long id, MultiValueMap<String, Object> params, MultipartFile file) {
		getLogger().info("Executing abstract patchFile");
		if (null != file) {
			T storedEntity = findById(id);
			removeExistingFile(storedEntity);
			storedEntity = buildExistingBaseFile(storedEntity, storedEntity, buildDefaultPrefix(), file, trimFileName(file.getOriginalFilename()));
			save(storedEntity);
			store(file);
		}
	}
	
	protected T buildBaseFile(T entity, String fileNamePrefix, MultipartFile file, String newVirtualFileName) {
		this.fileNamePrefix = fileNamePrefix;
		this.newVirtualFileName = newVirtualFileName;
		this.fileLocation = buildFileLocation();
		entity.setVirtualFileName(buildVirtualFileName(newVirtualFileName));
		entity.setOriginalFileName(GenericUtils.removeExtensionFromFileName(file.getOriginalFilename()));
		entity.setFileSystemPath(this.fileLocation.toString());
		entity.setFileNamePrefix(getFileNamePrefix());
		entity.setSize(file.getSize());
		entity.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
		return entity;
	}
	
	protected T buildExistingBaseFile(T entity, T storedEntity, String fileNamePrefix, MultipartFile file, String newVirtualFileName) {
		this.fileNamePrefix = fileNamePrefix;
		this.newVirtualFileName = newVirtualFileName;
		this.fileLocation = buildFileLocation();
		entity.setId(storedEntity.getId());
		entity.setVirtualFileName(buildVirtualFileName(newVirtualFileName));
		entity.setOriginalFileName(GenericUtils.removeExtensionFromFileName(file.getOriginalFilename()));
		entity.setFileSystemPath(this.fileLocation.toString());
		entity.setFileNamePrefix(getFileNamePrefix());
		entity.setSize(file.getSize());
		entity.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
		return entity;
	}
	
	protected Path load(String fileName) {
		return FileUtils.load(getRootLocation(), fileName);
	}
	
	protected void store(MultipartFile file) {
		FileUtils.store(this.fileLocation, file, this.newVirtualFileName);
	}
	
	protected String getFileNamePrefix() {
		return fileNamePrefix;
	}
	
	protected String setFileNamePrefix(String fileNamePrefix) {
		return this.fileNamePrefix = fileNamePrefix;
	}
	
	protected Path getFileLocation() {
		return fileLocation;
	}

	protected void setFileLocation(Path fileLocation) {
		this.fileLocation = fileLocation;
	}

	protected String getNewVirtualFileName() {
		return newVirtualFileName;
	}

	protected void setNewVirtualFileName(String newVirtualFileName) {
		this.newVirtualFileName = newVirtualFileName;
	}

	protected String trimFileName(String originalFilename) {
		return originalFilename.replaceAll("\\s+","-");
	}
	
	protected String buildDefaultPrefix() {
		return GenericUtils.getStringFromDate(Calendar.getInstance().getTime(), GenericUtils.FULL_REVERSE_DATE_FORMATTER_FILENAME);
	}
	
	protected String buildVirtualFileName(String fileName) {
		return buildVirtualFileName(this.fileNamePrefix, fileName);
	}
	
	protected String buildVirtualFileName(String prefix, String fileName) {
		if (null == prefix || "".equals(prefix))
			return fileName;
		return new StringBuilder(prefix).append(fileNameSeparator).append(fileName).toString();
	}
	
	protected Path buildFileLocation() {
		return buildFileLocation(this.fileNamePrefix);
	}
	
	protected Path buildFileLocation(String prefix) {
		if (null == prefix || "".equals(prefix)) {
			this.fileLocation = this.rootLocation;
			return this.fileLocation;
		}
		return Paths.get(new StringBuilder(this.rootLocation.toString()).append("/").append(prefix).toString());
	}
	
	protected String cleanFileName(String prefix, String fileName) {
		if (null == prefix || "".equals(prefix))
			return fileName;
		return fileName.substring(prefix.length()+1);
	}
	
	protected void removeExistingFile(T storedEntity) {
		FileUtils.removeFile(storedEntity.getFileSystemPath(), cleanFileName(storedEntity.getFileNamePrefix(), storedEntity.getVirtualFileName()), true);
	}
	
}
