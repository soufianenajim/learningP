package com.learning.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.SessionRepository;
import com.learning.dto.SessionDTO;
import com.learning.model.Session;
import com.learning.model.Organization;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.SessionService;
import com.learning.service.OrganizationService;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private OrganizationService organizationService;

	// save or update
	@Override
	public SessionDTO save(SessionDTO sessionDTO) {
		if (sessionDTO.getId() != null) {
			if (!existingSessionById(sessionDTO.getId(), sessionDTO.getName(), sessionDTO.getOrganization().getId()))
				return null;
		} else if (!existingSession(sessionDTO.getName(), sessionDTO.getOrganization().getId()))
			return null;
		Session session = convertDTOtoModel(sessionDTO);
		session = sessionRepository.save(session);
		return convertModelToDTO(session);
	}

	@Override
	public SessionDTO findById(long idOut) {
		Optional<Session> optional = sessionRepository.findById(idOut);

		if (optional.isPresent()) {
			Session sessionFromDb = optional.get();
			return convertModelToDTO(sessionFromDb);
		}
		return null;
	}

	@Override
	public void delete(Session session) {
		sessionRepository.delete(session);
	}

	@Override
	public PartialList<SessionDTO> findByCriteres(Demande<SessionDTO> demande) {

		SessionDTO session = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Page<Session> pageSession = null;
		String name = session.getName();
		Long idOrganization = session.getOrganization() != null ? session.getOrganization().getId() : null;

		pageSession = idOrganization != null
				? sessionRepository.findByNameAndOrganization(name, idOrganization, PageRequest.of(page, size))
				: sessionRepository.findByName(name, PageRequest.of(page, size));

		List<SessionDTO> list = convertEntitiesToDtos(pageSession.getContent());
		Long totalElement = pageSession.getTotalElements();
		return new PartialList<SessionDTO>(totalElement, list);
	}

	@Override
	public Session convertDTOtoModel(SessionDTO sessionDTO) {
		Session session = new Session();
		session.setId(sessionDTO.getId());
		session.setName(sessionDTO.getName());
		session.setStartDate(sessionDTO.getStartDate());
		session.setEndDate(sessionDTO.getEndDate());
		session.setStartDateExam(sessionDTO.getStartDateExam());
		session.setEndDateExam(sessionDTO.getEndDateExam());
		session.setStartDateCatchUp(sessionDTO.getStartDateCatchUp());
		session.setEndDateCatchUp(sessionDTO.getEndDateCatchUp());

		if (sessionDTO.getOrganization() != null) {
			session.setOrganization(organizationService.convertDTOtoModel(sessionDTO.getOrganization()));
		}
		return session;
	}

	@Override
	public SessionDTO convertModelToDTO(Session session) {
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setId(session.getId());
		sessionDTO.setName(session.getName());
		sessionDTO.setStartDate(session.getStartDate());
		sessionDTO.setEndDate(session.getEndDate());
		sessionDTO.setStartDateExam(session.getStartDateExam());
		sessionDTO.setEndDateExam(session.getEndDateExam());
		sessionDTO.setStartDateCatchUp(session.getStartDateCatchUp());
		sessionDTO.setEndDateCatchUp(session.getEndDateCatchUp());
		Organization organization = session.getOrganization();
		if (organization != null) {
			sessionDTO.setOrganization(organizationService.convertModelToDTO(organization));

		}

		sessionDTO.setCreatedAt(session.getCreatedAt());
		sessionDTO.setUpdatedAt(session.getUpdatedAt());
		return sessionDTO;
	}

	@Override
	public PartialList<SessionDTO> convertToListDTO(PartialList<Session> list) {

		return null;
	}

	@Override
	public void deleteById(Long id) {
		sessionRepository.deleteById(id);

	}

	@Override
	public List<SessionDTO> convertEntitiesToDtos(List<Session> sessions) {
		// basic methode
		List<SessionDTO> list = new ArrayList<SessionDTO>();
		for (Session session : sessions) {
			list.add(convertModelToDTO(session));
		}
		return list;
	}

	@Override
	public List<Session> convertDtosToEntities(List<SessionDTO> sessionsDTO) {
		List<Session> list = new ArrayList<Session>();
		for (SessionDTO sessionDTO : sessionsDTO) {
			list.add(convertDTOtoModel(sessionDTO));
		}
		return list;
	}

	@Override
	public List<SessionDTO> findAll() {
		List<Session> list = sessionRepository.findAll();
		return convertEntitiesToDtos(list);
	}

	@Override
	public Session convertDTOtoModelWithOutOrganization(SessionDTO sessionDTO) {
		Session session = new Session();
		session.setId(sessionDTO.getId());
		session.setName(sessionDTO.getName());
		return session;
	}

	@Override
	public SessionDTO convertModelToDTOWithOutOrganization(Session session) {
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setId(session.getId());
		sessionDTO.setName(session.getName());
		return sessionDTO;
	}

	@Override
	public List<SessionDTO> convertEntitiesToDtosWithOutOrganization(List<Session> list) {
		List<SessionDTO> sessions = new ArrayList<>();
		for (Session session : list) {
			sessions.add(convertModelToDTOWithOutOrganization(session));
		}
		return sessions;
	}

	@Override
	public List<Session> convertDtosToEntitiesWithOutOrganization(List<SessionDTO> list) {
		List<Session> sessions = new ArrayList<>();
		for (SessionDTO session : list) {
			sessions.add(convertDTOtoModelWithOutOrganization(session));
		}
		return sessions;
	}

	@Override
	public void saveSessionsByOrganization(List<SessionDTO> sessions, Organization organization) {
		for (SessionDTO sessionDTO : sessions) {
			Session session = convertDTOtoModel(sessionDTO);
			session.setOrganization(organization);
			sessionRepository.save(session);

		}

	}

	@Override
	public void deleteByOrganizationId(Long id) {
		sessionRepository.deleteByOrganisation(id);

	}

	@Override
	public List<SessionDTO> findByOrganization(Long id) {

		return convertEntitiesToDtosWithOutOrganization(sessionRepository.findByOrganization(id));
	}

	@Override
	public boolean existingSession(String name, Long idOrganization) {
		Session existSession = sessionRepository.findByNameAndOrganization(name, idOrganization);
		return existSession == null || existSession.getId() == null;
	}

	@Override
	public boolean existingSessionById(Long id, String name, Long idOrganization) {
		Session existSession = sessionRepository.findByNameAndOrganization(name, idOrganization);
		return existSession == null || existSession.getId().equals(id);
	}

	@Override
	public SessionDTO findCurrentSessionByOranization(Long idOrg,LocalDate localDate) {
		
		return convertModelToDTO(sessionRepository.findCurrentByOrganization(idOrg, localDate));
	}

	@Override
	public SessionDTO convertModelToDTOWithOuRelation(Session session) {
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setId(session.getId());
		sessionDTO.setName(session.getName());
		sessionDTO.setStartDate(session.getStartDate());
		sessionDTO.setEndDate(session.getEndDate());
		sessionDTO.setStartDateExam(session.getStartDateExam());
		sessionDTO.setEndDateExam(session.getEndDateExam());
		sessionDTO.setStartDateCatchUp(session.getStartDateCatchUp());
		sessionDTO.setEndDateCatchUp(session.getEndDateCatchUp());
		return sessionDTO;
	}

}
