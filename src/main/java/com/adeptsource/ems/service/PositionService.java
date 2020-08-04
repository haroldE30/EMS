package com.adeptsource.ems.service;

import java.util.List;

import com.adeptsource.ems.dto.PositionDTO;
import com.adeptsource.ems.entity.Position;
import com.adeptsource.ems.exception.ResourceNotFoundException;
import com.adeptsource.ems.exception.TransactionProcessException;

public interface PositionService {
	
	List<Position> getAll() throws ResourceNotFoundException;
	
	Position getById(Long id) throws ResourceNotFoundException;
	
	Position create(Position position) throws TransactionProcessException;
	
	Position update(Long id, PositionDTO position) throws TransactionProcessException;
	
	void delete(Long id) throws TransactionProcessException;
}
