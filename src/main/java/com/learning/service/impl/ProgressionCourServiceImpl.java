package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.ProgressionCourRepository;
import com.learning.dto.ProgressionCourDTO;
import com.learning.dto.UserDTO;
import com.learning.model.Cour;
import com.learning.model.ProgressionCour;
import com.learning.model.User;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.CourService;
import com.learning.service.ProgressionCourService;
import com.learning.service.UserService;

@Service
public class ProgressionCourServiceImpl implements ProgressionCourService {

	@Autowired
	private ProgressionCourRepository progressionCourRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private CourService courService;

	// save or update
	@Override
	public ProgressionCourDTO save(ProgressionCourDTO progressionCourDTO) {
		ProgressionCour progressionCour = convertDTOtoModel(progressionCourDTO);
		progressionCour = progressionCourRepository.save(progressionCour);
		return convertModelToDTO(progressionCour);
	}

	@Override
	public ProgressionCourDTO findById(long idOut) {
		Optional<ProgressionCour> optional = progressionCourRepository.findById(idOut);

		if (optional.isPresent()) {
			ProgressionCour progressionCourFromDb = optional.get();
			return convertModelToDTO(progressionCourFromDb);
		}
		return null;
	}

	@Override
	public void delete(ProgressionCour progressionCour) {
		progressionCourRepository.delete(progressionCour);
	}

	@Override
	public PartialList<ProgressionCourDTO> findByCriteres(Demande<ProgressionCourDTO> demande) {

		ProgressionCourDTO progressionCour = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Long idStudent = progressionCour.getStudent()!=null ?progressionCour.getStudent().getId():null;
		Long idModule=progressionCour.getModuleId();
		Long idCour = progressionCour.getCour()!=null?progressionCour.getCour().getId():null;
		Page<ProgressionCour> pageProgressionCour = idStudent != null
				? idCour != null
						? progressionCourRepository.findByStudentAndCour(idStudent, idCour, PageRequest.of(page, size))
						: progressionCourRepository.findByStudentAndModule(idStudent,idModule, PageRequest.of(page, size))
				: null;

		if (pageProgressionCour != null) {
			List<ProgressionCourDTO> list = convertEntitiesToDtos(pageProgressionCour.getContent());
			Long totalElement = pageProgressionCour.getTotalElements();

			return new PartialList<ProgressionCourDTO>(totalElement, list);
		} else {
			return new PartialList<ProgressionCourDTO>(0l, new ArrayList<ProgressionCourDTO>());
		}
	}

	@Override
	public ProgressionCour convertDTOtoModel(ProgressionCourDTO progressionCourDTO) {
		ProgressionCour progressionCour = new ProgressionCour();
		progressionCour.setId(progressionCourDTO.getId());
		progressionCour.setCourFinished(progressionCourDTO.isCourFinished());

		progressionCour.setTdFinished(progressionCourDTO.isTdFinished());
		progressionCour.setQuizFinished(progressionCourDTO.isQuizFinished());
		progressionCour.setProgression(progressionCourDTO.getProgression());
		progressionCour.setScoreQuiz(progressionCourDTO.getScoreQuiz());

		if (progressionCourDTO.getCour() != null) {
			progressionCour.setCour(courService.convertDTOtoModelWithOutModule(progressionCourDTO.getCour()));
		}
		if (progressionCourDTO.getStudent() != null) {
			progressionCour.setStudent(userService.convertDTOtoModelWithOutRelation(progressionCourDTO.getStudent()));
		}

		return progressionCour;
	}

	@Override
	public ProgressionCourDTO convertModelToDTO(ProgressionCour progressionCour) {
		ProgressionCourDTO progressionCourDTO = new ProgressionCourDTO();
		progressionCourDTO.setId(progressionCour.getId());
		progressionCourDTO.setCourFinished(progressionCour.isCourFinished());

		progressionCourDTO.setTdFinished(progressionCour.isTdFinished());
		progressionCourDTO.setQuizFinished(progressionCour.isQuizFinished());
		progressionCourDTO.setProgression(progressionCour.getProgression());
		progressionCourDTO.setScoreQuiz(progressionCour.getScoreQuiz());

		Cour cour = progressionCour.getCour();
		User student = progressionCour.getStudent();
		if (cour != null) {
			progressionCourDTO.setCour(courService.convertModelToDTOWithOutModule(cour));
		}

		if (student != null) {
			progressionCourDTO.setStudent(userService.convertModelToDTOWithOutRelation(student));
		}
		return progressionCourDTO;
	}

	@Override
	public PartialList<ProgressionCourDTO> convertToListDTO(PartialList<ProgressionCour> list) {

		return null;
	}

	@Override
	public void deleteById(Long id) {
		progressionCourRepository.deleteById(id);

	}

	@Override
	public List<ProgressionCourDTO> convertEntitiesToDtos(List<ProgressionCour> progressionCours) {
		// basic methode
		List<ProgressionCourDTO> list = new ArrayList<ProgressionCourDTO>();
		for (ProgressionCour progressionCour : progressionCours) {
			list.add(convertModelToDTO(progressionCour));
		}
		return list;
	}

	@Override
	public List<ProgressionCour> convertDtosToEntities(List<ProgressionCourDTO> progressionCoursDTO) {
		List<ProgressionCour> list = new ArrayList<ProgressionCour>();
		for (ProgressionCourDTO progressionCourDTO : progressionCoursDTO) {
			list.add(convertDTOtoModel(progressionCourDTO));
		}
		return list;
	}

	@Override
	public void saveByCourAndStudents(Cour cour, List<UserDTO> students) {
		for (UserDTO student : students) {
			ProgressionCour progressionCour=new ProgressionCour();
			progressionCour.setCour(cour);
			progressionCour.setStudent(userService.convertDTOtoModel(student));
			progressionCour.setProgression(0.0);
			
			progressionCourRepository.save(progressionCour);
		}
		
	}

	
	

}
