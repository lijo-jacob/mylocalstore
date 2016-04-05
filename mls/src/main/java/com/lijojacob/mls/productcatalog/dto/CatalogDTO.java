package com.lijojacob.mls.productcatalog.dto;

import java.util.List;

import lombok.Data;

public @Data class CatalogDTO {
	
	private String id;
	
	private List<CategoryDTO> rootCategories;
	
	

}
