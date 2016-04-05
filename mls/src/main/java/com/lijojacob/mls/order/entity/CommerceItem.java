package com.lijojacob.mls.order.entity;

import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.Description;

import com.lijojacob.mls.common.entity.BaseEntity;

@Document
public @Data class CommerceItem extends BaseEntity {
	
	@Description("Catalog Reference ID")
	private String catalogRefId;
	
	@Description("Item Price Info")
	private ItemPriceInfo priceInfo;
	
	@Description("Order ID")
	private String orderId;
	
	@Description("Product ID")
	private String productId;
	
	@Description("Quantity")
	private Long quantity;
	
	@Description("Site ID")
	private String siteId;
	
	@Description("State")
	private String state;
	
	@Description("Version")
	private int version;

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommerceItem other = (CommerceItem) obj;
		if (catalogRefId == null) {
			if (other.catalogRefId != null)
				return false;
		} else if (!catalogRefId.equals(other.catalogRefId))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (siteId == null) {
			if (other.siteId != null)
				return false;
		} else if (!siteId.equals(other.siteId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((catalogRefId == null) ? 0 : catalogRefId.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((siteId == null) ? 0 : siteId.hashCode());
		return result;
	}

}
