package com.sports.bookingsystem.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sports.bookingsystem.entity.AbstractEntity;

@Transactional
public class BaseRepositoryImpl<T extends AbstractEntity, ID> extends SimpleJpaRepository<T, ID>
		implements BaseRepository<T, ID> {
	
	private final EntityManager entityManager;

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public T insert(T entity) {
		if (entity.getRecordUpdateTime() == null) {
			entity.setRecordCreateTime(new Date());
			entity.setRecordUpdateTime(new Date());	
		}
		return super.save(entity);
	}
	
	public List<T> findAll() {
		return super.findAll();
	}
	
	public List<T> queryEntityMgr(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	@Override
	public void softDeleteById(ID id) {
		super.deleteById(id);
	}
	
	@Override
	public Optional<T> findById(ID id) {
		T entity = entityManager.find(this.getDomainClass(), id);
		return Optional.ofNullable(entity);
	}
}
