package com.lijojacob.mls.order.dto;

import com.lijojacob.mls.productcatalog.dto.PriceListDTO;

import lombok.Data;

public @Data class ItemPriceInfoDTO extends AmountInfoDTO {

	private Boolean discountable;
	
	private Double listPrice;
	
	private Boolean onSale;
	
	private Double orderDiscountShare;
	
	private PriceListDTO priceList;
	
	private Long quantityAsQualifier;
	
	private Long quantityDiscounted;
	
	private Double rawTotalPrice;
	
	private Double salePrice;
	
	private int version;
}
