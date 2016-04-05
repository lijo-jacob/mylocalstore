package com.lijojacob.mls.productcatalog.index.dto;

import lombok.Data;

public @Data class SearchResultDTO {
	
	private String categoryName;
	
	private DocumentResults documents;
	
	private FacetResults facets;

}
