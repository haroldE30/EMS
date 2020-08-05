package com.adeptsource.ems.dto;

import java.util.Date;

import com.adeptsource.ems.common.bind.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DTO
public class ProjectDTO {
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private Long managerId;
	
	private String managerName;
	
	private int size;
	
	private String status;
	
	private String remarks;
	
	@JsonFormat(timezone = "Asia/Manila")
	private Date startDate;
	
	@JsonFormat(timezone = "Asia/Manila")
	private Date endDate;
}
