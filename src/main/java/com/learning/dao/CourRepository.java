package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.model.Cour;

public interface CourRepository extends JpaRepository<Cour,Long > {
}
