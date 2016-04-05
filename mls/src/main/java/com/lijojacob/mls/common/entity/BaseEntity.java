package com.lijojacob.mls.common.entity;

import org.springframework.core.annotation.Order;
import org.springframework.data.annotation.Id;

import lombok.Data;

public @Data class BaseEntity {
	
	@Id
	@Order(1)
	private String id;

}

