package com.lijojacob.mls.productcatalog.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
@EqualsAndHashCode(callSuper = true)
public @Data class Image extends Media {
	
	private String alt;
	
	private String caption;
	
	private String thumbnail;
	
	private String small;
	
	private String large;

}
