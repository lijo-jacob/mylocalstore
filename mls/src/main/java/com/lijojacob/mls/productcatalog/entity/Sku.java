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

import com.lijojacob.mls.common.entity.BaseEntity;
import com.lijojacob.mls.siteconfiguration.entity.SiteConfiguration;

@Document
public @Data class Sku extends BaseEntity {
	
	private int version;
	
	@TextIndexed
	private String displayName;
	
	private Date creationDate;
	
	private Date startDate;
	
	private Date endDate;
	
	@TextIndexed
	private String description;
	
	private Double listPrice;
	
	private Double salePrice;
	
	private Boolean onSale;
	
	private Boolean nonreturnable;
	
	private Boolean discountable;
	
	@TextIndexed
	private String manufacturerPartNumber;
	
	private Boolean onlineOnly;
	
	private Map<String, String> dynamicAttributes;
	
	@DBRef
	private Media template;
	
	@DBRef
	private Media thumbnailImage;

	@DBRef
	private Media smallImage;
	
	@DBRef
	private Media largeImage;
	
	@DBRef(lazy = true)
	private Map<String, Media> auxiliaryMedia;
	
	private Double quantity;
	
	private	UnitOfMeasure unitOfMeasure;
	
	@Order(9)
	@Description("Sites")
	@DBRef(lazy = true)
	private List<SiteConfiguration> sites;
	
	

}
