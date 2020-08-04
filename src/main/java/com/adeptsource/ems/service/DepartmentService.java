package com.adeptsource.ems.service;

import java.util.List;

import com.adeptsource.ems.dto.DepartmentDTO;
import com.adeptsource.ems.entity.Department;
import com.adeptsource.ems.exception.ResourceNotFoundException;
import com.adeptsource.ems.exception.TransactionProcessException;

public interface DepartmentService {
	
	List<Department> getAll() throws ResourceNotFoundException;
	
	Department getById(Long id) throws ResourceNotFoundException;
	
	Department create(Department department) throws TransactionProcessException;
	
	Department update(Long id, DepartmentDTO department) throws TransactionProcessException;
	
	void delete(Long id) throws TransactionProcessException;
}
