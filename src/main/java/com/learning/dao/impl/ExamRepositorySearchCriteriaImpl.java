package com.learning.dao.impl;

import java.time.LocalDateTime;
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

import com.learning.dao.ExamRepositorySearchCriteria;
import com.learning.dto.ExamDTO;
import com.learning.model.Exam;
import com.learning.model.TypeEnum;
import com.learning.model.TypeEnumExam;
import com.learning.model.base.Demande;
import com.learning.model.base.SortOrder;

@Repository
public class ExamRepositorySearchCriteriaImpl implements ExamRepositorySearchCriteria {

	@Autowired
	private EntityManager em;

	private CriteriaBuilder cb = null;

	private Root<Exam> exam = null;

	private List<Predicate> predicates = null;

	@Override
	public List<Exam> findByCriteres(Demande<ExamDTO> demande) {
		cb = em.getCriteriaBuilder();
		CriteriaQuery<Exam> cq = cb.createQuery(Exam.class);

		exam = cq.from(Exam.class);
		predicates = getPredicate(demande);
		int page = demande.getPage();
		int size = demande.getSize();
		if (!StringUtils.isEmpty(demande.getSortField()) && !StringUtils.isEmpty(demande.getSortOrder())
				&& !demande.getSortOrder().equals(SortOrder.NONE)) {
			if (demande.getSortOrder().equals(SortOrder.ASCENDING)) {
				cq.orderBy(cb.asc(exam.get(demande.getSortField())));
			} else {
				cq.orderBy(cb.desc(exam.get(demande.getSortField())));
			}
		}
		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);

		return em.createQuery(cq).setFirstResult(page * size).setMaxResults(size).getResultList();

	}

	@Override
	public Long countByCriteres(Demande<ExamDTO> demande) {
		cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		exam = cq.from(Exam.class);
		cq.select(cb.count(exam));
		predicates = getPredicate(demande);

		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);
		return em.createQuery(cq).getSingleResult();
	}

	private List<Predicate> getPredicate(Demande<ExamDTO> demande) {
		List<Predicate> predicates = new ArrayList<>();
		ExamDTO examDTO = demande.getModel();

		if (!StringUtils.isEmpty(examDTO.getName())) {
			predicates.add(cb.like(cb.lower(exam.<String>get("name")), "%" + examDTO.getName().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(examDTO.getModule()) && examDTO.getModule() != null) {
			predicates.add(cb.equal(exam.<Long>get("module"), examDTO.getModule().getId()));
		}

		if (!StringUtils.isEmpty(examDTO.getType())) {
			predicates.add(cb.equal(exam.<TypeEnum>get("type"), TypeEnumExam.valueOf(examDTO.getType())));
		}
		if (examDTO.isAfterCurrentDate()) {
			LocalDateTime current = LocalDateTime.now();
			predicates.add(cb.greaterThanOrEqualTo((exam.<LocalDateTime>get("startDateTime")), current));
		}

		return predicates;
	}

}
