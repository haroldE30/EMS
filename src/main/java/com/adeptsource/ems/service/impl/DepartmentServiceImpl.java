package com.adeptsource.ems.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adeptsource.ems.common.util.CopyUtil;
import com.adeptsource.ems.entity.Department;
import com.adeptsource.ems.exception.ResourceNotFoundException;
import com.adeptsource.ems.exception.TransactionProcessException;
import com.adeptsource.ems.repository.DepartmentRepository;
import com.adeptsource.ems.service.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	
	private DepartmentRepository departmentRepository;

	@Autowired
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public List<Department> getAll() throws ResourceNotFoundException{
		try {
			return departmentRepository.findAll();
		}catch(Exception e) {
			log.error("Failed to load list of departments. {}", e.getMessage());
			throw new ResourceNotFoundException("Failed to load departments. ", e);
		}
	}

	@Override
	public Department getById(Long id) throws ResourceNotFoundException {
		try {
			return departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found for id: " + id));
		} catch (Exception e) {
			log.error("Department not found for id: {}. {}", id, e.getMessage());
			throw e;
		}
	}

	@Override
	public Department create(Department department) throws TransactionProcessException{
		try {
			return departmentRepository.save(department);
		} catch (Exception e) {
			log.error("Failed to create department. {}", e.getMessage());
			throw new TransactionProcessException("Failed to create department. ", e);
		}
	}

	@Override
	public Department update(Long id, Department params) throws TransactionProcessException {
		try {
			Department department = getById(id);
			CopyUtil.copyNonNullProperties(params, department);
			return departmentRepository.save(department);
		} catch(Exception e) {
			log.error("Failed to update department. {}", e.getMessage());
			throw new TransactionProcessException("Failed to update department with id: " + id, e);
		}
	}

	@Override
	public void delete(Long id) throws TransactionProcessException{
		try {
			Department department = getById(id);
			departmentRepository.delete(department);
		} catch(Exception e) {
			log.error("Failed to delete department. {}", e.getMessage());
			throw new TransactionProcessException("Failed to delete department with id: " + id, e);
		}
	}

}
