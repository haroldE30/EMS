package com.adeptsource.ems.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MaritalStatus {
	SINGLE("single"),
	MARRIED("married"),
	WIDOWED("widowed"),
	DIVORCED("divorced");
	
	private String status;
	
	private MaritalStatus(String status) {
		this.status = status;
	}
	
	@JsonValue
	public String getStatus() {
		return this.status;
	}
	
	public static MaritalStatus find(String status) {
		return Arrays.stream(MaritalStatus.values())
				.filter(m -> m.status.equals(status))
				.findFirst()
				.orElse(SINGLE);
	}
}
