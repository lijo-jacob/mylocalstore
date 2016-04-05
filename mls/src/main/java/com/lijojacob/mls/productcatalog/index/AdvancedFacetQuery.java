package com.lijojacob.mls.productcatalog.index;

import java.util.List;

import lombok.Data;

import org.springframework.data.solr.core.query.SimpleFacetQuery;

public @Data class AdvancedFacetQuery extends SimpleFacetQuery {
	
	private List<RangeFacet> rangeFacets;

}
