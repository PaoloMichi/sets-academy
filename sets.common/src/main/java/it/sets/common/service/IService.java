package it.sets.common.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

public interface IService<T, R> {
	
	public List<T> findAll();
	
	public Page<T> findAllPaginated(Pageable page);
	
	public List<T> findByFilters(Map<String, Object> queryParamas);
	
	public Page<T> findByFiltersPaginated(Map<String, Object> queryParams, Pageable page);
	
	boolean existsById(R id);
	
	public Optional<T> findByIdOpz(R id);
	
	public T findById(R id);
	
	public List<T> findByIds(List<R> ids);
	
	public Page<T> findByIdsPaginated(List<R> ids, Pageable page);
	
	public Optional<T> findByVirtualFileNameOpz(String virtualFileName);
	
	public T findByVirtualFileName(String virtualFileName);
	
	public Resource serveFileById(R id);
	
	public Resource serveFileByVirtualFileName(String virtualFileName);
	
	public T create(T entity);
	
	public List<T> createAll(List<T> entities);
	
	public T createFile(T entity, MultipartFile file);
	
	public void deleteById(R id);
	
	public void deleteFileById(R id);
	
	public void deleteFilesByFilters(Map<String, Object> queryParams);
	
	public void deleteAll();
	
	public T update(R id, T entity);
	
	public List<T> updateAll(List<T> entities);
	
	public void updateFile(R id, T entity, MultipartFile file);
	
	public T patch(R id, T entity);
	
	public T patch(R id, Map<String, Object> fieldValues);
	
	public void patchFile(R id, MultiValueMap<String, Object> fieldValues, MultipartFile file);
	
}