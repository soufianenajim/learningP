package com.learning.dao;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learning.model.UserConnexion;

@Repository
public interface UserConnexionRepository extends JpaRepository<UserConnexion, Long> {

	@Query("SELECT uc FROM UserConnexion uc WHERE uc.user.id=?1 and uc.dateConnexion=?2  ")
	UserConnexion findByUserAndDate(Long idUser, LocalDate localDate);

	@Query("SELECT u.organization.id,u.organization.name,sum(uc.numberConnexion) from UserConnexion uc,User u where u.id=uc.user.id and uc.dateConnexion>=1 and u.organization.deletable is true group by u.organization.id  order by sum(uc.numberConnexion)")
	List<Object> getConnexionByOrganizationAfterDate(LocalDate startDate,Pageable pageable);
	
	@Query("SELECT uc.user,sum(uc.numberConnexion) from UserConnexion uc where uc.dateConnexion>=?1 and uc.user.organization.id=?2 and uc.user.organization.deletable is true group by uc.user.id  order by sum(uc.numberConnexion) desc")
	List<Object> getConnexionByUserAfterDate(LocalDate startDate,Long idOrg);
	
	
}
