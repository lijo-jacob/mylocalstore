package com.lijojacob.mls.productcatalog.index;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.common.params.FacetParams.FacetRangeOther;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.FilterQuery;
import org.springframework.data.solr.core.query.SimpleFilterQuery;
import org.springframework.data.solr.core.query.result.FacetPage;

import com.lijojacob.mls.productcatalog.index.dto.Filter;
import com.lijojacob.mls.productcatalog.index.dto.FilterCriteria;

public class ProductDocumentRepositoryImpl implements
		CustomProductDocumentRepository {
	
	@Resource
    private SolrTemplate solrTemplate;
	
	public FacetPage<ProductDocument> findByTextStartsWith(List<String> keywords, FilterCriteria filterCriteria, Pageable pageable) {
		AdvancedFacetQuery facetQuery = new AdvancedFacetQuery();
		Criteria searchConditions = createKeywordSearchConditions(keywords);
		facetQuery.addCriteria(searchConditions);
		setFilterCriteria(facetQuery, filterCriteria);
        setFacets(facetQuery);
        facetQuery.setPageRequest(pageable);
        facetQuery.addGroupByField(SearchableProductDefinition.PRODUCT_ID);
        FacetPage<ProductDocument> results = solrTemplate.queryForFacetPage(facetQuery, ProductDocument.class);
        return results;
	}
	
	
	public FacetPage<ProductDocument> findByAncestorCategories(String categoryId, FilterCriteria filterCriteria, Pageable pageable) {
        AdvancedFacetQuery facetQuery = new AdvancedFacetQuery();
        Criteria searchConditions = createSearchConditions(categoryId);
        facetQuery.addCriteria(searchConditions);
        setFilterCriteria(facetQuery, filterCriteria);
        setFacets(facetQuery);
        facetQuery.setPageRequest(pageable);
        facetQuery.addGroupByField(SearchableProductDefinition.PRODUCT_ID);
        FacetPage<ProductDocument> results = solrTemplate.queryForFacetPage(facetQuery, ProductDocument.class);
        return results;
	}


	private void setFacets(AdvancedFacetQuery facetQuery) {
		FacetOptions facetOptions = new FacetOptions( SearchableProductDefinition.PRODUCT_BRAND, SearchableProductDefinition.PRODUCT_MANUFACTURER_NAME, SearchableProductDefinition.PRODUCT_FIXED_PARENT_CATEGORIES_NAMES, SearchableProductDefinition.ON_SALE);
        facetQuery.setFacetOptions(facetOptions);
        List<RangeFacet> rangeFacets = createRangeFacets();
        facetQuery.setRangeFacets(rangeFacets);
	}


	private void setFilterCriteria(FacetQuery facetQuery, FilterCriteria filterCriteria) {
        if(null != filterCriteria && CollectionUtils.isNotEmpty(filterCriteria.getFilters())) {
        	Criteria filterConditions = createFilterConditions(filterCriteria);
	        FilterQuery filterQuery  = new SimpleFilterQuery();
	        filterQuery.addCriteria(filterConditions);
	        facetQuery.addFilterQuery(filterQuery);
        }
	}

	private List<RangeFacet> createRangeFacets() {
		List<RangeFacet> rangeFacets = new ArrayList<RangeFacet>();
        RangeFacet listPriceRange = new RangeFacet();
        listPriceRange.setField(SearchableProductDefinition.PRICE_FIELD_NAME);
        listPriceRange.setStart("10000.0");
        listPriceRange.setEnd("50000.0");
        listPriceRange.setGap("10000.0");
        List<FacetRangeOther> rangeOthers = new ArrayList<FacetRangeOther>();
        rangeOthers.add(FacetRangeOther.BEFORE);
        rangeOthers.add(FacetRangeOther.AFTER);
        listPriceRange.setOther(rangeOthers);
        rangeFacets.add(listPriceRange);
		return rangeFacets;
	}
	
	private Criteria createSearchConditions(String categoryId) {
        Criteria conditions = new Criteria("ancestorCategories").contains(categoryId);
        return conditions;
    }
	
	private Criteria createKeywordSearchConditions(List<String> keywords) {
        Criteria conditions = new Criteria(SearchableProductDefinition.KEYWORD_SEARCH_FIELD).startsWith(keywords);
        return conditions;
    }
	
	private Criteria createFilterConditions(FilterCriteria filterCriteria) {
		List<Filter> filters = filterCriteria.getFilters();
		Criteria conditions = new Criteria();
		for(Filter filter : filters) {
			conditions = conditions.and(filter.getName()).in(filter.getValue());
		}
        return conditions;
    }

}
