package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dao.ProgressionModuleRepository;
import com.learning.dao.ProgressionModuleRepositorySearchCriteria;
import com.learning.dto.CourDTO;
import com.learning.dto.ModuleAffectedDTO;
import com.learning.dto.ProgressionModuleDTO;
import com.learning.dto.UserDTO;
import com.learning.model.ModuleAffected;
import com.learning.model.ProgressionModule;
import com.learning.model.TypeEnumExam;
import com.learning.model.User;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.CourService;
import com.learning.service.ExamService;
import com.learning.service.ModuleAffectedService;
import com.learning.service.NoteExamService;
import com.learning.service.ProgressionCourService;
import com.learning.service.ProgressionModuleService;
import com.learning.service.UserService;

@Service
public class ProgressionModuleServiceImpl implements ProgressionModuleService {

	@Autowired
	private ProgressionModuleRepository progressionModuleRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ModuleAffectedService moduleService;
	@Autowired
	private ProgressionCourService progressionCourService;

	@Autowired
	private CourService courService;

	@Autowired
	private ExamService examService;

	@Autowired
	private NoteExamService noteExamService;
	@Autowired
	private ProgressionModuleRepositorySearchCriteria progressionModuleRepositorySearchCriteria;

	// save or update
	@Override
	public ProgressionModuleDTO save(ProgressionModuleDTO progressionModuleDTO) {
		ProgressionModule progressionModule = convertDTOtoModel(progressionModuleDTO);
		progressionModule = progressionModuleRepository.save(progressionModule);
		return convertModelToDTO(progressionModule);
	}

	@Override
	public ProgressionModuleDTO findById(long idOut) {
		Optional<ProgressionModule> optional = progressionModuleRepository.findById(idOut);

		if (optional.isPresent()) {
			ProgressionModule progressionModuleFromDb = optional.get();
			return convertModelToDTO(progressionModuleFromDb);
		}
		return null;
	}

	@Override
	public void delete(ProgressionModule progressionModule) {
		progressionModuleRepository.delete(progressionModule);
	}

	@Override
	public PartialList<ProgressionModuleDTO> findByCriteres(Demande<ProgressionModuleDTO> demande) {

		List<ProgressionModule> progressionModules = progressionModuleRepositorySearchCriteria.findByCriteres(demande);
		Long count = progressionModuleRepositorySearchCriteria.countByCriteres(demande);
		return new PartialList<ProgressionModuleDTO>(count, convertEntitiesToDtos(progressionModules));
	}

	@Override
	public ProgressionModule convertDTOtoModel(ProgressionModuleDTO progressionModuleDTO) {
		ProgressionModule progressionModule = new ProgressionModule();
		progressionModule.setId(progressionModuleDTO.getId());
		progressionModule.setProgressionCour(progressionModuleDTO.getProgressionCour());
		progressionModule.setProgressionExamQuiz(progressionModuleDTO.getProgressionExamQuiz());
		progressionModule.setProgressionAbsence(progressionModuleDTO.getProgressionAbsence());
		progressionModule.setNoteFinal(progressionModuleDTO.getNoteFinal());

		if (progressionModuleDTO.getModule() != null) {
			progressionModule
					.setModule(moduleService.convertDTOtoModelWithOutRelation(progressionModuleDTO.getModule()));
		}
		if (progressionModuleDTO.getStudent() != null) {
			progressionModule
					.setStudent(userService.convertDTOtoModelWithOutRelation(progressionModuleDTO.getStudent()));
		}

		return progressionModule;
	}

	@Override
	public ProgressionModuleDTO convertModelToDTO(ProgressionModule progressionModule) {
		ProgressionModuleDTO progressionModuleDTO = new ProgressionModuleDTO();
		progressionModuleDTO.setId(progressionModule.getId());
		progressionModuleDTO.setProgressionCour(progressionModule.getProgressionCour());
		progressionModuleDTO.setProgressionExamQuiz(progressionModule.getProgressionExamQuiz());
		progressionModuleDTO.setProgressionAbsence(progressionModule.getProgressionAbsence());
		progressionModuleDTO.setNoteFinal(progressionModule.getNoteFinal());

		ModuleAffected module = progressionModule.getModule();
		User student = progressionModule.getStudent();
		if (module != null) {
			ModuleAffectedDTO moduleDTO = moduleService.convertModelToDTOWithOutRelation(module);

			moduleDTO.setHasExam(examService.countExamByModule(moduleDTO.getId()) > 0);
			progressionModuleDTO.setModule(moduleDTO);

		}

		if (student != null) {
			progressionModuleDTO.setStudent(userService.convertModelToDTOWithOutRelation(student));
		}
		return progressionModuleDTO;
	}

	@Override
	public PartialList<ProgressionModuleDTO> convertToListDTO(PartialList<ProgressionModule> list) {

		return null;
	}

	@Override
	public void deleteById(Long id) {
		progressionModuleRepository.deleteById(id);

	}

	@Override
	public List<ProgressionModuleDTO> convertEntitiesToDtos(List<ProgressionModule> progressionModules) {
		// basic methode
		List<ProgressionModuleDTO> list = new ArrayList<ProgressionModuleDTO>();
		for (ProgressionModule progressionModule : progressionModules) {
			list.add(convertModelToDTO(progressionModule));
		}
		return list;
	}

