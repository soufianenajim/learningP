package com.learning.service;

import java.time.LocalDate;
import java.util.List;

import com.learning.dto.SessionDTO;
import com.learning.model.Organization;
import com.learning.model.Session;

public interface SessionService extends CrudService<Session, SessionDTO> {
	List<SessionDTO> findAll();

	void saveSessionsByOrganization(List<SessionDTO> sessions, Organization organization);

	Session convertDTOtoModelWithOutOrganization(SessionDTO dto);

	SessionDTO convertModelToDTOWithOutOrganization(final Session model);

	List<SessionDTO> convertEntitiesToDtosWithOutOrganization(List<Session> list);

	List<Session> convertDtosToEntitiesWithOutOrganization(List<SessionDTO> list);

	void deleteByOrganizationId(Long id);

	List<SessionDTO> findByOrganization(Long id);

	boolean existingSession(String name, Long idOrganization);

	boolean existingSessionById(Long id, String name, Long idOrganization);

	SessionDTO findCurrentSessionByOranization(Long idOrg,LocalDate localDate);
}
