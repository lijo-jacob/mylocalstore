package com.lijojacob.mls.productcatalog.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lijojacob.mls.common.repository.MlsRepository;
import com.lijojacob.mls.productcatalog.entity.Category;

@RepositoryRestResource
public interface CategoryRepository extends MlsRepository<Category, String> {

	// Combine a derived query with a full-text search
	  List<Category> findByDisplayNameOrderByScoreDesc(@Param("displayName") String displayName, TextCriteria criteria);
	  
	  List<Category> findByFixedParentCategoriesContaining(Category category);
	
}
