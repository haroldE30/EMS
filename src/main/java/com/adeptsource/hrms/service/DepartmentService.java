package com.adeptsource.hrms.service;

import java.util.List;

import com.adeptsource.hrms.dto.DepartmentDTO;
import com.adeptsource.hrms.entity.Department;
import com.adeptsource.hrms.exception.ResourceNotFoundException;
import com.adeptsource.hrms.exception.TransactionProcessException;

public interface DepartmentService {
	
	List<Department> getAll() throws ResourceNotFoundException;
	
	Department getById(Long id) throws ResourceNotFoundException;
	
	Department create(Department department) throws TransactionProcessException;
	
	Department update(Long id, DepartmentDTO department) throws TransactionProcessException;
	
	void delete(Long id) throws TransactionProcessException;
}
