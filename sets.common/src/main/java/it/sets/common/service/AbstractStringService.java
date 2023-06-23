package it.sets.common.service;

import java.util.List;

import it.sets.common.exception.DuplicateElemException;
import it.sets.common.exception.MissingEntityFieldException;
import it.sets.common.model.AbstractStringModel;
import it.sets.common.repository.IStringRepository;

public abstract class AbstractStringService<T extends AbstractStringModel> extends AbstractService<T, String> {

	protected abstract IStringRepository<T> getRepository();

	@Override
	public T create(T entity) {
		getLogger().info("Executing abstractString create");
		if (null == entity.getId())
			throw new MissingEntityFieldException(getEntityTypeDesc(), "id");
		if (super.existsById(entity.getId()))
			throw new DuplicateElemException(getEntityTypeDesc(), entity.getId());
		return super.save(entity);
	}
	
	@Override
	public List<T> createAll(List<T> entities) {
		getLogger().info("Executing abstractString createAll");
		if (null != entities && !entities.isEmpty())
			return saveAll(entities);
		throw new MissingEntityFieldException("Input list is NULL or Empty");
	}

}