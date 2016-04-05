package com.lijojacob.mls.productcatalog.entity;

import java.util.Date;

import lombok.Data;

import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lijojacob.mls.common.entity.BaseEntity;

@Document
public @Data class Media extends BaseEntity {
	
	@TextIndexed
	private String displayName;
	
	private int version;
	
	private Date creationDate;
	
	private Date startDate;
	
	private Date endDate;
	
	@TextIndexed
	private String description;
	
	private String path;
	
	@DBRef
	private Folder parentFolder;
	
}
