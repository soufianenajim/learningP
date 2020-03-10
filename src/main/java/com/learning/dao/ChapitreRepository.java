package com.learning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.model.Chapitre;

public interface ChapitreRepository extends JpaRepository<Chapitre,Long > {
	Page<Chapitre> findByName(String name,Pageable pageable);
	
}
