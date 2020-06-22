package com.learning.service.impl;

import java.time.LocalDate;
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
import com.learning.dto.SessionDTO;
import com.learning.dto.UserDTO;
import com.learning.model.ModuleAffected;
import com.learning.model.Organization;
import com.learning.model.ProgressionModule;
import com.learning.model.StatutEnum;
import com.learning.model.TypeEnumExam;
import com.learning.model.TypeOrganizationEnum;
import com.learning.model.User;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.CourService;
import com.learning.service.ExamService;
import com.learning.service.ModuleAffectedService;
import com.learning.service.NoteExamService;
import com.learning.service.ProgressionCourService;
import com.learning.service.ProgressionModuleService;
import com.learning.service.SessionService;
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

	@Autowired
	private SessionService sessionService;

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
	public void calculateNoteFinal(Long idModule) {
		List<ProgressionModule> list = findByModuleAndSecondNotSuccess(idModule);
		if (!list.isEmpty()) {
			Organization org = list.get(0).getStudent().getOrganization();
			for (ProgressionModule progressionModule : list) {
				calculateNoteFinalByStudent(progressionModule, org);
			}
		}
	}

	private void calculateNoteFinalByStudent(ProgressionModule progressionModule, Organization organization) {
		Long idStudent = progressionModule.getStudent().getId();
		Long idModule = progressionModule.getModule().getId();
		Double noteExam = 0.0, noteQuiz = 0.0, noteFinal = 0.0;
		List<Double> notesExam = noteExamService.findByUserAndModuleAndType(idStudent, idModule, TypeEnumExam.EXAM);
		List<Double> notesQuiz = noteExamService.findByUserAndModuleAndType(idStudent, idModule, TypeEnumExam.QUIZ);
		Double noteCour = progressionModule.getProgressionCour();
		Double noteAbsence = progressionModule.getProgressionAbsence();
		ModuleAffected module = progressionModule.getModule();
		Double percentageAbsence = module.getPercentageAbsence();
		Double percentageCour = module.getPercentageCour();
		Double percentageExam = module.getPercentageExam();
		Double percentageQuiz = module.getPercentageQuiz();
		Double thresholdeCatchUp = (progressionModule.getStudent().getOrganization().getThresholdeCatchUp() * 100)
				/ organization.getScale();
		Double thresholdeSucccess = (progressionModule.getStudent().getOrganization().getThresholdeSucccess() * 100)
				/ organization.getScale();
		boolean firstSuscces = progressionModule.isFirstSuccess();

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

		if (organization.getType().equals(TypeOrganizationEnum.HIGHER_EDUCATION)) {
			if (progressionModule.getStatut() == null) {
				boolean success = noteFinal >= thresholdeSucccess;
				progressionModule.setFirsNote(noteFinal);
				progressionModule.setFirstSuccess(success);
				progressionModule.setSecondSuccess(success);
				progressionModule.setNoteFinal(noteFinal);
				progressionModule.setStatut(success ? StatutEnum.SUCCESS
						: noteFinal < thresholdeCatchUp ? StatutEnum.FAILED : StatutEnum.CATCHING_UP);
			} else if (!firstSuscces && progressionModule.getStatut().equals(StatutEnum.CATCHING_UP)) {
				progressionModule.setNoteFinal(noteFinal);
				progressionModule.setSecondSuccess(noteFinal >= thresholdeSucccess);
				progressionModule.setStatut(noteFinal >= thresholdeSucccess ? StatutEnum.SUCCESS : StatutEnum.FAILED);
			}

		} else {
			progressionModule.setStatut(noteFinal >= thresholdeSucccess ? StatutEnum.SUCCESS : StatutEnum.FAILED);
			progressionModule.setFirsNote(noteFinal);
			progressionModule.setNoteFinal(noteFinal);
			progressionModule.setFirstSuccess(noteFinal >= thresholdeSucccess);
			progressionModule.setSecondSuccess(noteFinal >= thresholdeSucccess);
		}

		progressionModuleRepository.save(progressionModule);

	}

	@Override
	public List<Object> getAverageSuccessStudent(Long idTeacher, Long idGroup, Long idModule) {
		UserDTO user = userService.findById(idTeacher);
		LocalDate current = LocalDate.now();
		SessionDTO sessionDTO = sessionService.findCurrentSessionByOranization(user.getOrganization().getId(), current);
		List<Object> list = null;
		if (idGroup > 0 && idModule > 0) {
			list = progressionModuleRepository.countSuccessByTeacherAndGroupAndModule(idTeacher, idGroup, idModule,
					sessionDTO.getId());
		} else if (idGroup > 0) {
			list = progressionModuleRepository.countSuccessByTeacherAndGroup(idTeacher, idGroup, sessionDTO.getId());
		} else if (idModule > 0) {
			list = progressionModuleRepository.countSuccessByTeacherAndModule(idTeacher, idModule, sessionDTO.getId());
		} else {
			list = progressionModuleRepository.countSuccessByTeacher(idTeacher, sessionDTO.getId());
		}

		return list;
	}

	@Override
	public List<ProgressionModule> findByModuleAndSecondNotSuccess(Long idModule) {

		return progressionModuleRepository.findByModuleAndSecondNotSuccess(idModule);
	}

	@Override
	public Long countModuleByStudent(Long idStudent) {
		Long count = progressionModuleRepository.countModuleByStudent(idStudent);
		return count != null ? count : 0;
	}

	@Override
	public List<ModuleAffectedDTO> getModuleByStudent(Long idStudent) {

		return moduleService
				.convertEntitiesToDtosWithOutRelation(progressionModuleRepository.getModuleByStudent(idStudent));
	}

	@Override
	public List<Object> getAverageSuccessStudentByOrg(Long idOrg, Long idLevel, Long idBranch, Long idGroup) {
		LocalDate current = LocalDate.now();
		SessionDTO sessionDTO = sessionService.findCurrentSessionByOranization(idOrg, current);
		List<Object> list = null;
		if (idGroup == 0 && idLevel == 0 && idBranch == 0) {
			list = progressionModuleRepository.countSuccessByOrganization(idOrg, sessionDTO.getId());
		} else if (idGroup != 0) {
			list = progressionModuleRepository.countSuccessByGroup(idGroup, sessionDTO.getId());
		} else if (idLevel != 0) {
			list = progressionModuleRepository.countSuccessByLevel(idLevel, sessionDTO.getId());
		} else if (idBranch != 0) {
			list = progressionModuleRepository.countSuccessByLevel(idBranch, sessionDTO.getId());
		} else {
			list = progressionModuleRepository.countSuccessByLevelAndBranch(idLevel, idBranch, sessionDTO.getId());
		}

		return list;
	}

}
