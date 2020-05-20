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

import com.learning.dao.NoteExamRepositorySearchCriteria;
import com.learning.dto.NoteExamDTO;
import com.learning.model.NoteExam;
import com.learning.model.base.Demande;
import com.learning.model.base.SortOrder;

@Repository
public class NoteExamRepositorySearchCriteriaImpl implements NoteExamRepositorySearchCriteria {

	@Autowired
	private EntityManager em;

	private CriteriaBuilder cb = null;

	private Root<NoteExam> noteExam = null;

	private List<Predicate> predicates = null;

	@Override
	public List<NoteExam> findByCriteres(Demande<NoteExamDTO> demande) {
		cb = em.getCriteriaBuilder();
		CriteriaQuery<NoteExam> cq = cb.createQuery(NoteExam.class);

		noteExam = cq.from(NoteExam.class);
		predicates = getPredicate(demande);
		int page = demande.getPage();
		int size = demande.getSize();
		if (!StringUtils.isEmpty(demande.getSortField()) && !StringUtils.isEmpty(demande.getSortOrder())
				&& !demande.getSortOrder().equals(SortOrder.NONE)) {
			if (demande.getSortOrder().equals(SortOrder.ASCENDING)) {
				cq.orderBy(cb.asc(noteExam.get(demande.getSortField())));
			} else {
				cq.orderBy(cb.desc(noteExam.get(demande.getSortField())));
			}
		}
		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);

		return em.createQuery(cq).setFirstResult(page * size).setMaxResults(size).getResultList();

	}

	@Override
	public Long countByCriteres(Demande<NoteExamDTO> demande) {
		cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		noteExam = cq.from(NoteExam.class);
		cq.select(cb.count(noteExam));
		predicates = getPredicate(demande);

		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);
		return em.createQuery(cq).getSingleResult();
	}

	private List<Predicate> getPredicate(Demande<NoteExamDTO> demande) {
		List<Predicate> predicates = new ArrayList<>();
		NoteExamDTO noteExamDTO = demande.getModel();

		if (!StringUtils.isEmpty(noteExamDTO.getExam()) && noteExamDTO.getExam() != null) {
			predicates.add(cb.equal(noteExam.<Long>get("exam"), noteExamDTO.getExam().getId()));
		}


		if (!StringUtils.isEmpty(noteExamDTO.getUser())) {
			predicates.add(cb.equal(noteExam.<Long>get("user"), noteExamDTO.getUser().getId()));
		}

		if (!StringUtils.isEmpty(noteExamDTO.getModule())) {
			
			predicates.add(cb.equal(noteExam.<Long>get("exam").get("module"), noteExamDTO.getModule().getId()));
		}

		return predicates;
	}

}
