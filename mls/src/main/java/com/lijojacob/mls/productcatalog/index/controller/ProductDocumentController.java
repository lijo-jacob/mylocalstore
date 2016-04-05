/*
 * Copyright 2012 - 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lijojacob.mls.productcatalog.index.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lijojacob.mls.productcatalog.index.ProductDocument;
import com.lijojacob.mls.productcatalog.index.ProductDocumentService;

@RestController
public class ProductDocumentController {

	private ProductDocumentService productDocumentService;

	@RequestMapping("/product-document/{id}")
	public String search(Model model, @PathVariable("id") String id, HttpServletRequest request) {
		model.addAttribute("product", productDocumentService.findById(id));
		return "product";
	}

	@Autowired
	public void setProductDocumentService(ProductDocumentService productDocumentService) {
		this.productDocumentService = productDocumentService;
	}
	
	@RequestMapping(value = "/product-document", method = RequestMethod.POST)
	public ProductDocument create(@RequestBody ProductDocument productDocument) {
		return productDocumentService.save(productDocument);
	}

}
