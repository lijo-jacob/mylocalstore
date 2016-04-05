package com.lijojacob.mls.productcatalog.entity;

import lombok.Data;

import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lijojacob.mls.common.entity.BaseEntity;


@Document
public @Data class Banner extends BaseEntity {

	@TextIndexed
	private String displayName;
	
	@DBRef
	private MediaExternal mediaExternal;
}
