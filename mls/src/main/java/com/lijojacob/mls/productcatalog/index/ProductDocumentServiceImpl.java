/*
 * Copyright 2012 - 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lijojacob.mls.productcatalog.index;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.Field;
import org.springframework.data.solr.core.query.result.FacetFieldEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.stereotype.Service;

import com.lijojacob.mls.productcatalog.entity.Category;
import com.lijojacob.mls.productcatalog.index.dto.DocumentResults;
import com.lijojacob.mls.productcatalog.index.dto.Facet;
import com.lijojacob.mls.productcatalog.index.dto.FacetResults;
import com.lijojacob.mls.productcatalog.index.dto.FilterCriteria;
import com.lijojacob.mls.productcatalog.index.dto.SearchResultDTO;
import com.lijojacob.mls.productcatalog.repository.CategoryRepository;


@Service
class ProductDocumentServiceImpl implements ProductDocumentService {

	private static final Pattern IGNORED_CHARS_PATTERN = Pattern.compile("\\p{Punct}");

	@Autowired
	private ProductDocumentRepository productDocumentRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Page<ProductDocument> findByName(String searchTerm, Pageable pageable) {
		if (StringUtils.isBlank(searchTerm)) {
			return productDocumentRepository.findAll(pageable);
		}

		return productDocumentRepository.findByDisplayNameIn(splitSearchTermAndRemoveIgnoredCharacters(searchTerm), pageable);
	}

	@Override
	public ProductDocument findById(String id) {
		return productDocumentRepository.findOne(id);
	}

	@Override
	public FacetPage<ProductDocument> autocompleteNameFragment(String fragment, Pageable pageable) {
		if (StringUtils.isBlank(fragment)) {
			return new SolrResultPage<ProductDocument>(Collections.<ProductDocument> emptyList());
		}
		return productDocumentRepository.findByDisplayNameStartsWith(splitSearchTermAndRemoveIgnoredCharacters(fragment), pageable);
	}
	
	@Override
	public SearchResultDTO findByAncestorCategories(String categoryId, FilterCriteria filterCriteria, Pageable pageable) {
		FacetPage<ProductDocument> productDocumentsFacetPage = productDocumentRepository.findByAncestorCategories(categoryId, filterCriteria, pageable);
		SearchResultDTO result = processSearchResults(productDocumentsFacetPage);
		Category category = categoryRepository.findOne(categoryId);
		result.setCategoryName(category.getDisplayName());
		return result;
	}
	
	@Override
	public SearchResultDTO keywordSearch(List<String> keywords, FilterCriteria filterCriteria, Pageable pageable) {
		FacetPage<ProductDocument> productDocumentsFacetPage = productDocumentRepository.findByTextStartsWith(keywords, filterCriteria, pageable);
		SearchResultDTO result = processSearchResults(productDocumentsFacetPage);
		return result;
	}

	private SearchResultDTO processSearchResults(
			FacetPage<ProductDocument> productDocumentsFacetPage) {
		SearchResultDTO result = new SearchResultDTO();
		DocumentResults documentResults = new DocumentResults();
		documentResults.setResults(productDocumentsFacetPage.getContent());
		documentResults.setTotalPages(productDocumentsFacetPage.getTotalPages());
		result.setDocuments(documentResults);
		if(CollectionUtils.isNotEmpty(productDocumentsFacetPage.getFacetFields())) {
			Map<String, List<Facet>> facetMap = new HashMap<String, List<Facet>>();
			FacetResults facetResults = new FacetResults();
			for(Field facetField : productDocumentsFacetPage.getFacetFields()) {
				if(null != productDocumentsFacetPage.getFacetResultPage(facetField)) {
					List<FacetFieldEntry> facetFieldEntryList = productDocumentsFacetPage.getFacetResultPage(facetField).getContent();
					List<Facet> facets = new ArrayList<Facet>();
					for(FacetFieldEntry facetFieldEntry : facetFieldEntryList) {
						Facet facet = new Facet();
						facet.setValue(facetFieldEntry.getValue());
						facet.setCount(facetFieldEntry.getValueCount());
						facets.add(facet);
					}
					facetMap.put(facetField.getName(), facets);
				}
			}
			facetResults.setResults(facetMap);
			result.setFacets(facetResults);
		}
		return result;
	}

	private Collection<String> splitSearchTermAndRemoveIgnoredCharacters(String searchTerm) {
		String[] searchTerms = StringUtils.split(searchTerm, " ");
		List<String> result = new ArrayList<String>(searchTerms.length);
		for (String term : searchTerms) {
			if (StringUtils.isNotEmpty(term)) {
				result.add(IGNORED_CHARS_PATTERN.matcher(term).replaceAll(" "));
			}
		}
		return result;
	}
	
	@Override
	public ProductDocument save(ProductDocument productDocument) {
		return productDocumentRepository.save(productDocument);
	}

	@Autowired
	public void setProductDocumentRepository(ProductDocumentRepository productDocumentRepository) {
		this.productDocumentRepository = productDocumentRepository;
	}

	@Override
	public Iterable<ProductDocument> save(Iterable<ProductDocument> productDocuments) {
		return productDocumentRepository.save(productDocuments);
	}

}
