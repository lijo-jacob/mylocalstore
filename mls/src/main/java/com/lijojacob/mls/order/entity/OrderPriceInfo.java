package com.lijojacob.mls.order.entity;

import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.Description;

@Document
public @Data class OrderPriceInfo extends AmountInfo {
	
	@Description("Raw sub-total")
	private Double rawSubTotal;
	
	@Description("Shipping Amount")
	private Double shipping;
	
	@Description("Tax Amount")
	private Double tax;
	
}
