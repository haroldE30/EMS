package com.adeptsource.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adeptsource.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
