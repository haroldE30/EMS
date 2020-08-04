package com.adeptsource.ems.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
	MALE("male"),
	FEMALE("female");
	
	private String value;
	
	private Gender(String value) {
		this.value = value;
	}
	
	@JsonValue
	public String getValue() {
		return this.value;
	}
	
	public static Gender find(String value) {
		return Arrays.stream(Gender.values())
				.filter(g -> g.value.equals(value))
				.findFirst()
				.orElse(MALE);
	}
}
