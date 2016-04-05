package com.lijojacob.mls.productcatalog.index.dto;


public enum FacetField {

	PRICE("listPrice", "Price"), BRAND("productBrand", "Brand"), MANUFACTURER("productManufacturer", "Manufacturer"), CATEGORY("ancestorCategoriesNames", "Category"), ON_SALE("onSale", "On Sale?");
	
	private String fieldName;
	
	private String displayName;
	
	private FacetField(String fieldName, String displayName) {
		this.fieldName = fieldName;
		this.displayName = displayName;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	
	
}
