package com.lijojacob.mls.userprofile.entity;

public enum Gender {
	
	Male("M"), Female("F"), Other("O");
	
	private String code;
	
	public String getCode() {
		return code;
	}

	private Gender(String code) {
		this.code = code;
	}

}
