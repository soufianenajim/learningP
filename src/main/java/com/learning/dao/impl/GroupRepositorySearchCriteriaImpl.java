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

import com.learning.dao.GroupRepositorySearchCriteria;
import com.learning.dto.GroupDTO;
import com.learning.model.Group;
import com.learning.model.base.Demande;
import com.learning.model.base.SortOrder;

@Repository
public class GroupRepositorySearchCriteriaImpl implements GroupRepositorySearchCriteria {

	@Autowired
	private EntityManager em;

	private CriteriaBuilder cb = null;

	private Root<Group> group = null;

	private List<Predicate> predicates = null;

	@Override
	public List<Group> findByCriteres(Demande<GroupDTO> demande) {
		cb = em.getCriteriaBuilder();
		CriteriaQuery<Group> cq = cb.createQuery(Group.class);

		group = cq.from(Group.class);
		predicates = getPredicate(demande);
		int page = demande.getPage();
		int size = demande.getSize();
		if (!StringUtils.isEmpty(demande.getSortField()) && !StringUtils.isEmpty(demande.getSortOrder())
				&& !demande.getSortOrder().equals(SortOrder.NONE)) {
			if (demande.getSortOrder().equals(SortOrder.ASCENDING)) {
				cq.orderBy(cb.asc(group.get(demande.getSortField())));
			} else {
				cq.orderBy(cb.desc(group.get(demande.getSortField())));
			}
		}
		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);

		return em.createQuery(cq).setFirstResult(page * size).setMaxResults(size).getResultList();

	}

	@Override
	public Long countByCriteres(Demande<GroupDTO> demande) {
		cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		group = cq.from(Group.class);
		cq.select(cb.count(group));
		predicates = getPredicate(demande);

		cq.where(predicates.toArray(new Predicate[0]));
		cq.distinct(true);
		return em.createQuery(cq).getSingleResult();
	}

	private List<Predicate> getPredicate(Demande<GroupDTO> demande) {
		List<Predicate> predicates = new ArrayList<>();
		GroupDTO groupDTO = demande.getModel();

		if (!StringUtils.isEmpty(groupDTO.getName())) {
			predicates.add(cb.like(cb.lower(group.<String>get("name")), "%" + groupDTO.getName().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(groupDTO.getLevel()) && groupDTO.getLevel() != null) {
			predicates.add(cb.equal(group.<Long>get("level"), groupDTO.getLevel().getId()));
		}
		if (!StringUtils.isEmpty(groupDTO.getBranch()) && groupDTO.getBranch() != null) {
			predicates.add(cb.equal(group.<Long>get("branch"), groupDTO.getBranch().getId()));
		}
		if (groupDTO.getOrganizationId() != null && groupDTO.getOrganizationId() != 0) {
			predicates.add(cb.equal(group.<Long>get("level").get("organization"), groupDTO.getOrganizationId()));
		}

		return predicates;
	}

}
