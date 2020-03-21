package com.learning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Suggestion;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
	@Query("SELECT s FROM Suggestion s WHERE (LOWER(s.name) LIKE CONCAT(?1, '%')) and s.question.id = ?2")
	Page<Suggestion> findByNameAndQuestion(String code, Long idQuestion, Pageable pageable);
}
