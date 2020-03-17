package com.learning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.model.Module;
public interface ModuleRepository extends JpaRepository<Module,Long > {

	Page<Module> findByName(String name,Pageable pageable);
}
