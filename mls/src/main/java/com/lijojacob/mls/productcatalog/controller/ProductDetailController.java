package com.lijojacob.mls.productcatalog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lijojacob.mls.productcatalog.dto.ProductDetailDTO;
import com.lijojacob.mls.productcatalog.index.ProductDocument;
import com.lijojacob.mls.productcatalog.index.ProductDocumentRepository;
import com.lijojacob.mls.userprofile.entity.UserSessionInterface;

@RestController
public class ProductDetailController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailController.class);
	
	@Autowired
	private ProductDocumentRepository productDocumentRepository;
	
	@Autowired
	private UserSessionInterface userSessionDTO;
	
	@RequestMapping(value = "/product/{productDocumentId}")
	public ProductDetailDTO getProductDetail(@PathVariable("productDocumentId") String productDocumentId) {
		System.err.println("Inside ProductDetailController.getProductDetail(), productDocumentId = " + productDocumentId);
		ProductDetailDTO productDetailDTO = new ProductDetailDTO();
		ProductDocument productDocument = productDocumentRepository.findOne(productDocumentId);
		BeanUtils.copyProperties(productDocument, productDetailDTO);
		System.err.println("productDetailDTO = " + productDetailDTO);
		return productDetailDTO;
	}

}
