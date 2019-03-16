package com.nik.rest.app.constant;

import org.apache.commons.lang3.StringUtils;

public enum AddressType {
	PHYSICAL("P");
	
	private final String code;
	
	AddressType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public static AddressType fromCode(String code) {
		
		if (StringUtils.isBlank(code)) {
			return null;
		}
		
		for (AddressType type : values()) {
			if (type.getCode().equals(code)) {
				return type;
			}
		}
		
		return null;
	}
}
