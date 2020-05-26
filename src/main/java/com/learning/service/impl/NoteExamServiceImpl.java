package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dao.NoteExamRepository;
import com.learning.dao.NoteExamRepositorySearchCriteria;
import com.learning.dto.NoteExamDTO;
import com.learning.dto.UserDTO;
import com.learning.model.Exam;
import com.learning.model.NoteExam;
import com.learning.model.Suggestion;
import com.learning.model.TypeEnumExam;
import com.learning.model.User;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.ExamService;
import com.learning.service.NoteExamService;
import com.learning.service.ProgressionModuleService;
import com.learning.service.SuggestionService;
import com.learning.service.UserService;

@Service
public class NoteExamServiceImpl implements NoteExamService {

	@Autowired
	private NoteExamRepository noteExamRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ExamService examService;
	@Autowired
	private SuggestionService suggestionService;
	@Autowired
	private NoteExamRepositorySearchCriteria noteExamRepositorySearchCriteria;
	@Autowired
	private ProgressionModuleService progressionModuleService;

	// save or update
	@Override
	public NoteExamDTO save(NoteExamDTO noteExamDTO) {
		NoteExam noteExam = convertDTOtoModel(noteExamDTO);
		noteExam = noteExamRepository.save(noteExam);
		Long idModule = noteExamDTO.getExam().getModule().getId();
		Long idStudent = noteExamDTO.getUser().getId();
		progressionModuleService.updateProgressionModule(idModule, idStudent);
		return convertModelToDTO(noteExam);
	}

	@Override
	public NoteExamDTO findById(long idOut) {
		Optional<NoteExam> optional = noteExamRepository.findById(idOut);

		if (optional.isPresent()) {
			NoteExam noteExamFromDb = optional.get();
			return convertModelToDTO(noteExamFromDb);
		}
		return null;
	}

	@Override
	public void delete(NoteExam noteExam) {
		noteExamRepository.delete(noteExam);
	}

	@Override
	public PartialList<NoteExamDTO> findByCriteres(Demande<NoteExamDTO> demande) {

		List<NoteExam> exams = noteExamRepositorySearchCriteria.findByCriteres(demande);
		Long count = noteExamRepositorySearchCriteria.countByCriteres(demande);
		return new PartialList<NoteExamDTO>(count, convertEntitiesToDtos(exams));
	}

	@Override
	public NoteExam convertDTOtoModel(NoteExamDTO noteExamDTO) {
		NoteExam noteExam = new NoteExam();
		noteExam.setId(noteExamDTO.getId());
		noteExam.setScore(noteExamDTO.getScore());
		noteExam.setFinished(noteExamDTO.isFinished());
		noteExam.setShowScore(noteExamDTO.isShowScore());

		if (noteExamDTO.getUser() != null) {
			noteExam.setUser(userService.convertDTOtoModel(noteExamDTO.getUser()));
		}

		if (noteExamDTO.getExam() != null) {
			noteExam.setExam(examService.convertDTOtoModel(noteExamDTO.getExam()));
		}
		if (noteExamDTO.getAnswers() != null) {
			noteExam.setAnswers(suggestionService.convertDtosToEntities(noteExamDTO.getAnswers()));
		}
		return noteExam;
	}

	@Override
	public NoteExamDTO convertModelToDTO(NoteExam noteExam) {
		NoteExamDTO noteExamDTO = new NoteExamDTO();
		noteExamDTO.setId(noteExam.getId());
		noteExamDTO.setScore(noteExam.getScore());
		noteExamDTO.setFinished(noteExam.isFinished());
		noteExamDTO.setShowScore(noteExam.isShowScore());
		User user = noteExam.getUser();
		Exam exam = noteExam.getExam();
		List<Suggestion> answers = noteExam.getAnswers();
		if (user != null) {
			noteExamDTO.setUser(userService.convertModelToDTO(user));

		}
		if (exam != null) {
			noteExamDTO.setExam(examService.convertModelToDTO(exam));
		}
		if (answers != null) {
			noteExamDTO.setAnswers(suggestionService.convertEntitiesToDtos(answers));

		}

		noteExamDTO.setCreatedAt(noteExam.getCreatedAt());
		noteExamDTO.setUpdatedAt(noteExam.getUpdatedAt());
		return noteExamDTO;
	}

	@Override
	public PartialList<NoteExamDTO> convertToListDTO(PartialList<NoteExam> list) {

		return null;
	}

	@Override
	public void deleteById(Long id) {
		noteExamRepository.deleteById(id);

	}

	@Override
	public List<NoteExamDTO> convertEntitiesToDtos(List<NoteExam> noteExams) {
		// basic methode
		List<NoteExamDTO> list = new ArrayList<NoteExamDTO>();
		for (NoteExam noteExam : noteExams) {
			list.add(convertModelToDTO(noteExam));
		}
		return list;
	}

	@Override
	public List<NoteExam> convertDtosToEntities(List<NoteExamDTO> noteExamsDTO) {
		List<NoteExam> list = new ArrayList<NoteExam>();
		for (NoteExamDTO noteExamDTO : noteExamsDTO) {
			list.add(convertDTOtoModel(noteExamDTO));
		}
		return list;
	}

	@Override
	public void saveByExamAndStudent(Exam exam, List<UserDTO> students) {
		for (UserDTO userDTO : students) {
			NoteExam noteExam = new NoteExam();
			noteExam.setUser(userService.convertDTOtoModel(userDTO));
			noteExam.setExam(exam);
			noteExam.setScore(0.0);
			noteExamRepository.save(noteExam);
		}

	}

	@Override
	public List<Boolean> findStatutByUserAndModule(Long idUser, Long idModule) {

		return noteExamRepository.findStatutByUserAndModule(idUser, idModule);
	}

	@Override
	public List<Double> findByUserAndModuleAndType(Long idUser, Long idModule, TypeEnumExam type) {
		List<Double> notes = noteExamRepository.findByUserAndModuleAndType(idUser, idModule, type);
		return notes;
	}

	@Override
	public List<Object> getAverageGoodAndBadGrades(Long idTeacher, Long idGroup,Long idModule) {
		List<Object> list=null;
		if(idGroup>0&&idModule>0) {
			list= noteExamRepository.countSuccessByTeacherAndGroupAndModule(idTeacher, idGroup,idModule);
		}
		else if(idGroup>0) {
			list=noteExamRepository.countSuccessByTeacherAndGroup(idTeacher, idGroup);
		}
		else if(idModule>0) {
			list=noteExamRepository.countSuccessByTeacherAndModule(idTeacher, idModule);
		}
		else {
			list=noteExamRepository.countSuccessByTeacher(idTeacher);
		}
		
		return list;
	}

}
