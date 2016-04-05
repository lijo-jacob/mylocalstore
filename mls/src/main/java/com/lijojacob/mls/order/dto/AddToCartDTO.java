package com.lijojacob.mls.order.dto;

import lombok.Data;

public @Data class AddToCartDTO {
	
	private String skuId;
	
	private Long quantity;

}
