package it.sets.common.service;

import java.util.ArrayList;
import java.util.List;

import it.sets.common.exception.DuplicateElemException;
import it.sets.common.exception.MissingEntityFieldException;
import it.sets.common.model.AbstractLongModel;
import it.sets.common.repository.ILongRepository;

public abstract class AbstractLongService<T extends AbstractLongModel> extends AbstractService<T, Long> {

	protected abstract ILongRepository<T> getRepository();

	@Override
	public T create(T entity) {
		getLogger().info("Executing abstractLong create");
		if (null != entity.getId() && super.existsById(entity.getId()))
			throw new DuplicateElemException(getEntityTypeDesc(), entity.getId());
		return super.save(entity);
	}
	
	@Override
	public List<T> createAll(List<T> entities) {
		getLogger().info("Executing abstractLong createAll");
		if (null != entities && !entities.isEmpty()) {
			List<T> newEntities = new ArrayList<>();
			int i = 0;
			for (T elem : entities) {
				newEntities.add(this.create(elem));
				if (getLogger().isDebugEnabled()) getLogger().debug("ROW {}", String.valueOf(i));
				i++;
			}
			return newEntities;
		}
		throw new MissingEntityFieldException("Input list is NULL or Empty");
	}
	
	@Override
	public List<T> updateAll(List<T> entities) {
		getLogger().info("Executing abstractLong updateAll");
		if (null != entities && !entities.isEmpty()) {
			List<T> newEntities = new ArrayList<>();
			for (T elem : entities)
				newEntities.add(super.update(elem.getId(), elem));
			return newEntities;
		}
		throw new MissingEntityFieldException("Input list is NULL or Empty");
	}

}