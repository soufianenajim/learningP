package com.learning.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.learning.dao.ProgressionModuleRepositorySearchCriteria;
import com.learning.dto.ModuleAffectedDTO;
import com.learning.dto.ProgressionModuleDTO;
import com.learning.dto.UserDTO;
import com.learning.model.Group;
import com.learning.model.ProgressionModule;
import com.learning.model.base.Demande;
import com.learning.model.base.SortOrder;

@Repository
public class ProgressionModuleRepositorySearchCriteriaImpl implements ProgressionModuleRepositorySearchCriteria {

	@Autowired
	private EntityManager em;

	private CriteriaBuilder cb = null;

	private Root<ProgressionModule> progressionModule = null;

	private List<Predicate> predicates = null;

	@Override
	public List<ProgressionModule> findByCriteres(Demande<ProgressionModuleDTO> demande) {
		cb = em.getCriteriaBuilder();
		CriteriaQuery<ProgressionModule> cq = cb.createQuery(ProgressionModule.class);

		progressionModule = cq.from(ProgressionModule.class);
		predicates = getPredicate(demande);
		int page = demande.getPage();
		int size = demande.getSize();
		if (!StringUtils.isEmpty(demande.getSortField()) && !StringUtils.isEmpty(demande.getSortOrder())
				&& !demande.getSortOrder().equals(SortOrder.NONE)) {
			if (demande.getSortOrder().equals(SortOrder.ASCENDING)) {
				cq.orderBy(cb.asc(progressionModule.get(demande.getSortField())));
			} else {
				cq.orderBy(cb.desc(progressionModule.get(demande.getSortField())));
			}
		}
		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);

		return em.createQuery(cq).setFirstResult(page * size).setMaxResults(size).getResultList();

	}

	@Override
	public Long countByCriteres(Demande<ProgressionModuleDTO> demande) {
		cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		progressionModule = cq.from(ProgressionModule.class);
		cq.select(cb.count(progressionModule));
		predicates = getPredicate(demande);

		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);
		return em.createQuery(cq).getSingleResult();
	}

	private List<Predicate> getPredicate(Demande<ProgressionModuleDTO> demande) {
		List<Predicate> predicates = new ArrayList<>();
		ProgressionModuleDTO progressionModuleDTO = demande.getModel();
		UserDTO student = progressionModuleDTO.getStudent();
		ModuleAffectedDTO module = progressionModuleDTO.getModule();
		boolean isTeacher = progressionModuleDTO.isTeacher();
		if (student != null) {
			if (student.getId() != null) {
				if (!isTeacher) {
					predicates.add(cb.equal(progressionModule.<Long>get("student"), student.getId()));
				} else {
					predicates.add(cb.equal(progressionModule.<Long>get("module").get("professor"), student.getId()));
				}
			}
			if (isTeacher) {
				if (!StringUtils.isEmpty(student.getFirstName())) {
					predicates.add(cb.like(cb.lower(progressionModule.<String>get("student").get("firstName")),
							"%" + student.getFirstName().toLowerCase() + "%"));
				}
				if (!StringUtils.isEmpty(student.getLastName())) {
					predicates.add(cb.like(cb.lower(progressionModule.<String>get("student").get("lastName")),
							"%" + student.getLastName().toLowerCase() + "%"));
				}
				if (!StringUtils.isEmpty(student.getEmail())) {
					predicates.add(cb.like(cb.lower(progressionModule.<String>get("student").get("email")),
							"%" + student.getEmail().toLowerCase() + "%"));
				}
				if (student.getGroupId() != null) {
					Join<ProgressionModule, Group> vulGroup = progressionModule.join("student").join("groups");

					predicates.add(cb.equal(vulGroup.<Long>get("id"), student.getGroupId()));
				}
			}

		}
		if (module != null) {
			predicates.add(cb.equal(progressionModule.<Long>get("module"), module.getId()));
		}

		return predicates;
	}

}
