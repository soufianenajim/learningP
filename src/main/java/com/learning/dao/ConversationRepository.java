package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learning.model.Conversation;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

	@Query("Select c from Conversation c where (c.user1.id=?1 and c.user2.id=?2) or (c.user1.id=?2 and c.user2.id=?1) ")
	Conversation 	findByUser1AndUser2(Long idUser1,Long idUser2);

	
	
}
