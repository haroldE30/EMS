package com.adeptsource.ems.service;

import java.util.List;

import com.adeptsource.ems.entity.Project;
import com.adeptsource.ems.exception.ResourceNotFoundException;
import com.adeptsource.ems.exception.TransactionProcessException;

public interface ProjectService {
	
	List<Project>getAll() throws ResourceNotFoundException;
	
	Project getById(Long id) throws ResourceNotFoundException;
	
	Project create(Project project) throws TransactionProcessException;
	
	Project update(Long id, Project project) throws TransactionProcessException;
	
	void delete(Long id) throws TransactionProcessException;
}
