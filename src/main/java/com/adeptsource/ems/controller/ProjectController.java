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

import com.adeptsource.ems.entity.Project;
import com.adeptsource.ems.exception.ResourceNotFoundException;
import com.adeptsource.ems.exception.TransactionProcessException;
import com.adeptsource.ems.service.ProjectService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProjectController {
	
	private ProjectService projectService;
	
	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@GetMapping("/projects")
	@ResponseStatus(HttpStatus.OK)
	public List<Project> getAllProjects() throws ResourceNotFoundException{
		return projectService.getAll();
	}
	
	@GetMapping("/projects/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Project> getProjectBy(@PathVariable Long id) throws ResourceNotFoundException{
		Project project = projectService.getById(id);
		return new ResponseEntity<>(project, HttpStatus.OK);
	}
	
	@PostMapping("/projects")
	@ResponseStatus(HttpStatus.CREATED)
	public void createProject(Project params) throws TransactionProcessException {
		projectService.create(params);
	}
	
	@PutMapping("/projects/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateProject(@PathVariable Long id, Project params) throws TransactionProcessException {
		projectService.update(id, params);
	}
	
	@DeleteMapping("/projects/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteProject(@PathVariable Long id) throws TransactionProcessException {
		projectService.delete(id);
	}
}
