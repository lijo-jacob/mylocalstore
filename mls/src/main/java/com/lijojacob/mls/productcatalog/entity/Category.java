package com.lijojacob.mls.productcatalog.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;

import org.springframework.core.annotation.Order;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;
import org.springframework.data.rest.core.annotation.Description;

import com.lijojacob.mls.common.annotation.ItemListingProperties;
import com.lijojacob.mls.common.annotation.PropertyOrder;
import com.lijojacob.mls.common.entity.BaseEntity;
import com.lijojacob.mls.siteconfiguration.entity.SiteConfiguration;

@Document
@Description("Category")
@PropertyOrder({"displayName", "description", "longDescription", "startDate", "endDate", "keywords"})
@ItemListingProperties({"displayName", "startDate", "endDate"})
public @Data class Category extends BaseEntity {
	
	@Description("version")
	private int version;
	
	@Order(2)
	@Description("Display name")
	@TextIndexed
	private String displayName;
	
	@Order(8)
	@Description("Creation date")
	private Date creationDate;
	
	@Order(9)
	@Description("Start date")
	private Date startDate;
	
	@Order(10)
	@Description("End date")
	private Date endDate;
	
	@Order(3)
	@Description("Description")
	@TextIndexed
	private String description;
	
	@Order(4)
	@Description("Long description")
	@TextIndexed
	private String longDescription;
	
	@Order(5)
	@Description("Catalog")
	@DBRef
	private Catalog catalog;
	
	@Order(7)
	@Description("Keywords")
	@TextIndexed
	private List<String> keywords;
	
	@Order(6)
	@Description("Default parent category")
	@DBRef
	private Category defaultParentCategory;
	
	@Order(11)
	@Description("Template")
	@DBRef
	private Media template;
	
	@Order(12)
	@Description("Thumbnail image")
	@DBRef
	private Media thumbnailImage;

	@Order(13)
	@Description("Small image")
	@DBRef
	private Media smallImage;
	
	@Order(14)
	@Description("Large image")
	@DBRef
	private Media largeImage;
	
	@Order(15)
	@Description("Auxiliary media")
	@DBRef(lazy = true)
	private Map<String, Media> auxiliaryMedia;	
	
	@Order(7)
	@Description("Fixed parent categories")
	@DBRef(lazy = true)
	private List<Category> fixedParentCategories;
	
	@Order(8)
	@Description("Ancestor categories")
	@Transient
	private List<Category> ancestorCategories;
	
	@Order(9)
	@Description("Fixed child categories")
	@Transient
	private List<Category> fixedChildCategories;
	
	@Order(9)
	@Description("Sites")
	@DBRef(lazy = true)
	private List<SiteConfiguration> sites;
	
	@TextScore
	private Float score;
	
}
