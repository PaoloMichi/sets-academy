package it.sets.common.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import it.sets.common.model.IModel;

@NoRepositoryBean
public interface IRepository<T extends IModel<R>, R>  extends JpaRepository<T, R> {

	Page<T> findByIdIn(List<R> ids, Pageable page);
	
	List<T> findByIdIn(List<R> ids);
	
}
