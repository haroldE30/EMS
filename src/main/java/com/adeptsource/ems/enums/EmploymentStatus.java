package com.adeptsource.ems.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EmploymentStatus {
	PROBITIONARY("probitionary"),
	CONTRACT("contract"),
	REGULAR("regular"),
	PROJECT("project");
	
	private String status;
	
	private EmploymentStatus(String value) {
		this.status = value;
	}
	
	@JsonValue
	public String getStatus() {
		return this.status;
	}
	
	public static EmploymentStatus find(String status) {
		return Arrays.stream(EmploymentStatus.values())
				.filter(e -> e.status.equals(status))
				.findFirst().orElse(PROBITIONARY);
	}
}
