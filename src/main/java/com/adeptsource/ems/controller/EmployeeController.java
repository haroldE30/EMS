package com.adeptsource.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adeptsource.ems.common.util.CopyUtil;
import com.adeptsource.ems.dto.EmployeeDTO;
import com.adeptsource.ems.entity.Employee;
import com.adeptsource.ems.exception.ResourceNotFoundException;
import com.adeptsource.ems.exception.TransactionProcessException;
import com.adeptsource.ems.service.EmployeeService;

@CrossOrigin(origins = EmployeeController.CROSS_ORIGIN)
@RestController
public class EmployeeController {
	
	static final String CROSS_ORIGIN = "http://localhost:4200";

	@Autowired
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/employees")
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getAllEmployees() throws ResourceNotFoundException{
		return employeeService.getAll();
	}
	
	@GetMapping("/employees/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) throws ResourceNotFoundException {
		Employee employee = employeeService.getById(id);
		
		EmployeeDTO dto = new EmployeeDTO();
		CopyUtil.copyNonNullProperties(employee, dto);
		dto.setPositionId(employee.getPosition().getId());
		dto.setPositionTitle(employee.getPosition().getTitle());
		dto.setDepartmentId(employee.getDepartment().getId());
		dto.setDepartmentName(employee.getDepartment().getName());
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PostMapping("/employees")
	@ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(EmployeeDTO params) throws TransactionProcessException {
		employeeService.create(params);
    }
	
	@PutMapping("/employees/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateEmployee(@PathVariable Long id, EmployeeDTO params) throws TransactionProcessException {
		employeeService.update(id, params);
	}
	
	@DeleteMapping("/employees/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteEmployee(@PathVariable Long id) throws TransactionProcessException {
		employeeService.delete(id);
	}
}
