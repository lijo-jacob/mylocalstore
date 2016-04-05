package com.lijojacob.mls.productcatalog.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

public @Data class ProductDTO {
	
	private String id;
	
	private String displayName;
	
	private Map<String, String> media;
	
	private List<SkuDTO> childSkus;

}
