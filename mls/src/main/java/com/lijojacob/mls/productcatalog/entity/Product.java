package com.lijojacob.mls.productcatalog.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
@Description("Product")
@ItemListingProperties({"displayName", "brand", "defaultParentCategory", "startDate", "endDate"})
public @Data class Product extends BaseEntity {

	@Description("Version")
	private int version;
	
	@Description("Display Name")
	@Order(2)
	@TextIndexed
	private String displayName;
	
	@Order(5)
	@Description("Creation Date")
	private Date creationDate;
	
	@Order(6)
	@Description("Start Date")
	private Date startDate;
	
	@Order(7)
	@Description("End Date")
	private Date endDate;
	
	@Order(3)
	@Description("Description")
	@TextIndexed
	private String description;

	@Order(4)
	@Description("Long Description")
	@TextIndexed
	private String longDescription;
	
	@Order(8)
	@Description("Non-returnable?")
	private Boolean nonreturnable;
	
	@Order(9)
	@Description("Discountable?")
	private Boolean discountable;
	
	@Order(10)
	@Description("Brand")
	private String brand;
	
	@Order(11)
	@Description("Disallow as recommendation?")
	private Boolean disallowAsRecommendation;
	
	@Order(12)
	@Description("Online only?")
	private Boolean onlineOnly;
	
	@Order(13)
	@Description("Keywords")
	@TextIndexed
	private List<String> keywords;
	
	@Order(14)
	@Description("Manufacturer")
	@DBRef
	private Manufacturer manufacturer;
	
	@Order(15)
	@Description("Default Parent Category")
	@DBRef
	private Category defaultParentCategory;
	
	@Order(16)
	@Description("Child SKUs")
	@DBRef(lazy = true)
	private List<Sku> childSkus;
	
	@Order(19)
	@Description("Images")
	private List<Image> images;
	
	
	@Order(20)
	@Description("Auxiliary Media")
	@DBRef(lazy = true)
	private Map<String, Media> auxiliaryMedia;
	
	@Order(21)
	@Description("Displayable SKU attributes")
	private List<String> displayableSkuAttributes;
	
	@Order(16)
	@Description("Fixed parent categories")
	@DBRef(lazy = true)
	private List<Category> fixedParentCategories;
	
	@Order(9)
	@Description("Sites")
	@DBRef(lazy = true)
	private List<SiteConfiguration> sites;
	
	
}
