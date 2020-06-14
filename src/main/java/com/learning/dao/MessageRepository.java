package com.learning.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learning.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	@Query("Select m from Message m where (m.userFrom.id=?1 and m.userTo.id=?2) or (m.userFrom.id=?2 and m.userTo.id=?1) order by m.updatedAt desc")
	List<Message> findByUser1AndUser2(Long idUser1, Long idUser2, Pageable pageable);

}
