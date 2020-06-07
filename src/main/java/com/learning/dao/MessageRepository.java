package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	

}
