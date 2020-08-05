package com.adeptsource.ems.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProjectStatus {
	ACTIVE("active"),
	CANCELLED("cancelled"),
	ONHOLD("onhold"),
	COMPLETED("completed");
	
	private String status;
	
	private ProjectStatus(String value) {
		this.status = value;
	}
	
	@JsonValue
	public String getStatus() {
		return this.status;
	}
}
