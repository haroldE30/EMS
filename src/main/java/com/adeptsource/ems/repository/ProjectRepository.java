package com.adeptsource.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adeptsource.ems.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
