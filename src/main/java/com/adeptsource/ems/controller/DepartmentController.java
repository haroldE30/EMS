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
	public List<Department> getAllDepartments() throws ResourceNotFoundException{
		return departmentService.getAll();
	}
	
	@GetMapping("/departments/{id}")
	public ResponseDTO<DepartmentDTO> getDepartmentById(@PathVariable Long id) throws ResourceNotFoundException {
		Department department = departmentService.getById(id);
		return ResponseDTO.ok().convertTo(department, DepartmentDTO.class);
	}
	
	@PostMapping("/departments")
	public ResponseDTO<DepartmentDTO> createDepartment(@RequestDTO(DepartmentDTO.class) @Validated Department params) throws TransactionProcessException{
		Department department = departmentService.create(params);
		return ResponseDTO.ok().convertTo(department, DepartmentDTO.class);
	}
	
	@PutMapping("/departments/{id}")
	public ResponseDTO<DepartmentDTO> updateDepartment(@PathVariable Long id,
			@Validated @RequestBody DepartmentDTO params) throws TransactionProcessException{
		Department department = departmentService.update(id, params);
		return ResponseDTO.ok().convertTo(department, DepartmentDTO.class);
	}
	
	@DeleteMapping("/departments/{id}")
	public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable Long id) throws TransactionProcessException{
		departmentService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
