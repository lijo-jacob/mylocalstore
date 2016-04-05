package com.lijojacob.mls.order.dto;

import lombok.Data;

import com.lijojacob.mls.order.entity.Order;

public @Data class ShoppingCart {
	
	private Order currentOrder;
	
	public int getNumberOfItems() {
		return currentOrder.getCommerceItems().size();
	}

}
