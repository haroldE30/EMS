package com.adeptsource.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adeptsource.hrms.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
