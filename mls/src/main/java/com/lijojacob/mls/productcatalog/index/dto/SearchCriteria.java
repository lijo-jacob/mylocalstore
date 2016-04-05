package com.lijojacob.mls.productcatalog.index.dto;

import lombok.Data;

public @Data class SearchCriteria {
	
	private String searchTerm;
	
	private String categoryId;
	
	private String siteId;
	
	private int page;
	
	private int size;
	
	private FilterCriteria filterCriteria;

}
