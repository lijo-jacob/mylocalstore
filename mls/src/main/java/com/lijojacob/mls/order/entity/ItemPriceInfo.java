package com.lijojacob.mls.order.entity;

import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.Description;

import com.lijojacob.mls.priceLists.entity.PriceList;

@Document
public @Data class ItemPriceInfo extends AmountInfo {

	@Description("Discountable?")
	private Boolean discountable;
	
	@Description("List Price")
	private Double listPrice;
	
	@Description("On Sale?")
	private Boolean onSale;
	
	@Description("Order Discount Share")
	private Double orderDiscountShare;
	
	@Description("Price List")
	private PriceList priceList;
	
	@Description("Quantity as qualifier")
	private Long quantityAsQualifier;
	
	@Description("Quantity discounted")
	private Long quantityDiscounted;
	
	@Description("Raw total price")
	private Double rawTotalPrice;
	
	@Description("Sale price")
	private Double salePrice;
	
}
