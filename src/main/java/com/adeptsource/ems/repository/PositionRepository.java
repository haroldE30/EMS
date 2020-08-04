package com.adeptsource.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adeptsource.ems.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

}
