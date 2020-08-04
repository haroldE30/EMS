package com.adeptsource.hrms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adeptsource.hrms.common.util.CopyUtil;
import com.adeptsource.hrms.dto.EmployeeDTO;
import com.adeptsource.hrms.entity.Department;
import com.adeptsource.hrms.entity.Employee;
import com.adeptsource.hrms.entity.Position;
import com.adeptsource.hrms.enums.EmploymentStatus;
import com.adeptsource.hrms.enums.Gender;
import com.adeptsource.hrms.enums.MaritalStatus;
import com.adeptsource.hrms.exception.ResourceNotFoundException;
import com.adeptsource.hrms.exception.TransactionProcessException;
import com.adeptsource.hrms.repository.EmployeeRepository;
import com.adeptsource.hrms.service.DepartmentService;
import com.adeptsource.hrms.service.EmployeeService;
import com.adeptsource.hrms.service.PositionService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	private EmployeeRepository employeeRepository;
	private PositionService positionService;
	private DepartmentService departmentService;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionService positionService,
			DepartmentService departmentService) {
		this.employeeRepository = employeeRepository;
		this.positionService = positionService;
		this.departmentService = departmentService;
	}

	@Override
	public List<Employee> getAll() throws ResourceNotFoundException {
		try {
			return employeeRepository.findAll();
		} catch (Exception e) {
			log.error("Failed to load list of employees. {}",  e);
			throw new ResourceNotFoundException("Failed to load list of employees." + e);
		}
	}

	@Override
	public Employee getById(Long id) throws ResourceNotFoundException {
		try {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: " + id));
		} catch (Exception e) {
			log.error("Employee not found for id: {}. {}", id, e.getLocalizedMessage());
			throw e;
		}
	}

	@Override
	public Employee create(EmployeeDTO params) throws TransactionProcessException {
		Employee employee = new Employee();

		Gender gender = Gender.find(params.getGender());
		employee.setGender(gender);

		MaritalStatus maritalStatus = MaritalStatus.find(params.getMaritalStatus());
		employee.setMaritalStatus(maritalStatus);

		EmploymentStatus employmentStatus = EmploymentStatus.find(params.getEmploymentStatus());
		employee.setEmploymentStatus(employmentStatus);

		try {
			CopyUtil.copyNonNullProperties(params, employee);

			if (params.getPositionId() != null) {
				Position position = positionService.getById(params.getPositionId());
				employee.setPosition(position);
			}
			if (params.getDepartmentId() != null) {
				Department department = departmentService.getById(params.getDepartmentId());
				employee.setDepartment(department);
			}
			return employeeRepository.save(employee);
		} catch (Exception e) {
			log.error("Failed to create employee. {}", e.getLocalizedMessage());
			throw new TransactionProcessException("Failed to create employee. ", e);
		}

	}

	@Override
	public Employee update(Long id, EmployeeDTO params) throws TransactionProcessException {
		try {
			Employee employee = getById(id);
			CopyUtil.copyNonNullProperties(params, employee);

			if (params.getPositionId() != null
					&& !params.getPositionId().equals(employee.getPosition().getId())) {
				Position position = positionService.getById(params.getPositionId());
				employee.setPosition(position);
			}
			if (params.getDepartmentId() != null
					&& !params.getDepartmentId().equals(employee.getDepartment().getId())) {
				Department department = departmentService.getById(params.getDepartmentId());
				employee.setDepartment(department);
			}

			Gender gender = Gender.find(params.getGender());
			if (gender != employee.getGender()) {
				employee.setGender(gender);
			}

			MaritalStatus maritalStatus = MaritalStatus.find(params.getMaritalStatus());
			if (maritalStatus != employee.getMaritalStatus()) {
				employee.setMaritalStatus(maritalStatus);
			}

			EmploymentStatus employmentStatus = EmploymentStatus.find(params.getEmploymentStatus());
			if (employmentStatus != employee.getEmploymentStatus()) {
				employee.setEmploymentStatus(employmentStatus);
			}

			return employeeRepository.save(employee);
		}catch (Exception e) {
			log.error("Failed to update employee. {}", e.getLocalizedMessage());
			throw new TransactionProcessException("Failed to update employee. ", e);
		}
	}

	@Override
	public void delete(Long id) throws TransactionProcessException {
		try {
			Employee employee = getById(id);
			employeeRepository.delete(employee);
		}catch (Exception e) {
			log.error("Failed to delete employee. {}", e.getMessage());
			throw new TransactionProcessException("Failed to delete employee. ", e);
		}
	}

}
