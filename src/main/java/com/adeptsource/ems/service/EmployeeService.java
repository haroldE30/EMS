package com.adeptsource.ems.service;

import java.util.List;

import com.adeptsource.ems.dto.EmployeeDTO;
import com.adeptsource.ems.entity.Employee;
import com.adeptsource.ems.exception.ResourceNotFoundException;
import com.adeptsource.ems.exception.TransactionProcessException;

public interface EmployeeService {
	
	List<Employee> getAll() throws ResourceNotFoundException;
	
	Employee getById(Long id) throws ResourceNotFoundException;
	
	Employee create(EmployeeDTO employee) throws TransactionProcessException;
	
	Employee update(Long id, EmployeeDTO params) throws TransactionProcessException;
	
	void delete(Long id) throws TransactionProcessException;
}
