package com.lijojacob.mls.common.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.lijojacob.mls.common.entity.BaseEntity;


public interface MlsRepository<T extends BaseEntity, ID extends Serializable> extends MongoRepository<T, ID> {
	
	
	  // Paginate over a full-text search result
	  Page<T> findAllBy(@Param("words") TextCriteria criteria, Pageable pageable);

}
