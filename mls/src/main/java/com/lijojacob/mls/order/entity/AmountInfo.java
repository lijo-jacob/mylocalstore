package com.lijojacob.mls.order.entity;

import java.util.List;

import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.Description;

import com.lijojacob.mls.common.entity.BaseEntity;

@Document
public @Data class AmountInfo extends BaseEntity {
	
	@Description("Adjustments")
	private List<Adjustment> adjustments;
	
	@Description("Amount")
	private Double amount;
	
	@Description("Amount is final?")
	private boolean amountIsFinal;
	
	@Description("Currency")
	private String currency;
	
	@Description("Discounted?")
	private boolean discounted;
	
	@Description("Reason for final amount")
	private String finalReasonCode;
	
	@Description("version")
	private int version;

}
