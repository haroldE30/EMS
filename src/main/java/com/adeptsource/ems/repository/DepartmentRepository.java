package com.adeptsource.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adeptsource.ems.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
