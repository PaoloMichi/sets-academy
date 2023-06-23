package it.sets.common.repository;

import org.springframework.data.repository.NoRepositoryBean;

import it.sets.common.model.AbstractStringModel;

@NoRepositoryBean
public interface IStringRepository<T extends AbstractStringModel> extends IRepository<T, String> {

}
