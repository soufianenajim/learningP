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

import com.learning.dao.QuestionRepositorySearchCriteria;
import com.learning.dto.QuestionDTO;
import com.learning.model.Question;
import com.learning.model.base.Demande;
import com.learning.model.base.SortOrder;

@Repository
public class QuestionRepositorySearchCriteriaImpl implements QuestionRepositorySearchCriteria {
	
	@Autowired
	private EntityManager em;
	
	private CriteriaBuilder cb = null;

	private Root<Question> question = null;

	private List<Predicate> predicates = null;

	@Override
	public List<Question> findByCriteres(Demande<QuestionDTO> demande) {
		cb = em.getCriteriaBuilder();
		CriteriaQuery<Question> cq = cb.createQuery(Question.class);

		question = cq.from(Question.class);
		predicates = getPredicate(demande);
		int page = demande.getPage();
		int size = demande.getSize();
		if (!StringUtils.isEmpty(demande.getSortField()) && !StringUtils.isEmpty(demande.getSortOrder())
				&& !demande.getSortOrder().equals(SortOrder.NONE)) {
			if (demande.getSortOrder().equals(SortOrder.ASCENDING)) {
				cq.orderBy(cb.asc(question.get(demande.getSortField())));
			} else {
				cq.orderBy(cb.desc(question.get(demande.getSortField())));
			}
		}
		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);

		return em.createQuery(cq).setFirstResult(page * size).setMaxResults(size).getResultList();

	}

	@Override
	public Long countByCriteres(Demande<QuestionDTO> demande) {
		 cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		question = cq.from(Question.class);
		cq.select(cb.count(question));
		predicates = getPredicate(demande);

		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);
		return em.createQuery(cq).getSingleResult();
	}

	private List<Predicate> getPredicate(Demande<QuestionDTO> demande) {
		List<Predicate> predicates = new ArrayList<>();
		QuestionDTO questionDTO = demande.getModel();

		if (!StringUtils.isEmpty(questionDTO.getCode())) {
			predicates.add(
					cb.like(cb.lower(question.<String>get("code")), "%" + questionDTO.getCode().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(questionDTO.getName())) {
			predicates.add(
					cb.like(cb.lower(question.<String>get("name")), "%" + questionDTO.getName().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(questionDTO.getExam()) && questionDTO.getExam() != null) {
			predicates.add(cb.equal(question.<Long>get("exam"), questionDTO.getExam().getId()));
		}
		if (!StringUtils.isEmpty(questionDTO.getExercices()) && questionDTO.getExercices() != null) {
			predicates.add(cb.equal(question.<Long>get("quiz"), questionDTO.getExercices().getId()));
		}
		

		return predicates;
	}

}
