package com.adeptsource.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adeptsource.ems.common.bind.RequestDTO;
import com.adeptsource.ems.common.http.ResponseDTO;
import com.adeptsource.ems.dto.DepartmentDTO;
import com.adeptsource.ems.entity.Department;
import com.adeptsource.ems.exception.ResourceNotFoundException;
import com.adeptsource.ems.exception.TransactionProcessException;
import com.adeptsource.ems.service.DepartmentService;

@CrossOrigin(origins = DepartmentController.CROSS_ORIGIN)
@RestController
public class DepartmentController {

	static final String CROSS_ORIGIN = "http://localhost:4200";
	
	private DepartmentService departmentService;

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@GetMapping("/departments")
	@ResponseStatus(HttpStatus.OK)
	public List<Department> getAllDepartments() throws ResourceNotFoundException{
		return departmentService.getAll();
	}
	
	@GetMapping("/departments/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseDTO<DepartmentDTO> getDepartmentById(@PathVariable Long id) throws ResourceNotFoundException {
		Department department = departmentService.getById(id);
		return ResponseDTO.ok().convertTo(department, DepartmentDTO.class);
	}
	
	@PostMapping("/departments")
	@ResponseStatus(HttpStatus.CREATED)
	public void createDepartment(@RequestDTO(DepartmentDTO.class) Department params) throws TransactionProcessException {
		departmentService.create(params);
	}
	
	@PutMapping("/departments/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateDepartment(@PathVariable Long id, @RequestDTO(DepartmentDTO.class) Department params) 
			throws TransactionProcessException {
		departmentService.update(id, params);
	}
	
	@DeleteMapping("/departments/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteDepartment(@PathVariable Long id) throws TransactionProcessException {
		departmentService.delete(id);
	}
}
