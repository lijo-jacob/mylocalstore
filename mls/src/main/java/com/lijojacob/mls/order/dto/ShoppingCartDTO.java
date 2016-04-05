package com.lijojacob.mls.order.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;

public @Data class ShoppingCartDTO {
	
	private List<CommerceItemDTO> commerceItems;
	
	private Date completionDate;
	
	private Date creationDate;
	
	private String creationSiteId;
	
	private String description;
	
	private boolean explicitlySaved;
	
	private Date lastModifiedDate;
	
	private OrderPriceInfoDTO priceInfo;
	
	private String userId;
	
	private String siteId;
	
	private Map<String, String> specialInstructions;
	
	private String state;
	
	private Date submittedDate;
	
	private TaxPriceInfoDTO taxPriceInfo;

}
