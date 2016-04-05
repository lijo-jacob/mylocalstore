package com.lijojacob.mls.productcatalog.index;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lijojacob.mls.productcatalog.entity.Product;
import com.lijojacob.mls.productcatalog.repository.ProductRepository;

@Service
public class BaselineIndexingService {
	
	@Autowired
	private ProductDocumentService productDocumentService;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductDocumentFactory productDocumentFactory;
	
	public void doBaselineIndex() {
		List<Product> products = productRepository.findAll();
		List<ProductDocument> productDocuments = new ArrayList<ProductDocument>();
		for(Product product : products) {
			productDocuments.addAll(productDocumentFactory.createProductDocuments(product));
		}
		productDocumentService.save(productDocuments);
	}
	
}
