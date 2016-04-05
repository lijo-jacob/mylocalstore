package com.lijojacob.mls.productcatalog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lijojacob.mls.exception.InvalidRequestException;
import com.lijojacob.mls.productcatalog.dto.CatalogDTO;
import com.lijojacob.mls.productcatalog.dto.CategoryDTO;
import com.lijojacob.mls.productcatalog.dto.ProductDTO;
import com.lijojacob.mls.productcatalog.dto.SkuDTO;
import com.lijojacob.mls.productcatalog.entity.Catalog;
import com.lijojacob.mls.productcatalog.entity.Category;
import com.lijojacob.mls.productcatalog.entity.Product;
import com.lijojacob.mls.productcatalog.entity.Sku;
import com.lijojacob.mls.productcatalog.index.ProductDocumentRepository;
import com.lijojacob.mls.productcatalog.index.ProductDocumentService;
import com.lijojacob.mls.productcatalog.index.dto.SearchCriteria;
import com.lijojacob.mls.productcatalog.index.dto.SearchResultDTO;
import com.lijojacob.mls.productcatalog.repository.CatalogRepository;
import com.lijojacob.mls.productcatalog.repository.CategoryRepository;
import com.lijojacob.mls.productcatalog.repository.ProductRepository;
import com.lijojacob.mls.siteconfiguration.entity.SiteConfiguration;
import com.lijojacob.mls.siteconfiguration.repository.SiteConfigurationRepository;
import com.lijojacob.mls.userprofile.entity.UserSessionInterface;

@RestController
public class CatalogBrowseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogBrowseController.class);
	
	@Autowired
	private SiteConfigurationRepository siteRepository;
	
	@Autowired 
	private CatalogRepository catalogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductDocumentRepository productDocumentRepository;
	
	@Autowired
	private ProductDocumentService productDocumentService;
	
	@Autowired
	private UserSessionInterface userSessionDTO;
	
	@RequestMapping(value = "/defaultCatalog")
	public CatalogDTO getDefaultCatalog(HttpServletRequest request) {
		CatalogDTO catalogDTO = new CatalogDTO();
		
		SiteConfiguration site = userSessionDTO.getUserSession().getSite();
		
		if(null == site) {
			throw new InvalidRequestException("Current Site not found ");
		}
		
		Catalog catalog = site.getDefaultCatalog();
		
		if(null == catalog) {
			throw new InvalidRequestException("Site: " + site.getId() + " has no default catalog");
		}
		
		BeanUtils.copyProperties(catalog, catalogDTO);
		
		if(null == catalog.getRootCategories()) {
			return catalogDTO;
		}
		List<Category> rootCategories = catalog.getRootCategories();
		List<CategoryDTO> rootCategoryDTOs = transformCategories(rootCategories);
		catalogDTO.setRootCategories(rootCategoryDTOs);
		return catalogDTO;
	}
	
	@RequestMapping(value = "/category/browse", method = RequestMethod.POST)
	public SearchResultDTO getChildren(@RequestBody SearchCriteria searchCriteria) {
		Sort sort = new Sort("productDisplayName");
		PageRequest pageable = new PageRequest(searchCriteria.getPage(), searchCriteria.getSize(), sort);
		SearchResultDTO result = productDocumentService.findByAncestorCategories(searchCriteria.getCategoryId(), searchCriteria.getFilterCriteria(), pageable);
		return result;
	}

	private List<CategoryDTO> transformCategories(List<Category> categories) {
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		for(Category category : categories) {
			CategoryDTO categoryDTO = new CategoryDTO();
			BeanUtils.copyProperties(category, categoryDTO);
			
//			copyProducts(category, categoryDTO);
			
			List<Category> subCategories = categoryRepository.findByFixedParentCategoriesContaining(category);
			if(CollectionUtils.isNotEmpty(subCategories)) {
				categoryDTO.setSubCategories(transformCategories(subCategories));
			}
			
			categoryDTOs.add(categoryDTO);
		}
		return categoryDTOs;
	}

	private void copyProducts(Category category, CategoryDTO categoryDTO) {
		List<Product> products = productRepository.findByFixedParentCategoriesContaining(category);
		
		if(CollectionUtils.isNotEmpty(products)) {
			List<ProductDTO> productDTOs = transformProducts(products);
			categoryDTO.setProducts(productDTOs);
		}
	}

	private List<ProductDTO> transformProducts(List<Product> products) {
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for(Product product : products) {
			ProductDTO productDTO = new ProductDTO();
			BeanUtils.copyProperties(product, productDTO);
			List<Sku> childSkus = product.getChildSkus();
			if(CollectionUtils.isNotEmpty(childSkus)) {
				List<SkuDTO> skuDTOs = new ArrayList<SkuDTO>();
				for(Sku sku : childSkus) {
					SkuDTO skuDTO = new SkuDTO();
					BeanUtils.copyProperties(sku, skuDTO);
					skuDTOs.add(skuDTO);
				}
				productDTO.setChildSkus(skuDTOs);
			}
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}
	
}
