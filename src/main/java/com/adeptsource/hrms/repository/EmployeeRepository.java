package com.adeptsource.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adeptsource.hrms.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
