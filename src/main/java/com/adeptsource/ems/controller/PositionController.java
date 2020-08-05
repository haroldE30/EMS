package com.adeptsource.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adeptsource.ems.common.bind.RequestDTO;
import com.adeptsource.ems.common.http.ResponseDTO;
import com.adeptsource.ems.dto.PositionDTO;
import com.adeptsource.ems.entity.Position;
import com.adeptsource.ems.exception.ResourceNotFoundException;
import com.adeptsource.ems.exception.TransactionProcessException;
import com.adeptsource.ems.service.PositionService;

@CrossOrigin(origins = PositionController.CROSS_ORIGIN)
@RestController
public class PositionController {
	
	static final String CROSS_ORIGIN = "http://localhost:4200";
	
	private PositionService positionService;
	
	@Autowired
	public PositionController(PositionService positionService) {
		this.positionService = positionService;
	}
	
	@GetMapping("/positions")
	@ResponseStatus(HttpStatus.OK)
	public List<Position> getAll() throws ResourceNotFoundException{
		return positionService.getAll();
	}
	
	@GetMapping("/positions/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseDTO<PositionDTO> getPositionById(@PathVariable Long id) throws ResourceNotFoundException {
		Position position = positionService.getById(id);
		return ResponseDTO.ok().convertTo(position, PositionDTO.class);
	}
	
	@PostMapping("/positions")
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestDTO(PositionDTO.class) @Validated Position params) throws TransactionProcessException{
		positionService.create(params);
	}
	
	@PutMapping("/positions/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable Long id,
			@RequestBody @Validated PositionDTO params) throws TransactionProcessException {
		positionService.update(id, params);
	}
	
	@DeleteMapping("/positions/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) throws TransactionProcessException {
		positionService.delete(id);
	}
}
