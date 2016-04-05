package com.lijojacob.mls.siteconfiguration.entity;

import lombok.Data;

import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.Description;

import com.lijojacob.mls.common.annotation.ItemListingProperties;
import com.lijojacob.mls.common.annotation.PropertyOrder;
import com.lijojacob.mls.common.entity.BaseEntity;
import com.lijojacob.mls.productcatalog.entity.Catalog;

@Document
@Description("Site Configuration")
@PropertyOrder({"name", "siteBaseUrl", "defaultCatalog", "homePageConfiguration", "categoryLandingPageConfiguration"})
@ItemListingProperties({"name", "siteBaseUrl"})
public @Data class SiteConfiguration extends BaseEntity {
	
	@TextIndexed
	private String displayName;

	@Description("Site Base URL")
	private String siteBaseUrl;
	
	@DBRef
	@Description("Default Catalog")
	private Catalog defaultCatalog;
	
	@DBRef
	@Description("Home Page")
	private HomePageConfiguration homePageConfiguration;
	
	@DBRef
	@Description("Category Landing Page")
	private CategoryLandingPageConfiguration categoryLandingPageConfiguration;
	
}
