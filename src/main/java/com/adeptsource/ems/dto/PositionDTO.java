package com.adeptsource.ems.dto;

import javax.validation.constraints.NotNull;

import com.adeptsource.ems.common.bind.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DTO
public class PositionDTO {
	
	private Long id;
	
	@NotNull
	private String title;
	
	@NotNull
	private String description;
}
