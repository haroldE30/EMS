package com.adeptsource.ems.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee_project")
public class EmployeeProject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	
	@OneToOne
	private Employee member;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "emp_join_date")
	private Date employeeJoinDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "emp_left_date")
	private Date employeeLeftDate;
	
	private boolean active;
	
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
