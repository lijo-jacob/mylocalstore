package com.lijojacob.mls.common.dto;

import lombok.Data;

public @Data class BaseResponse {
	
	public BaseResponse(int statusCode, Object response) {
		this.statusCode = statusCode;
		this.response = response;
	}
	
	private int statusCode;
	
	private Object response;

}
