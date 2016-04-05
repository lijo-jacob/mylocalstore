package com.lijojacob.mls.productcatalog.index.dto;

import java.util.List;

import lombok.Data;

import com.lijojacob.mls.productcatalog.index.ProductDocument;

public @Data class DocumentResults {

	private List<ProductDocument> results;
	
	private int totalPages;
	
}
