package com.adeptsource.ems.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.adeptsource.ems.enums.EmploymentStatus;
import com.adeptsource.ems.enums.Gender;
import com.adeptsource.ems.enums.MaritalStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name", length = 100)
	private String firstName;
	
	@Column(name = "last_name", length = 100)
	private String lastName;
	
	@Column(name = "middle_name", length = 100)
	private String middleName;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	private Date birthDate;
	
	@Column(name = "phone", length = 14)
	private String phone;
	
	@Column(name = "mobile", length = 12)
	private String mobile;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gender", length = 6)
	private Gender gender;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "marital_status", length = 8)
	private MaritalStatus maritalStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "emp_status", length = 12)
	private EmploymentStatus employmentStatus;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "employed_date")
	private Date employedDate;
	
	private String street;
	
	@Column(length = 100)
	private String city;

	@Column(name = "zip_code")
	private int zipCode;
	
	private String country;

	@OneToOne
	@JoinColumn(name = "position_id")
	private Position position;

	@OneToOne
	@JoinColumn(name = "department_id")
	private Department department;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", updatable = false)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@PrePersist
    public void prePersist() {
        createdDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = new Date();
    }
	
}
