package com.learning.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.learning.dao.UserRepositorySearchCriteria;
import com.learning.dto.UserDTO;
import com.learning.model.User;
import com.learning.model.base.Demande;
import com.learning.model.base.SortOrder;

@Repository
public class UserRepositorySearchCriteriaImpl implements UserRepositorySearchCriteria {
	
	@Autowired
	private EntityManager em;
	
	private CriteriaBuilder cb = null;

	private Root<User> user = null;

	private List<Predicate> predicates = null;

	@Override
	public List<User> findByCriteres(Demande<UserDTO> demande) {
		cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);

		user = cq.from(User.class);
		predicates = getPredicate(demande);
		int page = demande.getPage();
		int size = demande.getSize();
		if (!StringUtils.isEmpty(demande.getSortField()) && !StringUtils.isEmpty(demande.getSortOrder())
				&& !demande.getSortOrder().equals(SortOrder.NONE)) {
			if (demande.getSortOrder().equals(SortOrder.ASCENDING)) {
				cq.orderBy(cb.asc(user.get(demande.getSortField())));
			} else {
				cq.orderBy(cb.desc(user.get(demande.getSortField())));
			}
		}
		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);

		return em.createQuery(cq).setFirstResult(page * size).setMaxResults(size).getResultList();

	}

	@Override
	public Long countByCriteres(Demande<UserDTO> demande) {
		 cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		user = cq.from(User.class);
		cq.select(cb.count(user));
		predicates = getPredicate(demande);

		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);
		return em.createQuery(cq).getSingleResult();
	}

	private List<Predicate> getPredicate(Demande<UserDTO> demande) {
		List<Predicate> predicates = new ArrayList<>();
		UserDTO userDTO = demande.getModel();

		if (!StringUtils.isEmpty(userDTO.getFirstName())) {
			predicates.add(
					cb.like(cb.lower(user.<String>get("firstName")), "%" + userDTO.getFirstName().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(userDTO.getLastName())) {
			predicates.add(
					cb.like(cb.lower(user.<String>get("lastName")), "%" + userDTO.getLastName().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(userDTO.getEmail())) {
			predicates.add(cb.like(cb.lower(user.<String>get("email")), "%" + userDTO.getEmail().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(userDTO.getRefRole()) && userDTO.getRefRole() != null) {
			predicates.add(cb.equal(user.<Long>get("refRole"), userDTO.getRefRole().getId()));

		}

		if (!StringUtils.isEmpty(userDTO.getOrganization()) && userDTO.getOrganization() != null) {
			predicates.add(cb.equal(user.<Long>get("organization"), userDTO.getOrganization().getId()));
		}
		if (!StringUtils.isEmpty(userDTO.getLevel()) && userDTO.getLevel() != null) {
			predicates.add(cb.equal(user.<Long>get("level"), userDTO.getLevel().getId()));
		}
		if (!StringUtils.isEmpty(userDTO.getBranch()) && userDTO.getBranch() != null) {
			predicates.add(cb.equal(user.<Long>get("branch"), userDTO.getBranch().getId()));
		}

		return predicates;
	}

}
