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

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;

import com.lijojacob.mls.productcatalog.index.dto.FilterCriteria;
import com.lijojacob.mls.productcatalog.index.dto.SearchResultDTO;

public interface ProductDocumentService {

	int DEFAULT_PAGE_SIZE = 3;

	public Page<ProductDocument> findByName(String searchTerm, Pageable pageable);

	public ProductDocument findById(String id);

	public FacetPage<ProductDocument> autocompleteNameFragment(String fragment, Pageable pageable);
	
	public ProductDocument save(ProductDocument productDocument);
	
	public Iterable<ProductDocument> save(Iterable<ProductDocument> productDocuments);
	
	public SearchResultDTO findByAncestorCategories(String categoryId, FilterCriteria filterCriteria, Pageable pageable);
	
	public SearchResultDTO keywordSearch(List<String> keywords, FilterCriteria filterCriteria, Pageable pageable);

}
