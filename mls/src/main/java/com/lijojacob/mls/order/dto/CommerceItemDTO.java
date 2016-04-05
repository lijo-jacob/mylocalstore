package com.lijojacob.mls.order.dto;

import lombok.Data;

public @Data class CommerceItemDTO {

	private String catalogRefId;
	
	private ItemPriceInfoDTO priceInfo;
	
	private String orderId;
	
	private String productId;
	
	private Long quantity;
	
	private String siteId;
	
	private String state;
	
	private int version;
	
}
