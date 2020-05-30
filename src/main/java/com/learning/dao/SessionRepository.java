package com.learning.dao;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
	@Query("SELECT s FROM Session s WHERE (LOWER(s.name) LIKE CONCAT(lower(?1), '%')) ")
	Page<Session> findByName(String name, Pageable pageable);

	@Query("SELECT s FROM Session s WHERE (LOWER(s.name) LIKE CONCAT(lower(?1), '%')) and s.organization.id=?2 ")
	Page<Session> findByNameAndOrganization(String code, Long idOrganization, Pageable pageable);

	@Modifying
	@Transactional
	@Query("delete  FROM Session  WHERE  organization.id=?1 ")
	void deleteByOrganisation(Long id);

	@Query("SELECT s FROM Session s WHERE s.organization.id=?1 ")
	List<Session> findByOrganization(Long id);

	@Query("SELECT s FROM Session s WHERE LOWER(s.name) like lower(?1) and s.organization.id=?2 ")
	Session findByNameAndOrganization(String name, Long idOrganization);

	@Query("SELECT s FROM Session s WHERE  s.organization.id=?1 and s.startDate<=?2 and s.endDate>=2")
	Session findCurrentByOrganization(Long idOrganization, LocalDate localDate);

}
