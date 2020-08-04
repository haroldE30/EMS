package com.adeptsource.ems.dto;

import javax.validation.constraints.NotNull;

import com.adeptsource.ems.common.bind.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DTO
public class DepartmentDTO {
	
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;
}
