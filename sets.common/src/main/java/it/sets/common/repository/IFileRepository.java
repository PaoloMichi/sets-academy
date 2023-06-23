package it.sets.common.repository;

import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;

import it.sets.common.model.AbstractFileModel;

@NoRepositoryBean
public interface IFileRepository<T extends AbstractFileModel> extends ILongRepository<T> {
	 
	Optional<T> findByVirtualFileName(String virtualFileName);
	
}
