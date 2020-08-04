package com.adeptsource.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public List<Employee> getAllEmployees() throws ResourceNotFoundException{
		return employeeService.getAll();
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Employee employee = employeeService.getById(id);
		return ResponseEntity.ok(employee);
	}
	
	@PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@Validated @RequestBody EmployeeDTO params) throws Exception {
		Employee employee = employeeService.create(params);
		return ResponseEntity.ok(employee);
    }
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id,
			@Validated @RequestBody EmployeeDTO params) throws TransactionProcessException {

		Employee employee = employeeService.update(id, params);
		return ResponseEntity.ok(employee);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Long id) throws TransactionProcessException {
		employeeService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
