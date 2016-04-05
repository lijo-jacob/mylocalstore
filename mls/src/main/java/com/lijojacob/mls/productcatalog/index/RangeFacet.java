package com.lijojacob.mls.productcatalog.index;

import java.util.List;

import lombok.Data;

import org.apache.solr.common.params.FacetParams.FacetRangeOther;

public @Data class RangeFacet {
	
	private String field;
	
	private String start;
	
	private String end;
	
	private String gap;
	
	private List<FacetRangeOther> other;

}