	@Override
	public List<ProgressionModule> convertDtosToEntities(List<ProgressionModuleDTO> progressionModulesDTO) {
		List<ProgressionModule> list = new ArrayList<ProgressionModule>();
		for (ProgressionModuleDTO progressionModuleDTO : progressionModulesDTO) {
			list.add(convertDTOtoModel(progressionModuleDTO));
		}
		return list;
	}

	@Override
	public void saveByModuleAndStudents(ModuleAffected module, List<UserDTO> students) {
		for (UserDTO student : students) {
			ProgressionModule progressionModule = new ProgressionModule();
			progressionModule.setModule(module);
			progressionModule.setStudent(userService.convertDTOtoModel(student));
			progressionModule.setProgressionCour(0.0);

			progressionModuleRepository.save(progressionModule);
		}

	}

	@Override
	public void updateProgressionModule(Long idModule, Long idStudent) {
		ProgressionModule progressionModule = progressionModuleRepository.findByModuleAndStudent(idModule, idStudent);
		if (progressionModule != null) {
			final List<Double> listProgressionCour = progressionCourService
					.listOfProgressionByModuleAndStudent(idModule, idStudent);
			final List<Boolean> listStatutNoteExam = noteExamService.findStatutByUserAndModule(idStudent, idModule);
			// calculate progression Cour
			Double progressionTemp = 0.0;
			Double resultProgressionCour = 0.0;
			for (Double progressionCour : listProgressionCour) {

				progressionTemp += progressionCour;
			}
			resultProgressionCour = progressionTemp / listProgressionCour.size();
			// calculate progression Exam and quiz
			Double progressionExamQuiz = 0.0;
			Double resultProgressionExamQuiz = 0.0;
			if (listStatutNoteExam != null && listStatutNoteExam.size() > 0) {
				for (Boolean finished : listStatutNoteExam) {
					if (finished) {
						progressionExamQuiz++;
					}

				}
				resultProgressionExamQuiz = (progressionExamQuiz * 100) / listStatutNoteExam.size();
				resultProgressionExamQuiz = Math.floor(resultProgressionExamQuiz * 100) / 100;

				progressionModule.setProgressionExamQuiz(resultProgressionExamQuiz);

			}
			progressionModule.setProgressionCour(resultProgressionCour);
			progressionModuleRepository.save(progressionModule);
		}
	}

	@Override
	public void saveByStudentAndModules(User student, List<ModuleAffectedDTO> modules) {
		for (ModuleAffectedDTO module : modules) {
			ProgressionModule progressionModule = new ProgressionModule();
			progressionModule.setModule(moduleService.convertDTOtoModel(module));
			progressionModule.setStudent(student);
			progressionModule.setProgressionCour(0.0);
			progressionModuleRepository.save(progressionModule);
			final List<CourDTO> cours = courService.findByModuleAndLaunched(module.getId(), true);
			progressionCourService.saveByStudentAndCours(student, cours);

		}

	}

	@Override
	public void calculateNoteFinal(Long idModule, List<UserDTO> students) {

		for (UserDTO student : students) {
			calculateNoteFinalByStudent(idModule, student.getId());
		}
	}

	private void calculateNoteFinalByStudent(Long idModule, Long idStudent) {
		Double noteExam = 0.0, noteQuiz = 0.0, noteFinal = 0.0;
		ProgressionModule progressionModule = progressionModuleRepository.findByModuleAndStudent(idModule, idStudent);
		List<Double> notesExam = noteExamService.findByUserAndModuleAndType(idStudent, idModule, TypeEnumExam.EXAM);
		List<Double> notesQuiz = noteExamService.findByUserAndModuleAndType(idStudent, idModule, TypeEnumExam.QUIZ);
		Double noteCour = progressionModule.getProgressionCour();
		Double noteAbsence = progressionModule.getProgressionAbsence();
		Double percentageAbsence = progressionModule.getModule().getPercentageAbsence();
		Double percentageCour = progressionModule.getModule().getPercentageCour();
		Double percentageExam = progressionModule.getModule().getPercentageExam();
		Double percentageQuiz = progressionModule.getModule().getPercentageQuiz();

		if (notesExam != null && notesExam.size() > 0) {
			for (Double noteE : notesExam) {

				noteExam += noteE;
			}
			noteExam = noteExam / notesExam.size();
		}

		if (noteQuiz != null && notesQuiz.size() > 0) {
			for (Double noteQ : notesQuiz) {

				noteQuiz += noteQ;
			}
			noteQuiz = noteQuiz / notesQuiz.size();
		}

		noteFinal = (noteAbsence * percentageAbsence + noteCour * percentageCour + noteExam * percentageExam
				+ noteQuiz * percentageQuiz) / 100;
		progressionModule.setNoteFinal(noteFinal);
		progressionModuleRepository.save(progressionModule);

	}

	@Override
	public List<Object> getAverageSuccessStudent(Long idTeacher, Long idGroup, Long idModule) {
		List<Object> list=null;
		if(idGroup>0&&idModule>0) {
			list= progressionModuleRepository.countSuccessByTeacherAndGroupAndModule(idTeacher, idGroup,idModule);
		}
		else if(idGroup>0) {
			list=progressionModuleRepository.countSuccessByTeacherAndGroup(idTeacher, idGroup);
		}
		else if(idModule>0) {
			list=progressionModuleRepository.countSuccessByTeacherAndModule(idTeacher, idModule);
		}
		else {
			list=progressionModuleRepository.countSuccessByTeacher(idTeacher);
		}
		
		return list;
	}

}
