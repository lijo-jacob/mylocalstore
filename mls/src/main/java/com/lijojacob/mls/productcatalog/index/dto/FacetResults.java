package com.lijojacob.mls.productcatalog.index.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

public @Data class FacetResults {
	
	private Map<String, List<Facet>> results;

}
