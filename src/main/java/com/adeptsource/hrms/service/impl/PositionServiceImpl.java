package com.adeptsource.hrms.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adeptsource.hrms.common.util.CopyUtil;
import com.adeptsource.hrms.dto.PositionDTO;
import com.adeptsource.hrms.entity.Position;
import com.adeptsource.hrms.exception.ResourceNotFoundException;
import com.adeptsource.hrms.exception.TransactionProcessException;
import com.adeptsource.hrms.repository.PositionRepository;
import com.adeptsource.hrms.service.PositionService;

@Service
@Transactional
public class PositionServiceImpl implements PositionService {
	
	private static final Logger log = LoggerFactory.getLogger(PositionServiceImpl.class);
	
	private PositionRepository positionRepository;
	
	@Autowired
	public PositionServiceImpl(PositionRepository positionRepository) {
		this.positionRepository = positionRepository;
	}

	@Override
	public List<Position> getAll() throws ResourceNotFoundException {
		try {
			return positionRepository.findAll();
		} catch (Exception e) {
			log.error("Failed to load list of positions. {}", e.getLocalizedMessage());
			throw new ResourceNotFoundException("Failed to load list of positions. ", e);
		}
	}
	
	@Override
	public Position getById(Long id) throws ResourceNotFoundException {
		try {
		return positionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Position not found for id: " + id));
		} catch(Exception e) {
			log.error("Position not found for id: {}. {}" + id, e.getLocalizedMessage());
			throw e;
		}
	}

	@Override
	public Position create(Position position) throws TransactionProcessException{
		try {
			return positionRepository.save(position);
		} catch (Exception e) {
			log.error("Failed to create position. {}", e.getLocalizedMessage());
			throw new TransactionProcessException("Failed to create position. ", e);
		}
	}

	@Override
	public Position update(Long id, PositionDTO params) throws TransactionProcessException {
		try {
			Position position = getById(id);
			CopyUtil.copyNonNullProperties(params, position);
			return positionRepository.save(position);
		} catch(Exception e) {
			log.error("Failed to update position. {}", e.getLocalizedMessage());
			throw new TransactionProcessException("Failed to update position. ", e);
		}
	}

	@Override
	public void delete(Long id) throws TransactionProcessException{
		try {
			Position position = getById(id);
			positionRepository.delete(position);
		} catch(Exception e) {
			log.error("Failed to delete position. {}", e.getLocalizedMessage());
			throw new TransactionProcessException("Failed to delete position. ", e);
		}
	}

}
