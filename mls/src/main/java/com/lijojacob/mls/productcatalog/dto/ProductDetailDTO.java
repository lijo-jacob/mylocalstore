package com.lijojacob.mls.productcatalog.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;

public @Data class ProductDetailDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String skuId;
	
	private String productId;
	
	private String displayName;
	
	private String productDisplayName;
	
	private String description;
	
	private String productDescription;
	
	private String productLongDescription;
	
	private String productBrand;
	
	private Boolean productDisallowAsRecommendation;
	
	private Boolean productNonreturnable;

	private Boolean nonreturnable;
	
	private Boolean productDiscountable;
	
	private Boolean productOnlineOnly;

	private Boolean discountable;
	
	private Set<String> productKeywords;
	
	private Map<String, String> dynamicAttributes;
	
	private String template;
	
	private String thumbnailImage;

	private String smallImage;
	
	private String largeImage;
	
	private Map<String, String> auxiliaryMedia;
	
	private List<String> productImages;

	private Map<String, String> productAuxiliaryMedia;
	
	private Double quantity;
	
	private	String unitOfMeasure;
	
	private Date productStartDate;
	
	private Date productEndDate;
	
	private Date startDate;
	
	private Date endDate;
	
	private Set<String> sites;
	
	private Set<String> productCatalogs;
	
	private String productManufacturer;
	
	private Set<String> childSkus;
	
	private String productDefaultParentCategory;
	
	private Set<String> productFixedParentCategories;
	
	private Set<String> ancestorCategories;
	
	private Set<String> features;
	
	private Double listPrice;
	
	private Double salePrice;
	
	private Boolean onSale;
	
	public List<ImageDTO> getProductImages() {
		List<ImageDTO> images = new ArrayList<ImageDTO>();
		if(null != this.productImages) {
			for(String imageString : this.productImages) {
				String[] subImages = imageString.split(", ");
				ImageDTO image = new ImageDTO();
				for(String subImage : subImages) {
					String imageKey = subImage.split("=")[0];
					String imageValue = subImage.split("=")[1];
					if(imageKey.equals("thumbnail")) {
						image.setThumbnail(imageValue);
					} else if(imageKey.equals("small")) {
						image.setSmall(imageValue);
					} else if(imageKey.equals("large")) {
						image.setLarge(imageValue);
					}
				}
				images.add(image);
			}
		}
		return images;
	}
	
}
