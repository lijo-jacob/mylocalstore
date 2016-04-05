package com.lijojacob.mls.order.dto;

import lombok.Data;

public @Data class UpdateCartDTO {

	private String productId;
	
	private String catalogRefId;
	
	private Long quantity;
	
}
