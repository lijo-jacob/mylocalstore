package com.lijojacob.mls.productcatalog.index;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument
public @Data class ProductDocument {
	
	@Id
	@Indexed
	private String id;
	
	@Indexed(name = "skuId")
	private String skuId;
	
	@Indexed(name="productId")
	private String productId;
	
	@Indexed(name = "displayName", searchable = true)
	private String displayName;
	
	@Indexed(name = "productDisplayName", searchable = true)
	private String productDisplayName;
	
	@Indexed(name = "description", searchable = true)
	private String description;
	
	@Indexed(name = "productDescription", searchable = true)
	private String productDescription;
	
	@Indexed(name = "productLongDescription", searchable = true)
	private String productLongDescription;
	
	@Indexed(name = "productBrand", searchable = true)
	private String productBrand;
	
	@Indexed(name = "productDisallowAsRecommendation")
	private Boolean productDisallowAsRecommendation;
	
	@Indexed(name = "productNonreturnable")
	private Boolean productNonreturnable;

	@Indexed(name = "nonreturnable")
	private Boolean nonreturnable;
	
	@Indexed(name = "productDiscountable")
	private Boolean productDiscountable;
	
	@Indexed(name = "productOnlineOnly")
	private Boolean productOnlineOnly;

	@Indexed(name = "discountable")
	private Boolean discountable;
	
	@Indexed(name = "productKeywords")
	private Set<String> productKeywords;
	
	@Indexed(name = "dynamicAttributes")
	private Map<String, String> dynamicAttributes;
	
	@Indexed(name = "template")
	private String template;
	
	@Indexed(name = "auxiliaryMedia")
	private Map<String, String> auxiliaryMedia;

	@Indexed(name = "productImages")
	private List<String> productImages;
	
	@Indexed(name = "productAuxiliaryMedia")
	private Map<String, String> productAuxiliaryMedia;
	
	@Indexed(name = "quantity")
	private Double quantity;
	
	@Indexed(name = "unitOfMeasure")
	private	String unitOfMeasure;
	
	@Indexed(name = "productStartDate")
	private Date productStartDate;
	
	@Indexed(name = "productEndDate")
	private Date productEndDate;
	
	@Indexed(name = "startDate")
	private Date startDate;
	
	@Indexed(name = "endDate")
	private Date endDate;
	
	@Indexed(name = "sites")
	private Set<String> sites;
	
	@Indexed(name = "productCatalogs")
	private Set<String> productCatalogs;
	
	@Indexed(name = "productManufacturer", searchable = true)
	private String productManufacturer;
	
	@Indexed(name = "productManufacturerName", searchable = true)
	private String productManufacturerName;
	
	@Indexed(name = "childSkus")
	private Set<String> childSkus;
	
	@Indexed(name = "productDefaultParentCategory")
	private String productDefaultParentCategory;
	
	@Indexed(name = "productFixedParentCategories")
	private Set<String> productFixedParentCategories;
	
	@Indexed(name = "ancestorCategories")
	private Set<String> ancestorCategories;
	
	@Indexed(name = "productDefaultParentCategoryName")
	private String productDefaultParentCategoryName;
	
	@Indexed(name = "productFixedParentCategoriesNames")
	private Set<String> productFixedParentCategoriesNames;
	
	@Indexed(name = "ancestorCategoriesNames")
	private Set<String> ancestorCategoriesNames;
	
	@Indexed(name = "features")
	private Set<String> features;
	
	@Indexed(name = "listPrice")
	private Double listPrice;
	
	@Indexed(name = "salePrice")
	private Double salePrice;
	
	@Indexed(name = "onSale")
	private Boolean onSale;
	
}
