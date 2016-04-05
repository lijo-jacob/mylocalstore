package com.lijojacob.mls.siteconfiguration.entity;

import java.util.List;

import lombok.Data;

import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lijojacob.mls.productcatalog.entity.Banner;
import com.lijojacob.mls.productcatalog.entity.Category;
import com.lijojacob.mls.productcatalog.entity.Product;

@Document
public @Data class CategoryLandingPageConfiguration extends PageConfiguration {

	@TextIndexed
	private String displayName;
	
	@DBRef(lazy = true)
	private List<Banner> banners;
	
	@DBRef(lazy = true)
	private List<Category> featuredCategories;
	
	@DBRef(lazy = true)
	private List<Product> featuredProducts;
	
}
