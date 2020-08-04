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
	public List<Position> getAll() throws ResourceNotFoundException{
		return positionService.getAll();
	}
	
	@GetMapping("/positions/{id}")
	public ResponseDTO<PositionDTO> getPositionById(@PathVariable Long id) throws ResourceNotFoundException {
		Position position = positionService.getById(id);
		return ResponseDTO.ok().convertTo(position, PositionDTO.class);
	}
	
	@PostMapping("/positions")
	public ResponseDTO<PositionDTO> create(@RequestDTO(PositionDTO.class) @Validated Position params) throws TransactionProcessException{
		Position position = positionService.create(params);
		return ResponseDTO.accepted().convertTo(position, PositionDTO.class);
	}
	
	@PutMapping("/positions/{id}")
	public ResponseDTO<PositionDTO> update(@PathVariable Long id,
			@RequestBody @Validated PositionDTO params) throws TransactionProcessException {
		Position position = positionService.update(id, params);
		return ResponseDTO.ok().convertTo(position, PositionDTO.class);
	}
	
	@DeleteMapping("/positions/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws TransactionProcessException {
		positionService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
