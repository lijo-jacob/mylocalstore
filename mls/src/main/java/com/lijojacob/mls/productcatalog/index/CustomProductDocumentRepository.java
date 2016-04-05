package com.lijojacob.mls.productcatalog.index;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;

import com.lijojacob.mls.productcatalog.index.dto.FilterCriteria;


public interface CustomProductDocumentRepository {

	public FacetPage<ProductDocument> findByAncestorCategories(String categoryId, FilterCriteria filterCriteria, Pageable pageable);
	
	public FacetPage<ProductDocument> findByTextStartsWith(List<String> keywords, FilterCriteria filterCriteria, Pageable pageable);
	
	
}
