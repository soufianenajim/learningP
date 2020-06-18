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

import com.learning.dao.ModuleAffectedRepositorySearchCriteria;
import com.learning.dto.ModuleAffectedDTO;
import com.learning.model.ModuleAffected;
import com.learning.model.base.Demande;
import com.learning.model.base.SortOrder;

@Repository
public class ModuleAffectedRepositorySearchCriteriaImpl implements ModuleAffectedRepositorySearchCriteria {

	@Autowired
	private EntityManager em;

	private CriteriaBuilder cb = null;

	private Root<ModuleAffected> module = null;

	private List<Predicate> predicates = null;

	@Override
	public List<ModuleAffected> findByCriteres(Demande<ModuleAffectedDTO> demande) {
		cb = em.getCriteriaBuilder();
		CriteriaQuery<ModuleAffected> cq = cb.createQuery(ModuleAffected.class);

		module = cq.from(ModuleAffected.class);
		predicates = getPredicate(demande);
		int page = demande.getPage();
		int size = demande.getSize();
		if (!StringUtils.isEmpty(demande.getSortField()) && !StringUtils.isEmpty(demande.getSortOrder())
				&& !demande.getSortOrder().equals(SortOrder.NONE)) {
			if (demande.getSortOrder().equals(SortOrder.ASCENDING)) {
				cq.orderBy(cb.asc(module.get(demande.getSortField())));
			} else {
				cq.orderBy(cb.desc(module.get(demande.getSortField())));
			}
		}
		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);

		return em.createQuery(cq).setFirstResult(page * size).setMaxResults(size).getResultList();

	}

	@Override
	public Long countByCriteres(Demande<ModuleAffectedDTO> demande) {
		cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		module = cq.from(ModuleAffected.class);
		cq.select(cb.count(module));
		predicates = getPredicate(demande);

		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);
		return em.createQuery(cq).getSingleResult();
	}

	private List<Predicate> getPredicate(Demande<ModuleAffectedDTO> demande) {
		List<Predicate> predicates = new ArrayList<>();
		ModuleAffectedDTO moduleDTO = demande.getModel();

		if (!StringUtils.isEmpty(moduleDTO.getName())) {
			predicates
					.add(cb.like(cb.lower(module.<String>get("name")), "%" + moduleDTO.getName().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(moduleDTO.getProfessor()) && moduleDTO.getProfessor() != null) {
			predicates.add(cb.equal(module.<Long>get("professor"), moduleDTO.getProfessor().getId()));
		}
		if (!StringUtils.isEmpty(moduleDTO.getGroup()) && moduleDTO.getGroup() != null) {
			predicates.add(cb.equal(module.<Long>get("group"), moduleDTO.getGroup().getId()));
		}
		if (!StringUtils.isEmpty(moduleDTO.getIdOrganization()) && moduleDTO.getIdOrganization() != null) {
			predicates.add(cb.equal(module.<Long>get("professor").get("organization"),
					moduleDTO.getIdOrganization()));
		}
		if (moduleDTO.getBranchId() != 0) {
			predicates.add(cb.equal(module.<Long>get("group").get("branch"), moduleDTO.getBranchId()));
		}

		if (moduleDTO.getLevelId() != 0) {
			predicates.add(cb.equal(module.<Long>get("group").get("level"), moduleDTO.getLevelId()));
		}
		

		return predicates;
	}

}
