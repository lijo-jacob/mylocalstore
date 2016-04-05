package com.lijojacob.mls.common.entity;

import lombok.Data;

import org.springframework.data.rest.core.annotation.Description;

public @Data class Currency extends BaseEntity {

	@Description("Code")
	private String code;
	
}
