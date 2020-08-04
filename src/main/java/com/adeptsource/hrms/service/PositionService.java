package com.adeptsource.hrms.service;

import java.util.List;

import com.adeptsource.hrms.dto.PositionDTO;
import com.adeptsource.hrms.entity.Position;
import com.adeptsource.hrms.exception.ResourceNotFoundException;
import com.adeptsource.hrms.exception.TransactionProcessException;

public interface PositionService {
	
	List<Position> getAll() throws ResourceNotFoundException;
	
	Position getById(Long id) throws ResourceNotFoundException;
	
	Position create(Position position) throws TransactionProcessException;
	
	Position update(Long id, PositionDTO position) throws TransactionProcessException;
	
	void delete(Long id) throws TransactionProcessException;
}
