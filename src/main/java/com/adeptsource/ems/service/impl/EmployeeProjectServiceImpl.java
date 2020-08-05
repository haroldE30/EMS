package com.adeptsource.ems.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.adeptsource.ems.common.util.CopyUtil;
import com.adeptsource.ems.entity.EmployeeProject;
import com.adeptsource.ems.exception.ResourceNotFoundException;
import com.adeptsource.ems.exception.TransactionProcessException;
import com.adeptsource.ems.repository.EmployeeProjectRepository;
import com.adeptsource.ems.service.EmployeeProjectService;

@Service
@Transactional
public class EmployeeProjectServiceImpl implements EmployeeProjectService {
	
	private EmployeeProjectRepository repository;

	@Override
	public List<EmployeeProject> getAll() throws ResourceNotFoundException {
		try {
			return repository.findAll();
		} catch (Exception e) {
			throw new ResourceNotFoundException("Failed to load employee projects.", e);
		}
	}

	@Override
	public EmployeeProject getById(Long id) throws ResourceNotFoundException {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee project not found for id: " + id));
	}

	@Override
	public EmployeeProject create(EmployeeProject employeeProject) throws TransactionProcessException {
		try {
			return repository.save(employeeProject);
		} catch(Exception e) {
			throw new TransactionProcessException("Failed to save employee project.", e);
		}
	}

	@Override
	public EmployeeProject update(Long id, EmployeeProject params) throws TransactionProcessException {
		try {
			EmployeeProject ep = getById(id);
			CopyUtil.copyNonNullProperties(params, ep);
			return repository.save(ep);
		} catch (Exception e) {
			throw new TransactionProcessException("Failed to update employee project with id: " + id, e);
		}
	}

	@Override
	public void delete(Long id) throws TransactionProcessException {
		try {
			EmployeeProject ep = getById(id);
			repository.delete(ep);
		} catch (Exception e) {
			throw new TransactionProcessException("Failed to delete employee project with id: " + id, e);
		}
		
	}
}
