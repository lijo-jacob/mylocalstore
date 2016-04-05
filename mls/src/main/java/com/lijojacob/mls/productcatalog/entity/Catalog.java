package com.lijojacob.mls.productcatalog.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.Data;

import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.Description;

import com.lijojacob.mls.common.annotation.ItemListingProperties;
import com.lijojacob.mls.common.entity.BaseEntity;
import com.lijojacob.mls.siteconfiguration.entity.SiteConfiguration;

@Document
@Description("Catalog")
@ItemListingProperties({"displayName", "description", "longDescription", "creationDate"})
public @Data class Catalog extends BaseEntity {
	
	@Description("Version")
	private int version;
	
	@Order(3)
	@Description("Description")
	@TextIndexed
	private String description;
	
	@Order(4)
	@Description("Long Description")
	@TextIndexed
	private String longDescription;
	
	@Order(2)
	@Description("Display Name")
	@TextIndexed
	private String displayName;
	
	@Order(5)
	@Description("Creation Date")
	private Date creationDate;
	
	@Order(6)
	@Description("Last Modified Date")
	private Date lastModifiedDate;
	
	@Order(7)
	@Description("Root categories")
	@DBRef(lazy = true)
	private List<Category> rootCategories;

	@Order(8)
	@Description("Root sub-catalogs")
	@DBRef(lazy = true)
	private List<Catalog> rootSubCatalogs;
	
	@Order(9)
	@Description("Sites")
	@DBRef(lazy = true)
	private Set<SiteConfiguration> sites;
	
}
