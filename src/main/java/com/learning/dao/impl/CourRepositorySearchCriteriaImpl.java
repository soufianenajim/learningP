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

import com.learning.dao.CourRepositorySearchCriteria;
import com.learning.dto.CourDTO;
import com.learning.model.Cour;
import com.learning.model.Exam;
import com.learning.model.NoteExam;
import com.learning.model.ProgressionCour;
import com.learning.model.base.Demande;
import com.learning.model.base.SortOrder;

@Repository
public class CourRepositorySearchCriteriaImpl implements CourRepositorySearchCriteria {

	@Autowired
	private EntityManager em;

	private CriteriaBuilder cb = null;

	private Root<Cour> cour = null;

	private List<Predicate> predicates = null;

	@Override
	public List<Cour> findByCriteres(Demande<CourDTO> demande) {
		cb = em.getCriteriaBuilder();
		CriteriaQuery<Cour> cq = cb.createQuery(Cour.class);

		cour = cq.from(Cour.class);
		predicates = getPredicate(demande);
		int page = demande.getPage();
		int size = demande.getSize();
		if (!StringUtils.isEmpty(demande.getSortField()) && !StringUtils.isEmpty(demande.getSortOrder())
				&& !demande.getSortOrder().equals(SortOrder.NONE)) {
			if (demande.getSortOrder().equals(SortOrder.ASCENDING)) {
				cq.orderBy(cb.asc(cour.get(demande.getSortField())));
			} else {
				cq.orderBy(cb.desc(cour.get(demande.getSortField())));
			}
		}
		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);

		return em.createQuery(cq).setFirstResult(page * size).setMaxResults(size).getResultList();

	}

	@Override
	public Long countByCriteres(Demande<CourDTO> demande) {
		cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		cour = cq.from(Cour.class);
		cq.select(cb.count(cour));
		predicates = getPredicate(demande);

		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);
		return em.createQuery(cq).getSingleResult();
	}

	private List<Predicate> getPredicate(Demande<CourDTO> demande) {
		List<Predicate> predicates = new ArrayList<>();
		CourDTO courDTO = demande.getModel();

		if (!StringUtils.isEmpty(courDTO.getName())) {
			predicates.add(cb.like(cb.lower(cour.<String>get("name")), "%" + courDTO.getName().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(courDTO.getModule()) && courDTO.getModule() != null) {
			predicates.add(cb.equal(cour.<Long>get("module"), courDTO.getModule().getId()));
		}

		if (!StringUtils.isEmpty(courDTO.getModule()) && courDTO.getModule() != null) {
			predicates.add(cb.equal(cour.<Long>get("module"), courDTO.getModule().getId()));
		}
		if (!StringUtils.isEmpty(courDTO.getStudent()) && courDTO.getStudent() != null) {
			Join<Cour, ProgressionCour> progressionCour = cour.join("progressionCours");

			
			predicates.add(cb.equal(progressionCour.<Long>get("student"), courDTO.getStudent().getId()));
		}
       if(courDTO.getIdTeacher()!=null) {
    	   predicates.add(cb.equal(cour.<Long>get("module").get("professor"), courDTO.getIdTeacher()));
       }
		

		return predicates;
	}

}
