package com.lijojacob.mls.userprofile.entity;

import lombok.Data;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lijojacob.mls.common.entity.BaseEntity;
import com.lijojacob.mls.order.entity.Order;
import com.lijojacob.mls.siteconfiguration.entity.SiteConfiguration;

@Document
public @Data class UserSession extends BaseEntity { 
	
	@DBRef
	private UserProfile userProfile;
	
	@DBRef 
	private SiteConfiguration site;
	
	@DBRef
	private Order shoppingCart;
	
	
	
}
