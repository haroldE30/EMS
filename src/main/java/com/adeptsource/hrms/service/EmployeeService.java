package com.adeptsource.hrms.service;

import java.util.List;

import com.adeptsource.hrms.dto.EmployeeDTO;
import com.adeptsource.hrms.entity.Employee;
import com.adeptsource.hrms.exception.ResourceNotFoundException;
import com.adeptsource.hrms.exception.TransactionProcessException;

public interface EmployeeService {
	
	List<Employee> getAll() throws ResourceNotFoundException;
	
	Employee getById(Long id) throws ResourceNotFoundException;
	
	Employee create(EmployeeDTO employee) throws TransactionProcessException;
	
	Employee update(Long id, EmployeeDTO params) throws TransactionProcessException;
	
	void delete(Long id) throws TransactionProcessException;
}
