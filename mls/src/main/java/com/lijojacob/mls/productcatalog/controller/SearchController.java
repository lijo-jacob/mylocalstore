package com.lijojacob.mls.productcatalog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lijojacob.mls.productcatalog.index.ProductDocumentService;
import com.lijojacob.mls.productcatalog.index.dto.SearchCriteria;
import com.lijojacob.mls.productcatalog.index.dto.SearchResultDTO;

@RestController
public class SearchController {
	
	@Autowired
	private ProductDocumentService productDocumentService;
	
	@RequestMapping(value = "/search/keywords", method = RequestMethod.POST)
	public SearchResultDTO keywordSearch(@RequestBody SearchCriteria searchCriteria) {
		List<String> keywords = new ArrayList<String>();
		keywords.add(searchCriteria.getSearchTerm());
		Sort sort = new Sort("productDisplayName");
		PageRequest pageable = new PageRequest(searchCriteria.getPage(), searchCriteria.getSize(), sort);
		return productDocumentService.keywordSearch(keywords, searchCriteria.getFilterCriteria(), pageable);
	}

}
