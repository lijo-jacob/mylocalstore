package com.lijojacob.mls.order.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.Description;

import com.lijojacob.mls.common.annotation.ItemListingProperties;
import com.lijojacob.mls.common.annotation.PropertyOrder;
import com.lijojacob.mls.common.entity.BaseEntity;

@Document
@Description("Order")
@PropertyOrder({"userProfile", "state", "site", "creationDate", "submittedDate", "completionDate"})
@ItemListingProperties({"userProfile", "state", "site", "creationDate", "submittedDate", "completionDate"})
public @Data class Order extends BaseEntity {
	
	@Description("Commerce Items")
	private List<CommerceItem> commerceItems;
	
	@Description("Completion Date")
	private Date completionDate;
	
	@Description("Creation Date")
	private Date creationDate;
	
	@Description("Creation Site ID")
	private String creationSiteId;
	
	@Description("Description")
	private String description;
	
	@Description("Explicitly saved by user")
	private boolean explicitlySaved;
	
	@Description("Last modified date")
	private Date lastModifiedDate;
	
	@Description("Price Info")
	private OrderPriceInfo priceInfo;
	
	@Description("User ID")
	private String userId;
	
	@Description("Site ID")
	private String siteId;
	
	@Description("Special Instructions")
	private Map<String, String> specialInstructions;
	
	@Description("State")
	private String state;
	
	@Description("Submitted Date")
	private Date submittedDate;
	
	@Description("Tax Price Info")
	private TaxPriceInfo taxPriceInfo;

}

