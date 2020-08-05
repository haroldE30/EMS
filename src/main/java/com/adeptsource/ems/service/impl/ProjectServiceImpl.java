package com.adeptsource.ems.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adeptsource.ems.common.util.CopyUtil;
import com.adeptsource.ems.entity.Project;
import com.adeptsource.ems.exception.ResourceNotFoundException;
import com.adeptsource.ems.exception.TransactionProcessException;
import com.adeptsource.ems.repository.ProjectRepository;
import com.adeptsource.ems.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	private ProjectRepository projectRepository;
	
	@Autowired
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	@Override
	public List<Project> getAll() throws ResourceNotFoundException{
		try {
			return projectRepository.findAll();
		} catch(Exception e) {
			throw new ResourceNotFoundException("Failed to load projects.", e);
		}
	}

	@Override
	public Project getById(Long id) throws ResourceNotFoundException{
		return projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found for id: " + id));
	}

	@Override
	public Project create(Project project) throws TransactionProcessException{
		try {
		return projectRepository.save(project);
		} catch(Exception e) {
			throw new TransactionProcessException("Failed to save project. ", e);
		}
	}

	@Override
	public Project update(Long id, Project params) throws TransactionProcessException{
		try {
			Project project = getById(id);
			CopyUtil.copyNonNullProperties(params, project);
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new TransactionProcessException("Failed to update project with id: " + id, e);
		}
	}

	@Override
	public void delete(Long id) throws TransactionProcessException{
		try {
			Project project = getById(id);
			projectRepository.delete(project);
		} catch(Exception e) {
			throw new TransactionProcessException("Failed to delete project with id: " + id, e);
		}
	}

}
