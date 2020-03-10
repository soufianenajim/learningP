package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.model.Paragraphe;

public interface ParagrapheRepository extends JpaRepository<Paragraphe,Long > {

}
