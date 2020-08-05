package com.adeptsource.ems.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.adeptsource.ems.common.bind.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@DTO
public class EmployeeDTO {

	private Long id;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String middleName;
	
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Manila")
	private Date birthDate;
	
	@NotNull
	private String phone;
	
	@NotNull
	private String mobile;
	
	@NotNull
	private String email;
	
	@NotNull
	private String gender;
	
	@NotNull
	private String maritalStatus;
	
	@NotNull
	private String employmentStatus;
	
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Manila")
	private Date employedDate;
	
	@NotNull
	private String street;
	
	@NotNull
	private String city;

	@NotNull
	private int zipCode;
	
	@NotNull
	private String country;

	@NotNull
	private Long positionId;
	
	private String positionTitle;
	
	@NotNull
	private Long departmentId;
	
	private String departmentName;
}
