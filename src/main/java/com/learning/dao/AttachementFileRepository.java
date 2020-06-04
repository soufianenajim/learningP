package com.learning.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.AttachmentFile;

public interface AttachementFileRepository extends JpaRepository<AttachmentFile,Long > {
	@Query("Select a from AttachmentFile a where a.cour.id=?1 and a.fileName=?2")
	AttachmentFile findByCourAndName(Long idCour,String name);
	
	@Modifying
	@Transactional
	@Query("delete  FROM AttachmentFile  WHERE  cour.id=?1 and  fileName=?2")
	void deleteAttachment(Long idCour,String name);
	
	@Query("Select count(a.id) from AttachmentFile a where a.cour.id=?1 and a.fileName=?2")
	Long countByFileNameAndCour(Long idCour,String name);
}

