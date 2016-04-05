package com.lijojacob.mls.order.dto;

import java.util.List;

import lombok.Data;

public @Data class AmountInfoDTO {

	private List<AdjustmentDTO> adjustments;
	
	private Double amount;
	
	private boolean amountIsFinal;
	
	private String currency;
	
	private boolean discounted;
	
	private String finalReasonCode;
	
	private int version;
}
