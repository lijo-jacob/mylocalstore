package com.lijojacob.mls.productcatalog.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lijojacob.mls.common.repository.MlsRepository;
import com.lijojacob.mls.productcatalog.entity.Category;
import com.lijojacob.mls.productcatalog.entity.Product;
import com.lijojacob.mls.productcatalog.entity.Sku;

@RepositoryRestResource
public interface ProductRepository extends MlsRepository<Product, String> {

	
	 List<Product> findByFixedParentCategoriesContaining(Category category);
	 
	 List<Product> findByChildSkusContaining(Sku sku);
	
}
