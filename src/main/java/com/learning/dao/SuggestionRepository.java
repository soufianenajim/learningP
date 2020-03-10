package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.model.Suggestion;

public interface SuggestionRepository extends JpaRepository<Suggestion,Long > {

}
