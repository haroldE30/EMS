package com.adeptsource.ems.service;

import java.util.List;

import com.adeptsource.ems.entity.EmployeeProject;
import com.adeptsource.ems.exception.ResourceNotFoundException;
import com.adeptsource.ems.exception.TransactionProcessException;

public interface EmployeeProjectService {

	List<EmployeeProject> getAll() throws ResourceNotFoundException;
	
	EmployeeProject getById(Long id) throws ResourceNotFoundException;
	
	EmployeeProject create(EmployeeProject employeeProject) throws TransactionProcessException;
	
	EmployeeProject update(Long id, EmployeeProject employeeProject) throws TransactionProcessException;
	
	void delete(Long id) throws TransactionProcessException;
}
