package it.sets.common.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import it.sets.common.exception.EntityNotFoundException;
import it.sets.common.exception.MissingEntityFieldException;
import it.sets.common.exception.RepositoryException;
import it.sets.common.model.ERepositoryMethod;
import it.sets.common.model.IModel;
import it.sets.common.repository.IRepository;

public abstract class AbstractService<T extends IModel<R>, R> implements IService<T, R> {
	
	protected abstract String getEntityTypeDesc();
	
	protected abstract Logger getLogger();

	protected abstract IRepository<T, R> getRepository();
	
	private IRepository<T, R> getRepositoryWrapper() {
		checkRepository(getRepository());
		return getRepository();
	}

	@Override
	public List<T> findAll() throws RepositoryException {
		getLogger().info("Executing abstract findAll");
		try {
			return getRepositoryWrapper().findAll();
		} catch (Exception e) {
			throw new RepositoryException(getEntityTypeDesc(), ERepositoryMethod.FIND_ALL, e);
		}
	}

	@Override
	public Page<T> findAllPaginated(Pageable page) {
		getLogger().info("Executing abstract findAllPaginated");
		try {
			return getRepositoryWrapper().findAll(page);
		} catch (Exception e) {
			throw new RepositoryException(getEntityTypeDesc(), ERepositoryMethod.FIND_ALL, e);
		}
	}
	
	@Override
	public boolean existsById(R id) {
		getLogger().info("Executing abstract existsById");
		try {
			return getRepositoryWrapper().findById(id).isPresent();
		} catch (Exception e) {
			throw new RepositoryException(getEntityTypeDesc(), ERepositoryMethod.FIND_BY_ID, e);
		}
	}
	
	@Override
	public Optional<T> findByIdOpz(R id) {
		getLogger().info("Executing abstract findByIdOpz");
		try {
			return getRepositoryWrapper().findById(id);
		} catch (Exception e) {
			throw new RepositoryException(getEntityTypeDesc(), ERepositoryMethod.FIND_BY_ID, e);
		}
	}
	
	@Override
	public T findById(R id) {
		getLogger().info("Executing abstract findById");
		Optional<T> entityOpt = Optional.empty();
		try {
			entityOpt = getRepositoryWrapper().findById(id);
		} catch (Exception e) {
			throw new RepositoryException(getEntityTypeDesc(), ERepositoryMethod.FIND_BY_ID, e);
		}
		if (entityOpt.isPresent()) {
			return entityOpt.get();
		} else {
			throw new EntityNotFoundException(getEntityTypeDesc(), id);
		}
	}
	
	@Override
	public List<T> findByIds(List<R> ids) {
		getLogger().info("Executing abstract findByIds");
		try {
			return getRepositoryWrapper().findByIdIn(ids);
		} catch (Exception e) {
			throw new RepositoryException(getEntityTypeDesc(), ERepositoryMethod.FIND_BY_ID_IN, e);
		}
	}
	
	@Override
	public Page<T> findByIdsPaginated(List<R> ids, Pageable page) {
		getLogger().info("Executing abstract findByIdsPaginated");
		try {
			return getRepositoryWrapper().findByIdIn(ids, page);
		} catch (Exception e) {
			throw new RepositoryException(getEntityTypeDesc(), ERepositoryMethod.FIND_BY_ID_IN, e);
		}
	}

	@Override
	public void deleteById(R id) {
		getLogger().info("Executing abstract deleteById");
		try {
			getRepositoryWrapper().deleteById(id);
		} catch (Exception e) {
			throw new RepositoryException(getEntityTypeDesc(), ERepositoryMethod.DELETE_BY_ID, e);
		}
	}

	@Override
	public T update(R id, T entity) {
		getLogger().info("Executing abstract update");
		if (null != id) {
			entity.setId(id);
			return save(entity);
		} else {
			throw new MissingEntityFieldException(getEntityTypeDesc(), "id");
		}
	}
	
	@Override
	public List<T> findByFilters(Map<String, Object> filtersMap) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Page<T> findByFiltersPaginated(Map<String, Object> filtersMap, Pageable page) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public T createFile(T entity, MultipartFile file) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public List<T> createAll(List<T> entity) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Optional<T> findByVirtualFileNameOpz(String virtualFileName) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public T findByVirtualFileName(String virtualFileName) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Resource serveFileByVirtualFileName(String virtualFilename) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Resource serveFileById(R id) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void deleteFilesByFilters(Map<String, Object> queryParams) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void deleteFileById(R id) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void deleteAll() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public List<T> updateAll(List<T> entity) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void updateFile(R id, T entity, MultipartFile file) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public T patch(R id, T entity) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public T patch(R id, Map<String, Object> fieldValues) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void patchFile(R id, MultiValueMap<String, Object> fieldValues, MultipartFile file) {
		throw new UnsupportedOperationException();
	}
	
	public T save(T entity) {
		getLogger().debug("Executing abstract save");
		try {
			return getRepositoryWrapper().save(entity);
		} catch (RuntimeException e) {
			throw new RepositoryException(getEntityTypeDesc(), ERepositoryMethod.SAVE, e);
		}
	}
	
	public List<T> saveAll(List<T> entities) {
		getLogger().debug("Executing abstract saveAll");
		try {
			return getRepositoryWrapper().saveAll(entities);
		} catch (RuntimeException e) {
			throw new RepositoryException(getEntityTypeDesc(), ERepositoryMethod.SAVE_ALL, e);
		}
	}
	
	protected void checkRepository(IRepository<T, R> repository) {
		if (null == repository) 
			throw new RuntimeException("Repository is NULL");
	}
	
}

