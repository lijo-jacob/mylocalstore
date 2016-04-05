package com.lijojacob.mls.productcatalog.entity;

import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public @Data class MediaExternal extends Media {
	
	private String url;
	
	private String mimeType;

}
