package com.lijojacob.mls.productcatalog.index;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetFieldEntry;
import org.springframework.data.solr.core.query.result.PageKey;
import org.springframework.data.solr.core.query.result.SolrResultPage;

public class AdvancedSolrResultPage extends SolrResultPage {
	
	private Map<PageKey, Page<FacetFieldEntry>> rangeFacetResultPages = new LinkedHashMap<PageKey, Page<FacetFieldEntry>>(1);
	

	public AdvancedSolrResultPage(List content) {
		super(content);
	}

	public AdvancedSolrResultPage(List content, Pageable pageable, long total,
			Float maxScore) {
		super(content, pageable, total, maxScore);
	}

}
