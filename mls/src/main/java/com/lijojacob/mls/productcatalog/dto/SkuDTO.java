package com.lijojacob.mls.productcatalog.dto;

import java.util.Map;

import lombok.Data;

public @Data class SkuDTO {

	private String id;
	
	private String displayName;
	
	private Map<String, String> media;
	
}
