package it.sets.common.repository;

import org.springframework.data.repository.NoRepositoryBean;

import it.sets.common.model.AbstractLongModel;

@NoRepositoryBean
public interface ILongRepository<T extends AbstractLongModel> extends IRepository<T, Long> {

}
