package com.lijojacob.mls.home.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lijojacob.mls.productcatalog.dto.CategoryDTO;

@RestController
public class HomeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value="/category/featured")
	public CategoryDTO getFeaturedCategory() {
		LOGGER.debug("Inside getFeaturedCategory()");
		CategoryDTO category = new CategoryDTO();
		category.setDisplayName("Electronics");
		List<CategoryDTO> subcategories = new ArrayList<CategoryDTO>();
		
		CategoryDTO cameras = new CategoryDTO();
		cameras.setDisplayName("Cameras");
//		cameras.setImageUrl("images/category/electronics/camera/nikon-coolpix-l330-point-shoot-125x125-imadu6uc76huggmu.jpeg");
		subcategories.add(cameras);
		
		CategoryDTO laptops = new CategoryDTO();
		laptops.setDisplayName("Laptops");
//		laptops.setImageUrl("images/category/electronics/laptops/hp-notebook-15-s003tu-125x125-imaeyf6e5cgmhn9r.jpeg");
		subcategories.add(laptops);
		
		CategoryDTO mobiles = new CategoryDTO();
		mobiles.setDisplayName("Mobiles");
//		mobiles.setImageUrl("images/category/electronics/mobiles/karbonn-titanium-s1-plus-125x125-imadwhvpt359zgzh.jpeg");
		subcategories.add(mobiles);
		
		CategoryDTO oven = new CategoryDTO();
		oven.setDisplayName("Oven");
//		oven.setImageUrl("images/category/electronics/oven/lg-ms2021cw-200x200-imadwr9wbwngds9d.jpeg");
		subcategories.add(oven);
		
		CategoryDTO television = new CategoryDTO();
		television.setDisplayName("Television");
//		television.setImageUrl("images/category/electronics/television/samsung-32h6400-125x125-imady4763kaja2vj.jpeg");
		subcategories.add(television);
		
		CategoryDTO refrigerators = new CategoryDTO();
		refrigerators.setDisplayName("Refrigerators");
//		refrigerators.setImageUrl("images/category/electronics/refrigerator/lg-gl-b252vpgy-200x200-imadzb55ctxzfvme.jpeg");
		subcategories.add(refrigerators);
		
		CategoryDTO washingMachines = new CategoryDTO();
		washingMachines.setDisplayName("Washing Machines");
//		washingMachines.setImageUrl("images/category/electronics/washing_machine/bosch-wax-16161in-200x200-imaeygcqcgtax7j8.jpeg");
		subcategories.add(washingMachines);
		
		CategoryDTO mobileAccessories = new CategoryDTO();
		mobileAccessories.setDisplayName("Mobile Accessories");
//		mobileAccessories.setImageUrl("images/category/electronics/mobile_accessory/koloredge-ke-motoewh-125x125-imadwzyynak4gspr.jpeg");
		subcategories.add(mobileAccessories);
		
		CategoryDTO laptopAccessories = new CategoryDTO();
		laptopAccessories.setDisplayName("Laptop Accessories");
//		laptopAccessories.setImageUrl("images/category/electronics/laptop_accessory/logitech-b175-200x200-imadq48qhbv4wenh.jpeg");
		subcategories.add(laptopAccessories);
		
		category.setSubCategories(subcategories);
		return category;
	}
	
}