package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.model.Td;

public interface TdRepository extends JpaRepository<Td,Long > {

}
