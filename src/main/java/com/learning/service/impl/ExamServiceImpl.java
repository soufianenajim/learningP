package com.learning.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.learning.dao.ExamRepository;
import com.learning.dao.ExamRepositorySearchCriteria;
import com.learning.dto.ExamDTO;
import com.learning.dto.UserDTO;
import com.learning.model.Exam;
import com.learning.model.ModuleAffected;
import com.learning.model.Question;
import com.learning.model.RoleName;
import com.learning.model.TypeEnumExam;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.ExamService;
import com.learning.service.ModuleAffectedService;
import com.learning.service.NoteExamService;
import com.learning.service.QuestionService;
import com.learning.service.UserService;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ModuleAffectedService moduleService;
	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private ExamRepositorySearchCriteria examRepositorySearchCriteria;
	@Autowired
	private UserService userService;

	@Autowired
	private NoteExamService noteExamService;

	@Override
	public ExamDTO save(ExamDTO examDTO) {
		Exam exam = convertDTOtoModel(examDTO);
		exam = examRepository.save(exam);
		if (examDTO.getQuestions() != null) {
			questionService.saveQuestionsByExam(examDTO.getQuestions(), exam);
		}
		if (examDTO.getId() == null)
			launchExam(exam);
		return convertModelToDTO(exam);
	}

	@Override
	public ExamDTO findById(long idOut) {
		Optional<Exam> optional = examRepository.findById(idOut);

		if (optional.isPresent()) {
			Exam examFromDb = optional.get();
			return convertModelToDTO(examFromDb);
		}
		return null;
	}

	@Override
	public void delete(Exam exam) {
		examRepository.delete(exam);

	}

	@Override
	public PartialList<ExamDTO> findByCriteres(Demande<ExamDTO> demande) {

		List<Exam> exams = examRepositorySearchCriteria.findByCriteres(demande);
		Long count = examRepositorySearchCriteria.countByCriteres(demande);
		return new PartialList<ExamDTO>(count, convertEntitiesToDtos(exams));

	}

	@Override
	public Exam convertDTOtoModel(ExamDTO examDTO) {
		Exam exam = new Exam();
		exam.setId(examDTO.getId());
		exam.setName(examDTO.getName());
		exam.setStartDateTime(examDTO.getStartDateTime() != null ? examDTO.getStartDateTime().withSecond(0) : null);
		exam.setEndDateTime(examDTO.getEndDateTime() != null ? examDTO.getEndDateTime().withSecond(0) : null);
		exam.setScale(examDTO.getScale());
		if (examDTO.getModule() != null) {
			exam.setModule(moduleService.convertDTOtoModel(examDTO.getModule()));
		}

		if (examDTO.getType() != null && !StringUtils.isEmpty(examDTO.getType())) {
			exam.setType(TypeEnumExam.valueOf(examDTO.getType()));
		}
		return exam;
	}

	@Override
	public ExamDTO convertModelToDTO(Exam exam) {
		ExamDTO examDTO = new ExamDTO();
		examDTO.setId(exam.getId());
		examDTO.setName(exam.getName());
		examDTO.setStartDateTime(exam.getStartDateTime());
		examDTO.setEndDateTime(exam.getEndDateTime());
		examDTO.setScale(exam.getScale());
		examDTO.setLaunched(exam.isLaunched());
		ModuleAffected module = exam.getModule();
		List<Question> questions = exam.getQuestions();
		TypeEnumExam type = exam.getType();
		if (module != null) {
			examDTO.setModule(moduleService.convertModelToDTO(exam.getModule()));
		}
		if (questions != null) {
			examDTO.setQuestions(questionService.convertEntitiesToDtos(questions));
		}
		if (type != null) {
			examDTO.setType(type.toString());
		}
		examDTO.setCreatedAt(exam.getCreatedAt());
		examDTO.setUpdatedAt(exam.getUpdatedAt());
		return examDTO;
	}

	@Override
	public PartialList<ExamDTO> convertToListDTO(PartialList<Exam> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		examRepository.deleteById(id);
		questionService.detachExam(id);

	}

	@Override
	public List<ExamDTO> convertEntitiesToDtos(List<Exam> exams) {
		// basic methode
		List<ExamDTO> list = new ArrayList<ExamDTO>();
		for (Exam exam : exams) {
			list.add(convertModelToDTO(exam));
		}
		return list;
	}

	@Override
	public List<Exam> convertDtosToEntities(List<ExamDTO> examsDTO) {
		List<Exam> list = new ArrayList<Exam>();
		for (ExamDTO examDTO : examsDTO) {
			list.add(convertDTOtoModel(examDTO));
		}
		return list;
	}

	@Override
	public void deleteByModule(Long idModule) {
		examRepository.deleteByModule(idModule);

	}

	@Override
	public List<ExamDTO> findByModule(Long idModule) {

		return convertEntitiesToDtos(examRepository.findByModule(idModule));
	}

	@Override
	public ExamDTO convertModelToDTOWithoutQuestion(Exam exam) {

		ExamDTO examDTO = new ExamDTO();
		examDTO.setId(exam.getId());
		examDTO.setName(exam.getName());
		examDTO.setStartDateTime(exam.getStartDateTime());
		examDTO.setEndDateTime(exam.getEndDateTime());
		examDTO.setLaunched(exam.isLaunched());
		ModuleAffected module = exam.getModule();

		if (module != null) {
			examDTO.setModule(moduleService.convertModelToDTO(exam.getModule()));
		}

		examDTO.setCreatedAt(exam.getCreatedAt());
		examDTO.setUpdatedAt(exam.getUpdatedAt());
		return examDTO;
	}

	@Override
	public Long countExamByModule(Long idModule) {

		return examRepository.countExamByModule(idModule);
	}

	@Override
	public List<ExamDTO> findByUser(Long idUser) {
		LocalDateTime now = LocalDateTime.now();
		List<Exam> exams = examRepository.findByUser(now);
		return convertEnititiesToDTOsWithoutQuestion(exams);
	}

	@Override
	public List<ExamDTO> convertEnititiesToDTOsWithoutQuestion(List<Exam> list) {

		return list.stream().map(e -> convertModelToDTOWithoutQuestion(e)).collect(Collectors.toList());
	}

	void launchExam(Exam exam) {
		exam.setLaunched(true);
		examRepository.save(exam);
		ModuleAffected module = exam.getModule();
		Long idGroup = moduleService.getGroupByModule(module.getId());
		List<UserDTO> students = userService.findByGroupAndRole(idGroup, RoleName.ROLE_STUDENT);
		if (students != null) {
			noteExamService.saveByExamAndStudent(exam, students);
		}
	}

	@Override
	public Long countExamByTeacherAndGroupAndType(Long idTeacher, Long idGroup, String type) {
		TypeEnumExam typeEnumExam = TypeEnumExam.valueOf(type);

		Long count = idGroup > 0 ? examRepository.countExamByTeacherAndGroupAndType(idTeacher, idGroup, typeEnumExam)
				: examRepository.countExamByTeacherAndType(idTeacher, typeEnumExam);
		return count;
	}

}
