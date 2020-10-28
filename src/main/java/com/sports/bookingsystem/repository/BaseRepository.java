package com.sports.bookingsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends Repository<T, ID> {
	public T insert(T entity);

	public void softDeleteById(ID id);
	
	public void deleteAll();
	
	public List<T> findAll();
	
	public Optional<T> findById(ID id);
}
