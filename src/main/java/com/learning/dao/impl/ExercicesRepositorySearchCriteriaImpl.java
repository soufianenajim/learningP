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

import com.learning.dao.ExercicesRepositorySearchCriteria;
import com.learning.dto.ExercicesDTO;
import com.learning.model.Exercices;
import com.learning.model.TypeEnum;
import com.learning.model.base.Demande;
import com.learning.model.base.SortOrder;

@Repository
public class ExercicesRepositorySearchCriteriaImpl implements ExercicesRepositorySearchCriteria {
	
	@Autowired
	private EntityManager em;
	
	private CriteriaBuilder cb = null;

	private Root<Exercices> exercices = null;

	private List<Predicate> predicates = null;

	@Override
	public List<Exercices> findByCriteres(Demande<ExercicesDTO> demande) {
		cb = em.getCriteriaBuilder();
		CriteriaQuery<Exercices> cq = cb.createQuery(Exercices.class);

		exercices = cq.from(Exercices.class);
		predicates = getPredicate(demande);
		int page = demande.getPage();
		int size = demande.getSize();
		if (!StringUtils.isEmpty(demande.getSortField()) && !StringUtils.isEmpty(demande.getSortOrder())
				&& !demande.getSortOrder().equals(SortOrder.NONE)) {
			if (demande.getSortOrder().equals(SortOrder.ASCENDING)) {
				cq.orderBy(cb.asc(exercices.get(demande.getSortField())));
			} else {
				cq.orderBy(cb.desc(exercices.get(demande.getSortField())));
			}
		}
		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);

		return em.createQuery(cq).setFirstResult(page * size).setMaxResults(size).getResultList();

	}

	@Override
	public Long countByCriteres(Demande<ExercicesDTO> demande) {
		 cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		exercices = cq.from(Exercices.class);
		cq.select(cb.count(exercices));
		predicates = getPredicate(demande);

		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);
		return em.createQuery(cq).getSingleResult();
	}

	private List<Predicate> getPredicate(Demande<ExercicesDTO> demande) {
		List<Predicate> predicates = new ArrayList<>();
		ExercicesDTO exercicesDTO = demande.getModel();

		if (!StringUtils.isEmpty(exercicesDTO.getName())) {
			predicates.add(
					cb.like(cb.lower(exercices.<String>get("name")), "%" + exercicesDTO.getName().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(exercicesDTO.getCour()) && exercicesDTO.getCour() != null) {
			predicates.add(cb.equal(exercices.<Long>get("cour"), exercicesDTO.getCour().getId()));
		}
		if (!StringUtils.isEmpty(exercicesDTO.getModule()) && exercicesDTO.getModule() != null) {
			predicates.add(cb.equal(exercices.<Long>get("cour").get("module"), exercicesDTO.getModule().getId()));
		}
		if (!StringUtils.isEmpty(exercicesDTO.getType())) {
			predicates.add(cb.equal(exercices.<TypeEnum>get("type"), exercicesDTO.getType()));
		}

		return predicates;
	}

}
