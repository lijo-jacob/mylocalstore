package com.lijojacob.mls.productcatalog.index;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lijojacob.mls.productcatalog.entity.Category;
import com.lijojacob.mls.productcatalog.entity.Image;
import com.lijojacob.mls.productcatalog.entity.Media;
import com.lijojacob.mls.productcatalog.entity.Product;
import com.lijojacob.mls.productcatalog.entity.Sku;
import com.lijojacob.mls.productcatalog.repository.CategoryRepository;
import com.lijojacob.mls.productcatalog.repository.ProductRepository;
import com.lijojacob.mls.siteconfiguration.entity.SiteConfiguration;

@Component
public class ProductDocumentFactory {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<ProductDocument> createProductDocuments(Sku sku) {
		List<ProductDocument> productDocuments = new ArrayList<ProductDocument>();
		List<Product> productsToSave = productRepository.findByChildSkusContaining(sku);
		for(Product product : productsToSave) {
			productDocuments.add(createProductDocument(product, sku));
		}
		return productDocuments;
	}
	
	public List<ProductDocument> createProductDocuments(Product product) {
		List<ProductDocument> productDocuments = new ArrayList<ProductDocument>();
		List<Sku> childSkus = product.getChildSkus();
		if(childSkus != null) {
			for(Sku sku : childSkus) {
				productDocuments.add(createProductDocument(product, sku));
			}
		}
		return productDocuments;
	}
	
	public ProductDocument createProductDocument(Product product, Sku sku) {
		ProductDocument productDocument = new ProductDocument();
		String id = generateId(product, sku);
		productDocument.setId(id);
		copyProductProperties(product, productDocument);
		copySkuProperties(sku, productDocument);
		return productDocument;
	}

	private String generateId(Product product, Sku sku) {
		String id = null;
		try {
			StringBuilder idBuilder = new StringBuilder();
			idBuilder.append(product.getId()).append("-").append(sku.getId());
			byte[] bytesOfMessage = idBuilder.toString().getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] theDigest = md.digest(bytesOfMessage);
			BigInteger bigInt = new BigInteger(1, theDigest);
			id = bigInt.toString(16);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	private void copySkuProperties(Sku sku, ProductDocument productDocument) {
		productDocument.setSkuId(sku.getId());
		productDocument.setDescription(sku.getDescription());
		productDocument.setDiscountable(sku.getDiscountable());
		productDocument.setDisplayName(sku.getDisplayName());
		productDocument.setEndDate(sku.getEndDate());
		productDocument.setListPrice(sku.getListPrice());
		productDocument.setNonreturnable(sku.getNonreturnable());
		productDocument.setOnSale(sku.getOnSale());
		productDocument.setQuantity(sku.getQuantity());
		productDocument.setSalePrice(sku.getSalePrice());
		productDocument.setStartDate(sku.getStartDate());
		if(null != sku.getTemplate()) {
			productDocument.setTemplate(sku.getTemplate().getPath());
		}
		if(null != sku.getUnitOfMeasure()) {
			productDocument.setUnitOfMeasure(sku.getUnitOfMeasure().toString());
		}
		
		if(CollectionUtils.isNotEmpty(sku.getSites())) {
			Set<String> sites = new TreeSet<String>();
			for(SiteConfiguration site : sku.getSites()) {
				sites.add(site.getId());
			}
			productDocument.setSites(sites);
		}
		
		if(MapUtils.isNotEmpty(sku.getAuxiliaryMedia())) {
			Map<String, String> auxiliaryMedia = new TreeMap<String, String>();
			for(Entry<String, Media> auxMediaEntry : sku.getAuxiliaryMedia().entrySet()) {
				if(null != auxMediaEntry.getValue()) {
					auxiliaryMedia.put(auxMediaEntry.getKey(), auxMediaEntry.getValue().getPath());
				}
			}
		}
		
		productDocument.setDynamicAttributes(sku.getDynamicAttributes());
		
	}
	
	private void copyProductProperties(Product product,
			ProductDocument productDocument) {
		productDocument.setProductBrand(product.getBrand());
		if(null != product.getDefaultParentCategory()) {
			productDocument.setProductDefaultParentCategory(product.getDefaultParentCategory().getId());
			productDocument.setProductDefaultParentCategoryName(product.getDefaultParentCategory().getDisplayName());
		}
		productDocument.setProductDescription(product.getDescription());
		productDocument.setProductDisallowAsRecommendation(product.getDisallowAsRecommendation());
		productDocument.setProductDiscountable(product.getDiscountable());
		productDocument.setProductDisplayName(product.getDisplayName());
		productDocument.setProductEndDate(product.getEndDate());
		productDocument.setProductId(product.getId());
		if(null != product.getImages()) {
			List<String> productImages = new ArrayList<String>();
			for(Image image : product.getImages()) {
				String imageValue = "thumbnail=" + image.getThumbnail() + ", small=" + image.getSmall() + ", large=" + image.getLarge();
				productImages.add(imageValue);
			}
			productDocument.setProductImages(productImages);
		}
		productDocument.setProductLongDescription(product.getLongDescription());
		if(null != product.getManufacturer()) {
			productDocument.setProductManufacturer(product.getManufacturer().getId());
			productDocument.setProductManufacturerName(product.getManufacturer().getDisplayName());
		}
		productDocument.setProductNonreturnable(product.getNonreturnable());
		productDocument.setProductOnlineOnly(product.getOnlineOnly());
		productDocument.setProductStartDate(product.getStartDate());
		
		if(CollectionUtils.isNotEmpty(product.getFixedParentCategories())) {
			Set<String> fixedParentCategories = new TreeSet<String>();
			Set<String> fixedParentCategoriesNames = new TreeSet<String>();
			Set<String> ancestorCategories = new TreeSet<String>();
			Set<String> ancestorCategoriesNames = new TreeSet<String>();
			for(Category category : product.getFixedParentCategories()) {
				if(null != category) {
					fixedParentCategories.add(category.getId());
					fixedParentCategoriesNames.add(category.getDisplayName());
					computeAncestorCategories(category, ancestorCategories, ancestorCategoriesNames);
				}
			}
			productDocument.setProductFixedParentCategories(fixedParentCategories);
			productDocument.setProductFixedParentCategoriesNames(fixedParentCategoriesNames);
			productDocument.setAncestorCategories(ancestorCategories);
			productDocument.setAncestorCategoriesNames(ancestorCategoriesNames);
		}
		
		if(CollectionUtils.isNotEmpty(product.getKeywords())) {
			Set<String> keywords = new TreeSet<String>(product.getKeywords());
			productDocument.setProductKeywords(keywords);
		}
		
	}
	
	
	private void computeAncestorCategories(Category category, Set<String> ancestorCategories, Set<String> ancestorCategoriesNames) {
		if(ancestorCategories.contains(category.getId())) {
			return;
		}
		ancestorCategories.add(category.getId());
		ancestorCategoriesNames.add(category.getDisplayName());
		if(null != category.getFixedParentCategories()) {
			for(Category parent : category.getFixedParentCategories()) {
				computeAncestorCategories(parent, ancestorCategories, ancestorCategoriesNames);
			}
		}
	}
	
}
