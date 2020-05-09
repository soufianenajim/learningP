package com.learning.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Exercices;
import com.learning.model.TypeEnum;

public interface ExercicesRepository extends JpaRepository<Exercices, Long> {
	
	@Query("SELECT t FROM Exercices t WHERE (LOWER(t.name) LIKE CONCAT(lower(?1), '%')) ")
	Page<Exercices> findByName(String name,  Pageable pageable);
	
	@Query("SELECT t FROM Exercices t WHERE (LOWER(t.name) LIKE CONCAT(lower(?1), '%')) and t.type=?2")
	Page<Exercices> findByNameAndType(String name,TypeEnum type,  Pageable pageable);
	@Query("SELECT t FROM  Exercices t WHERE (LOWER(t.name) LIKE CONCAT(lower(?1), '%')) and t.cour.id=?2 ")
	Page<Exercices> findByNameAndCour(String code, Long idCour, Pageable pageable);
	
	@Query("SELECT e FROM Exercices e WHERE e.cour.id=?1 and e.type=?2 ")
	Exercices findByCourAndType(Long courId,TypeEnum type);
	
	@Query("SELECT e FROM Exercices e JOIN e.questions eq WHERE eq.id=?1  ")
	List<Exercices> findByQuestion(Long questionId);
	
	@Query("SELECT e FROM Exercices e WHERE e.cour.module.id=?1 and e.type=?2")
	List<Exercices> findByModuleAndType(Long idModule,TypeEnum type);
	
	
}
